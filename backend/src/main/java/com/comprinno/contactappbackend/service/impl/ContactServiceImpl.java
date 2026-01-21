package com.comprinno.contactappbackend.service.impl;

import com.comprinno.contactappbackend.dto.ContactDTO;
import com.comprinno.contactappbackend.entity.Contact;
import com.comprinno.contactappbackend.entity.User;
import com.comprinno.contactappbackend.repository.ContactRepository;
import com.comprinno.contactappbackend.repository.UserRepository;
import com.comprinno.contactappbackend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ContactDTO addContact(ContactDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        contact.setUser(user);

        return mapToDTO(contactRepository.save(contact));
    }

    @Override
    public Page<ContactDTO> getAllContacts(Long userId, Pageable pageable) {
        return contactRepository
                .findByUser_Id(userId, pageable)
                .map(this::mapToDTO);
    }

    @Override
    public ContactDTO getContactById(Long id, Long userId) {
        Contact contact = contactRepository
                .findByIdAndUser_Id(id, userId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        return mapToDTO(contact);
    }

    @Override
    public ContactDTO updateContact(Long id, ContactDTO dto, Long userId) {
        Contact contact = contactRepository
                .findByIdAndUser_Id(id, userId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());

        return mapToDTO(contactRepository.save(contact));
    }

    @Override
    public void deleteContact(Long id, Long userId) {

        Contact contact = contactRepository
                .findByIdAndUser_Id(id, userId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contactRepository.delete(contact);
    }

    


    private ContactDTO mapToDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        return dto;
    }
}
