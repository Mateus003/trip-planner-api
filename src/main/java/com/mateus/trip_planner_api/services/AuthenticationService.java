package com.mateus.trip_planner_api.services;

import com.mateus.trip_planner_api.dto.AuthDTO;
import com.mateus.trip_planner_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String authenticate(AuthDTO authDTO) {
        var user = userRepository.findByEmail(authDTO.getEmail());
        if (user == null || !passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return jwtService.generateToken(user);
    }
}
