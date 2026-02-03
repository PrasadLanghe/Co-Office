package com.cooffice.repository;

import com.cooffice.entity.SpaceEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceEnquiryRepository extends JpaRepository<SpaceEnquiry, Long> {
}
