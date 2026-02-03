package com.cooffice.controller;



import com.cooffice.dto.SpaceEnquiryRequest;
import com.cooffice.entity.SpaceEnquiry;
import com.cooffice.service.SpaceEnquiryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/space-enquiry")
@CrossOrigin(origins = "*")
public class SpaceEnquiryController {

    private final SpaceEnquiryService service;

    public SpaceEnquiryController(SpaceEnquiryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> submitEnquiry(@Valid @RequestBody SpaceEnquiryRequest request) {
        service.saveEnquiry(request);
        return ResponseEntity.ok("Enquiry submitted successfully");
    }

    @GetMapping
    public ResponseEntity<List<SpaceEnquiry>> getAllEnquiries() {
        return ResponseEntity.ok(service.getAllEnquiries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceEnquiry> getEnquiryById(@PathVariable Long id) {
        return service.getEnquiryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceEnquiry> updateEnquiry(@PathVariable Long id,
                                                      @Valid @RequestBody SpaceEnquiryRequest request) {
        return ResponseEntity.ok(service.updateEnquiry(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnquiry(@PathVariable Long id) {
        service.deleteEnquiry(id);
        return ResponseEntity.ok("Enquiry deleted successfully");
    }

    // Optional: API for dropdowns
    @GetMapping("/spacetypes")
    public List<String> getSpaceTypes() {
        return List.of("Private Cabin", "Team Cabin", "Meeting Room", "Private Pod");
    }

    @GetMapping("/cities")
    public List<String> getCities() {
        return List.of("Pune", "Mumbai", "Akola");
    }
}
