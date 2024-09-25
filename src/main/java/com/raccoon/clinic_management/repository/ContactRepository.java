package com.raccoon.clinic_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raccoon.clinic_management.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
