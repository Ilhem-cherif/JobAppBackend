package com.cv.job.educations;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educations")
@RequiredArgsConstructor
@Tag(name = "Education")
public class EducationController {

    private final EducationService service;

    @PostMapping
    public ResponseEntity<Integer> saveEducation(
            @Valid @RequestBody EducationRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/candidate")
    public ResponseEntity<List<EducationResponse>> getAllEducationByCandidate(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllEducationByCandidate(connectedUser));
    }
}
