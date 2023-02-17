package com.example.smartcity.Authentication;

import com.example.smartcity.User.Role;
import com.example.smartcity.User.User;
import com.example.smartcity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountCreated(new Date(System.currentTimeMillis()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        context.setAuthentication(authenticate);
        SecurityContextHolder.setContext(context);
        System.out.println(SecurityContextHolder.getContext());

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        System.out.println(SecurityContextHolder.getContext());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public void logout() {
        System.out.println(SecurityContextHolder.getContext());
        SecurityContextHolder.clearContext();
        System.out.println(SecurityContextHolder.getContext());
    }
}
