package com.cooffice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooffice.dto.ContactRequest;
import com.cooffice.entity.Contact;
import com.cooffice.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final EmailService emailService;

    public ContactServiceImpl(ContactRepository contactRepository,  EmailService emailService) {
        this.contactRepository = contactRepository;
        this.emailService=emailService;
    }

    @Override
    public void saveContact(ContactRequest request) {
        Contact contact = new Contact();
        contact.setFullName(request.getFullName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setMessage(request.getMessage());

        // Save to DB
        contactRepository.save(contact);

        // Send Email
        String subject = "New Contact Enquiry: " + contact.getFullName();
        String text = "You received a new contact enquiry:\n\n" +
                      "Full Name: " + contact.getFullName() + "\n" +
                      "Email: " + contact.getEmail() + "\n" +
                      "Phone: " + contact.getPhone() + "\n" +
                      "Message: " + contact.getMessage();

        emailService.sendSimpleMessage("your_email@gmail.com", subject, text);
    }
    
    // READ ALL
    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // READ BY ID
    @Override
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    // UPDATE
    @Override
    public Contact updateContact(Long id, ContactRequest request) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if(optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setFullName(request.getFullName());
            contact.setEmail(request.getEmail());
            contact.setPhone(request.getPhone());
            contact.setMessage(request.getMessage());
            return contactRepository.save(contact);
        }
        throw new RuntimeException("Contact not found with ID " + id);
    }

    // DELETE
    @Override
    public void deleteContact(Long id) {
        if(contactRepository.existsById(id)) {
        	contactRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contact not found with ID " + id);
        }
    }
}