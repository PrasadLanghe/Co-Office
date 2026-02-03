package com.cooffice.service;

import com.cooffice.dto.SpaceEnquiryRequest;
import com.cooffice.entity.SpaceEnquiry;
import com.cooffice.repository.SpaceEnquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceEnquiryServiceImpl implements SpaceEnquiryService {

    private final SpaceEnquiryRepository repository;
    private final EmailService emailService; // Optional for sending emails

    public SpaceEnquiryServiceImpl(SpaceEnquiryRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Override
    public void saveEnquiry(SpaceEnquiryRequest request) {
        SpaceEnquiry enquiry = new SpaceEnquiry();
        enquiry.setName(request.getName());
        enquiry.setEmail(request.getEmail());
        enquiry.setPhone(request.getPhone());
        enquiry.setSpaceType(request.getSpaceType());
        enquiry.setCity(request.getCity());

        repository.save(enquiry);

        // send email
        String subject = "New Space Enquiry: " + enquiry.getName();
        String text = "New enquiry details:\n\n" +
                      "Name: " + enquiry.getName() + "\n" +
                      "Email: " + enquiry.getEmail() + "\n" +
                      "Phone: " + enquiry.getPhone() + "\n" +
                      "Space Type: " + enquiry.getSpaceType() + "\n" +
                      "City: " + enquiry.getCity();

        emailService.sendSimpleMessage("your_email@gmail.com", subject, text);
    }

    @Override
    public List<SpaceEnquiry> getAllEnquiries() { return repository.findAll(); }

    @Override
    public Optional<SpaceEnquiry> getEnquiryById(Long id) { return repository.findById(id); }

    @Override
    public SpaceEnquiry updateEnquiry(Long id, SpaceEnquiryRequest request) {
        SpaceEnquiry enquiry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));

        enquiry.setName(request.getName());
        enquiry.setEmail(request.getEmail());
        enquiry.setPhone(request.getPhone());
        enquiry.setSpaceType(request.getSpaceType());
        enquiry.setCity(request.getCity());

        return repository.save(enquiry);
    }

    @Override
    public void deleteEnquiry(Long id) { repository.deleteById(id); }
}
