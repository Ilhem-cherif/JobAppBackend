package com.cv.job.controllers;


import com.cv.job.dto.*;
import com.cv.job.services.AuthenticationService;
import com.cv.job.services.RegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final RegistrationService service;
    private final AuthenticationService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> register(
            @RequestBody @Valid RegistrationDto registrationDto
            ) throws MessagingException {
        return ResponseEntity.ok(service.register(registrationDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationDto request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        authService.activateAccount(token);
    }
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfileByCandidate(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(authService.getProfileByCandidate(connectedUser));
    }
    @GetMapping("/entreprise-profile")
    public ResponseEntity<EntrepriseProfileResponse> getProfileByEmployer(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(authService.getProfileByEmployer(connectedUser));
    }
}
