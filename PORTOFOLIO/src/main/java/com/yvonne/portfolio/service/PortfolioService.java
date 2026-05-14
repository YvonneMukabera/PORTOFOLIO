package com.yvonne.portfolio.service;

import com.yvonne.portfolio.model.Certificate;
import com.yvonne.portfolio.model.Education;
import com.yvonne.portfolio.model.Experience;
import com.yvonne.portfolio.model.PortfolioProfile;
import com.yvonne.portfolio.model.SkillCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioProfile profile = new PortfolioProfile(
            "Yvonne Mukabera",
            "Software Engineering Student, UI/UX Designer & Full-Stack Developer",
            "Nyarugenge, Kigali City, Rwanda",
            "+250793835394",
            "mukabera255@gmail.com",
            "I am a Computer Engineering student specializing in Software Engineering, building a practical foundation across UI/UX design, frontend development, backend systems, and learner-focused digital support.",
            List.of(
                    "I study Computer Engineering at the University of Rwanda, Nyarugenge Campus",
                    "I specialize in Software Engineering and build practical, user-focused digital solutions",
                    "I designed a Citizen Engagement System prototype that won second place at the DTP Hackathon 2025",
                    "I support learners with programming fundamentals, digital skills, and platform navigation"
            ),
            List.of(
                    new SkillCategory(
                            "UI & UX",
                            "I design clean, user-friendly interfaces before implementation, so the final product has structure, purpose, and a better user journey.",
                            List.of("Figma", "Wireframing", "Prototyping", "User flows", "Responsive layout planning")
                    ),
                    new SkillCategory(
                            "Frontend",
                            "I build responsive web interfaces with clear structure, polished styling, interactive behavior, and attention to accessibility.",
                            List.of("HTML", "CSS", "JavaScript", "Responsive design", "DOM interaction")
                    ),
                    new SkillCategory(
                            "Backend",
                            "I work with backend frameworks that power applications, validate user input, serve APIs, and organize application data.",
                            List.of("Spring Boot", "Django", "Java", "Python", "REST APIs")
                    ),
                    new SkillCategory(
                            "Database",
                            "I can structure application data, connect backend services to databases, and manage records with clear data models.",
                            List.of("MySQL", "SQL basics", "Database tables", "Data modeling", "CRUD foundations")
                    ),
                    new SkillCategory(
                            "Tools & Collaboration",
                            "I use professional tools to manage code, document progress, and collaborate with others as my projects grow.",
                            List.of("Git", "GitHub", "IntelliJ IDEA", "VS Code", "IT Essentials")
                    )
            ),
            List.of(
                    new Education(
                            "Bachelor of Science with Honors in Computer Engineering",
                            "University of Rwanda, Nyarugenge Campus",
                            "Software Engineering",
                            "September 2024 - October 2028"
                    )
            ),
            List.of(
                    new Experience(
                            "Intern - Digital Skills Support",
                            "DTP Rwanda",
                            "October 2025 - January 2026",
                            List.of(
                                    "Assisted learners using the DTP Rwanda platform",
                                    "Guided students through basic software development concepts and digital skills",
                                    "Supported practical sessions in programming fundamentals and platform navigation",
                                    "Demonstrated tasks and reinforced core technical concepts"
                            )
                    ),
                    new Experience(
                            "Office Assistant",
                            "ES Sancta Maria Karambo",
                            "September 2023 - January 2024",
                            List.of(
                                    "Printed, photocopied, and scanned documents for school staff and students",
                                    "Prepared teaching and learning materials including handouts, exams, and worksheets",
                                    "Supported the secretary with office work and document organization"
                            )
                    )
            ),
            List.of(
                    new Certificate("Software Development Certificate", "DTP Rwanda", "/assets/docs/DTP ADVANCED CERTIFICATE.pdf"),
                    new Certificate("UI/UX Design Certificate", "KLab TechHer Online Courses", "/assets/docs/FIGMA UI DESIGN CERTIFICATE.pdf"),
                    new Certificate("IT Essentials Certificate", "Cisco NetAcad", "/assets/docs/IT ESSENTIAL.pdf"),
                    new Certificate("Teaching Assistant Certificate", "IEE Rwanda", "/assets/docs/IEE certificate of participati.pdf"),
                    new Certificate("HTML Certificate", "Online Certification", "/assets/docs/HTML CERTIFICATE.pdf"),
                    new Certificate("CSS Certificate", "Online Certification", "/assets/docs/CSS CERTIFICATE.pdf"),
                    new Certificate("JavaScript Certificate", "Online Certification", "/assets/docs/JAVASCRIPT CERTIFICATE.pdf"),
                    new Certificate("High School Diploma", "Academic Record", "/assets/docs/mukaberayvonne diploma.pdf"),
                    new Certificate("Best Performance Award", "Achievement", "/assets/docs/Best performance award.pdf"),
                    new Certificate("Full CV", "Yvonne Mukabera", "/assets/docs/Yvonne-Mukabera-CV.pdf")
            )
    );

    public PortfolioProfile getProfile() {
        return profile;
    }
}
