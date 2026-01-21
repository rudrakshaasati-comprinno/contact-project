package com.comprinno.contactappbackend.service;

import com.comprinno.contactappbackend.dto.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    ContactDTO addContact(ContactDTO dto, Long userId);

    Page<ContactDTO> getAllContacts(Long userId, Pageable pageable);

    ContactDTO getContactById(Long id, Long userId);

    ContactDTO updateContact(Long id, ContactDTO dto, Long userId);


    void deleteContact(Long id, Long userId);
}
