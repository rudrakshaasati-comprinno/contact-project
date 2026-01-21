package com.comprinno.contactappbackend.repository;

import com.comprinno.contactappbackend.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // get all contacts of logged-in user
    Page<Contact> findByUser_Id(Long userId, Pageable pageable);

    // get single contact (secure)
    Optional<Contact> findByIdAndUser_Id(Long id, Long userId);

    // delete contact (secure)
    void deleteByIdAndUser_Id(Long id, Long userId);
}
