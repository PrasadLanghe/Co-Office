package com.cooffice.service;

import java.util.List;
import java.util.Optional;

import com.cooffice.dto.ContactRequest;
import com.cooffice.entity.Contact;

public interface ContactService {
    void saveContact(ContactRequest request);
    // Read all
    List<Contact> getAllContacts();

    // Read by ID
    Optional<Contact> getContactById(Long id);

    // Update
    Contact updateContact(Long id, ContactRequest request);

    // Delete
    void deleteContact(Long id);
}
