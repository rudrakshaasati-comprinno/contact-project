package com.comprinno.contactappbackend.controller;

import com.comprinno.contactappbackend.dto.UserDTO;
import com.comprinno.contactappbackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.comprinno.contactappbackend.util.JwtUtil;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

        System.out.println("🟡 STEP-1: REGISTER API HIT");
        System.out.println("🟡 Request Body = " + userDTO);

        logger.info("========== REGISTER API CALLED ==========");

        try {
            System.out.println("🟡 STEP-2: Calling userService.registerUser()");
            UserDTO registeredUser =
                    userService.registerUser(userDTO);

            System.out.println("🟢 STEP-3: User registered successfully");
            System.out.println("🟢 Registered User = " + registeredUser);

            return ResponseEntity.ok(registeredUser);

        } catch (RuntimeException e) {
            System.out.println("🔴 STEP-ERROR: Exception in registerUser()");
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {

        System.out.println("🟡 STEP-1: LOGIN API HIT");
        System.out.println("🟡 Email = " + userDTO.getEmail());

        logger.info("========== LOGIN API CALLED ==========");

        try {
            System.out.println("🟡 STEP-2: Calling userService.loginUser()");
            UserDTO loggedInUser =
                    userService.loginUser(
                            userDTO.getEmail(),
                            userDTO.getPassword()
                    );

            System.out.println("🟢 STEP-3: Login successful");

            // ✅ STEP-4: Generate JWT token
            String token = jwtUtil.generateToken(loggedInUser.getId());



            // ✅ STEP-5: Build response with token
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", loggedInUser);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            System.out.println("🔴 STEP-ERROR: Exception in loginUser()");
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }


    // ================= UPDATE USER =================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {

        System.out.println("🟡 STEP-1: UPDATE USER API HIT");
        System.out.println("🟡 userId = " + id);
        System.out.println("🟡 Request Body = " + userDTO);

        try {
            System.out.println("🟡 STEP-2: Calling userService.updateUser()");
            UserDTO updatedUser =
                    userService.updateUser(id, userDTO);

            System.out.println("🟢 STEP-3: Update successful");
            System.out.println("🟢 Updated User = " + updatedUser);

            return ResponseEntity.ok(updatedUser);

        } catch (RuntimeException e) {
            System.out.println("🔴 STEP-ERROR: Exception in updateUser()");
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ================= GET USER BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        System.out.println("🟡 STEP-1: GET USER BY ID API HIT");
        System.out.println("🟡 userId = " + id);

        try {
            System.out.println("🟡 STEP-2: Calling userService.getUserById()");
            UserDTO user =
                    userService.getUserById(id);

            System.out.println("🟢 STEP-3: User found");
            System.out.println("🟢 User = " + user);

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            System.out.println("🔴 STEP-ERROR: Exception in getUserById()");
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(
            @RequestHeader("Authorization") String authHeader) {

        Long userId = jwtUtil.extractUserId(authHeader);
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody UserDTO dto) {

        System.out.println("🔵 UPDATE PROFILE API HIT");
        System.out.println("🔵 Name = " + dto.getName());
        System.out.println("🔵 Password = " + dto.getPassword());

        Long userId = jwtUtil.extractUserId(authHeader);
        System.out.println("🔵 userId from token = " + userId);

        return ResponseEntity.ok(
                userService.updateProfile(userId, dto)
        );
    }




}