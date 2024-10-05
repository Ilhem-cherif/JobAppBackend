package com.cv.job.services;

import com.cv.job.dto.*;
import com.cv.job.models.Candidate;
import com.cv.job.models.Employer;
import com.cv.job.security.JwtService;
import com.cv.job.models.ApplicationUser;
import com.cv.job.models.Token;
import com.cv.job.repositories.TokenRepository;
import com.cv.job.repositories.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final RegistrationService service;
    private final UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationDto request) {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String,Object>();
        //cast the authentication to user
        var user = ((ApplicationUser)auth.getPrincipal());
        claims.put("fullname", user.fullName());
        //generate jwt token
        var jwtToken = jwtService.generateToken(claims, user);
        var expiredAt = jwtService.extractExpiration(jwtToken).toString();
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .role(user.getRole().name())
                .permission(user.getAuthorities().toString())
                .build();

        return AuthenticationResponse.builder()
                .status("success")
                .token(jwtToken)
                .expiredAt(expiredAt)
                .user(userDto)
                .message("Login successful")
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        //retrieve the token fom the database
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(()-> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiredAt())){
            service.sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email address");
        }
        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public ProfileResponse getProfileByCandidate(Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        return ProfileResponse.builder()
                .id(candidate.getId())
                .firstName(candidate.getFirstname())
                .lastName(candidate.getLastname())
                .personalResume(candidate.getPersonalResume())
                .phoneNumber(candidate.getPhoneNumber())
                .email(candidate.getEmail())
                .age(candidate.getAge())
                .build();
    }

    public EntrepriseProfileResponse getProfileByEmployer(Authentication connectedUser) {
        Employer employer  = ((Employer) connectedUser.getPrincipal());
        return EntrepriseProfileResponse.builder()
                .id(employer.getId())
                .entrepriseName(employer.getEntrepriseName())
                .entrepriseDescription(employer.getEntrepriseDescription())
                .entrepriseLogo(employer.getEntrepriseLogo())
                .entrepriseWebsite(employer.getEntrepriseWebsite())
                .entrepriseLocation(employer.getEntrepriseLocation())
                .build();
    }
}
