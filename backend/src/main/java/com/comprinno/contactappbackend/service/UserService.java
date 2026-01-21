package com.comprinno.contactappbackend.service;

import com.comprinno.contactappbackend.dto.UserDTO;
import com.comprinno.contactappbackend.entity.User;
import com.comprinno.contactappbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register new user
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // In production, hash the password

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    // Login user
    public UserDTO loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        return convertToDTO(user);
    }

    // Update user
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    // Get user by ID
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    // Convert Entity to DTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(null); // Don't send password in response
        return dto;
    }

    // ================= PROFILE =================

    // Get profile of logged-in user
    public UserDTO getProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToDTO(user);
    }

    // Update profile of logged-in user
    public UserDTO updateProfile(Long userId, UserDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // name can be updated
        user.setName(dto.getName());

        // password optional
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword()); // later we can hash
        }

        User updated = userRepository.save(user);
        return convertToDTO(updated);
    }


}