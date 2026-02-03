package com.cooffice.service;

import com.cooffice.dto.SpaceEnquiryRequest;
import com.cooffice.entity.SpaceEnquiry;

import java.util.List;
import java.util.Optional;

public interface SpaceEnquiryService {

    void saveEnquiry(SpaceEnquiryRequest request);
    List<SpaceEnquiry> getAllEnquiries();
    Optional<SpaceEnquiry> getEnquiryById(Long id);
    SpaceEnquiry updateEnquiry(Long id, SpaceEnquiryRequest request);
    void deleteEnquiry(Long id);
}
