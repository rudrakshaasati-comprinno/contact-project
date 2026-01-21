package com.comprinno.contactappbackend.controller;

import com.comprinno.contactappbackend.dto.ContactDTO;
import com.comprinno.contactappbackend.service.ContactService;
import com.comprinno.contactappbackend.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private static final Logger logger =
            LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private JwtUtil jwtUtil;

    // ================= ADD CONTACT =================
    @PostMapping
    public ResponseEntity<?> addContact(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ContactDTO contactDTO) {

        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(
                contactService.addContact(contactDTO, userId)
        );
    }


    // ================= GET ALL CONTACTS =================
    @GetMapping
    public ResponseEntity<?> getAllContacts(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);

        Pageable pageable = PageRequest.of(page, size);
        Page<ContactDTO> contacts =
                contactService.getAllContacts(userId, pageable);

        return ResponseEntity.ok(contacts);
    }


    // ================= GET CONTACT BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id) {

        Long userId = jwtUtil.extractUserId(authHeader);

        return ResponseEntity.ok(
                contactService.getContactById(id, userId)
        );
    }

    // ================= UPDATE CONTACT =================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id,
            @RequestBody ContactDTO contactDTO) {

        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(
                contactService.updateContact(id, contactDTO, userId)
        );
    }


    // ================= DELETE CONTACT =================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id) {

        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);

        contactService.deleteContact(id, userId);

        return ResponseEntity.ok("Contact deleted successfully");
    }

}
