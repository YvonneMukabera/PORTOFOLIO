package com.yvonne.portfolio.service;

import com.yvonne.portfolio.model.ContactMessage;
import com.yvonne.portfolio.model.ContactSubmissionResult;
import com.yvonne.portfolio.service.storage.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final EmailService emailService;

    public ContactService(ContactRepository contactRepository, EmailService emailService) {
        this.contactRepository = contactRepository;
        this.emailService = emailService;
    }

    public ContactSubmissionResult submit(ContactMessage message) {
        ContactMessage saved = contactRepository.save(message);
        String emailStatus = emailService.sendContactMessage(saved);
        return new ContactSubmissionResult(saved, emailStatus);
    }

    public int count() {
        return contactRepository.count();
    }
}
