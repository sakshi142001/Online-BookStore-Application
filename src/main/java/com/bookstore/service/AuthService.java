package com.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bookstore.config.JwtUtil;
import com.bookstore.dto.AuthResponseDTO;
import com.bookstore.dto.LoginRequest;
import com.bookstore.dto.RegisterRequest;
import com.bookstore.dto.UserResponseDTO;
import com.bookstore.entity.User;
import com.bookstore.repository.UserRepo;
import com.bookstore.entity.Role;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepo userRepo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    // DTO Conversion
    public UserResponseDTO convertToDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }

    // REGISTER
    public String register(RegisterRequest request) {

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        // SET ROLE
        user.setRole(Role.USER);

        userRepo.save(user);

        return "User registered successfully";
    }

    // LOGIN
    public AuthResponseDTO login(LoginRequest req) {

        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

    
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name());

        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return new AuthResponseDTO(token, userDTO);
    }
}
