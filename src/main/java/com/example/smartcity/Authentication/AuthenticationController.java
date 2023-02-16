package com.example.smartcity.Authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("Authentication Incoming.");
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        authenticationService.logout();
        return ResponseEntity.ok("Logout");
    }


}
