package com.yvonne.portfolio.controller;

import com.yvonne.portfolio.model.ContactMessage;
import com.yvonne.portfolio.model.ContactSubmissionResult;
import com.yvonne.portfolio.model.PortfolioProfile;
import com.yvonne.portfolio.service.ContactService;
import com.yvonne.portfolio.service.DatabaseStatusService;
import com.yvonne.portfolio.service.PortfolioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final PortfolioService portfolioService;
    private final ContactService contactService;
    private final DatabaseStatusService databaseStatusService;

    public ApiController(PortfolioService portfolioService, ContactService contactService, DatabaseStatusService databaseStatusService) {
        this.portfolioService = portfolioService;
        this.contactService = contactService;
        this.databaseStatusService = databaseStatusService;
    }

    @GetMapping("/profile")
    public PortfolioProfile profile() {
        return portfolioService.getProfile();
    }

    @GetMapping("/database/status")
    public Map<String, Object> databaseStatus() {
        return databaseStatusService.status();
    }

    @PostMapping("/contact")
    public ResponseEntity<Map<String, Object>> contact(@Valid @RequestBody ContactMessage message) {
        ContactSubmissionResult result = contactService.submit(message);
        ContactMessage saved = result.message();
        String responseMessage = switch (result.emailStatus()) {
            case "email_sent" -> "Thank you, " + saved.getFirstName() + ". Your message has been sent directly to Yvonne.";
            case "email_not_enabled" -> "Thank you, " + saved.getFirstName() + ". Your message has been saved. Email sending is not enabled yet.";
            case "email_username_missing" -> "Thank you, " + saved.getFirstName() + ". Your message has been saved. Email username is missing.";
            case "email_password_missing" -> "Thank you, " + saved.getFirstName() + ". Your message has been saved. Email password is missing.";
            case "email_failed" -> "Thank you, " + saved.getFirstName() + ". Your message has been saved, but email delivery failed. Yvonne can still view it in the database.";
            default -> "Thank you, " + saved.getFirstName() + ". Your message has been received.";
        };
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", responseMessage,
                "emailStatus", result.emailStatus(),
                "submittedAt", saved.getSubmittedAt(),
                "totalMessages", contactService.count()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validationError(MethodArgumentNotValidException exception) {
        Map<String, String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (first, second) -> first
                ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", "error",
                "errors", errors
        ));
    }
}
