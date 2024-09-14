package com.cv.job.experiences;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
@RequiredArgsConstructor
@Tag(name = "Experience")
public class ExperienceController {

    private final ExperienceService service;

    @PostMapping
    public ResponseEntity<Integer> saveExperience(
            @Valid @RequestBody ExperienceRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/candidate")
    public ResponseEntity<List<ExperienceResponse>> getAllExperienceByCandidate(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllExperienceByCandidate(connectedUser));
    }

}
