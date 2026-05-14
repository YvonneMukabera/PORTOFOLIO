const year = document.getElementById("year");
if (year) {
    year.textContent = new Date().getFullYear();
}

const navToggle = document.querySelector(".nav-toggle");
const navLinks = document.querySelector(".nav-links");

if (navToggle && navLinks) {
    navToggle.addEventListener("click", () => {
        const isOpen = navLinks.classList.toggle("open");
        navToggle.setAttribute("aria-expanded", String(isOpen));
    });

    navLinks.querySelectorAll("a").forEach((link) => {
        link.addEventListener("click", () => {
            navLinks.classList.remove("open");
            navToggle.setAttribute("aria-expanded", "false");
        });
    });
}

const revealObserver = new IntersectionObserver(
    (entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                entry.target.classList.add("visible");
                revealObserver.unobserve(entry.target);
            }
        });
    },
    { threshold: 0.16 }
);

document.querySelectorAll(".reveal").forEach((element) => {
    revealObserver.observe(element);
});

const portraitPanel = document.querySelector(".portrait-panel");
const portraitImage = document.querySelector(".portrait-image");

if (portraitPanel && portraitImage) {
    portraitPanel.addEventListener("mousemove", (event) => {
        const bounds = portraitPanel.getBoundingClientRect();
        const x = (event.clientX - bounds.left) / bounds.width - 0.5;
        const y = (event.clientY - bounds.top) / bounds.height - 0.5;
        portraitImage.style.transform = `translateY(-8px) rotateX(${y * -5}deg) rotateY(${x * 7}deg)`;
    });

    portraitPanel.addEventListener("mouseleave", () => {
        portraitImage.style.transform = "";
    });
}

const contactForm = document.getElementById("contactForm");
const formStatus = document.getElementById("formStatus");
const contactErrorMessages = {
    email_not_enabled: "Email sending is not enabled on this server yet.",
    email_username_missing: "Email username is missing on this server.",
    email_password_missing: "Email app password is missing on this server.",
    email_failed: "Email delivery failed. Check the Gmail app password and try again."
};

function setStatus(message, type = "") {
    if (!formStatus) {
        return;
    }
    formStatus.textContent = message;
    formStatus.className = `form-status ${type}`.trim();
}

if (contactForm) {
    contactForm.addEventListener("submit", async (event) => {
        event.preventDefault();
        const submitButton = contactForm.querySelector("button[type='submit']");
        const formData = new FormData(contactForm);
        const payload = Object.fromEntries(formData.entries());

        if (!payload.firstName || !payload.lastName || !payload.email || !payload.message) {
            setStatus("Please complete all fields before sending.", "error");
            return;
        }

        setStatus("Sending your message...");
        submitButton.disabled = true;

        try {
            const response = await fetch("/api/contact", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload)
            });
            const result = await response.json();

            if (!response.ok) {
                const errors = result.errors ? Object.values(result.errors).join(" ") : "Please check the form.";
                setStatus(errors, "error");
                return;
            }

            if (result.emailStatus !== "email_sent") {
                setStatus(contactErrorMessages[result.emailStatus] || "Message could not be sent yet. Please try again later.", "error");
                return;
            }

            contactForm.reset();
            setStatus("Thank you. Your message was sent to Yvonne's inbox.", "success");
        } catch (error) {
            setStatus("Message could not be sent. Please try again later.", "error");
        } finally {
            submitButton.disabled = false;
        }
    });
}
