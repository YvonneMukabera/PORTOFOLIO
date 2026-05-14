package com.yvonne.portfolio.service.storage;

import com.yvonne.portfolio.model.ContactMessage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("!xampp")
public class InMemoryContactRepository implements ContactRepository {

    private final List<ContactMessage> messages = new ArrayList<>();

    @Override
    public synchronized ContactMessage save(ContactMessage message) {
        message.setSubmittedAt(Instant.now());
        messages.add(message);
        return message;
    }

    @Override
    public synchronized int count() {
        return messages.size();
    }
}
