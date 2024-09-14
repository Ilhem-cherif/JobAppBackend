package com.cv.job.skills;


import com.cv.job.educations.EducationRequest;
import com.cv.job.educations.EducationResponse;
import com.cv.job.educations.EducationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
@Tag(name = "Skill")
public class SkillController {

    private final SkillService service;

    @PostMapping
    public ResponseEntity<Integer> saveSkill(
            @Valid @RequestBody SkillRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/candidate")
    public ResponseEntity<List<SkillResponse>> getAllSkillsByCandidate(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllSkillsByCandidate(connectedUser));
    }
}
