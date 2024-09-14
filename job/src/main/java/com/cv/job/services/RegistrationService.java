package com.cv.job.services;

import com.cv.job.dto.RegistrationDto;
import com.cv.job.email.EmailService;
import com.cv.job.email.EmailTemplateName;
import com.cv.job.models.*;
import com.cv.job.repositories.CandidateRepository;
import com.cv.job.repositories.EmployerRepository;
import com.cv.job.repositories.TokenRepository;
import com.cv.job.repositories.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final CandidateRepository candidateRepository;
    private final EmployerRepository employerRepository;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public String register(RegistrationDto registrationDto) throws MessagingException {
        // Check if the user already exists
        boolean userExists = userRepository.findByEmail(registrationDto.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("A user already exists with the same email");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        // Handle role-specific fields
        ApplicationUser applicationUser;
        if (registrationDto.getRole() == UserRole.CANDIDATE) {
            Candidate candidate = Candidate.builder()
                    .firstname(registrationDto.getFirstname())
                    .lastname(registrationDto.getLastname())
                    .email(registrationDto.getEmail())
                    .password(encodedPassword)
                    .locked(false)
                    .enabled(false)
                    .role(registrationDto.getRole())
                    .personalResume(registrationDto.getPersonalResume())
                    .phoneNumber(registrationDto.getPhoneNumber())
                    .age(registrationDto.getAge())
                    .photo(registrationDto.getPhoto())
                    .build();
            applicationUser = candidateRepository.save(candidate);
        } else if (registrationDto.getRole() == UserRole.EMPLOYER) {
            Employer employer = Employer.builder()
                    .firstname(registrationDto.getFirstname())
                    .lastname(registrationDto.getLastname())
                    .email(registrationDto.getEmail())
                    .password(encodedPassword)
                    .locked(false)
                    .enabled(false)
                    .role(registrationDto.getRole())
                    .entrepriseName(registrationDto.getEntrepriseName())
                    .entrepriseDescription(registrationDto.getEntrepriseDescription())
                    .entrepriseLocation(registrationDto.getEntrepriseLocation())
                    .entrepriseWebsite(registrationDto.getEntrepriseWebsite())
                    .entrepriseLogo(registrationDto.getEntrepriseLogo())
                    .build();
            applicationUser = employerRepository.save(employer);
        } else {
            throw new IllegalStateException("Invalid role specified");
        }

        // Send validation email
        sendValidationEmail(applicationUser);

        // Return success message
        return "User has been successfully created";
    }

    public void sendValidationEmail(ApplicationUser user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        // Send email
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(ApplicationUser user) {
        // Generate a token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length()); //0..9
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
