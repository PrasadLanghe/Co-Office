package com.cooffice.service;

import org.springframework.stereotype.Service;

import com.cooffice.dto.ContactRequest;
import com.cooffice.entity.Contact;
import com.cooffice.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void saveContact(ContactRequest request) {
        Contact contact = new Contact();
        contact.setFullName(request.getFullName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setMessage(request.getMessage());

        contactRepository.save(contact);
    }
}