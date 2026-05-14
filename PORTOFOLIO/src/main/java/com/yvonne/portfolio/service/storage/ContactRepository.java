package com.yvonne.portfolio.service.storage;

import com.yvonne.portfolio.model.ContactMessage;

public interface ContactRepository {

    ContactMessage save(ContactMessage message);

    int count();
}
