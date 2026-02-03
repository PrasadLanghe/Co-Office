package com.cooffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooffice.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}