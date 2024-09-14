package com.cv.job.applications;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@Tag(name = "Application")
public class ApplicationController {

    private final ApplicationService service;

    @PostMapping(value = "/cv/{position-id}", consumes = "multipart/form-data")
    public ResponseEntity<Integer> saveApplication(
            @PathVariable("position-id") Integer positionId,
            @RequestPart("file") MultipartFile file,
            @Parameter()
            Authentication connectedUser
    ) throws IOException {
        return ResponseEntity.ok(service.saveApplication(file, connectedUser, positionId));
    }
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadCv(@PathVariable Integer id) {
        ApplicationResponse applicationResponse = service.getApplicationById(id);

        if (applicationResponse == null || applicationResponse.getCv() == null) {
            return ResponseEntity.notFound().build();
        }
        String fileName = applicationResponse.getCvName();
        if (fileName == null || fileName.isEmpty()) {
            fileName = "cv_" + id + ".pdf";
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(applicationResponse.getCv());
    }
    @GetMapping("/candidateApp" )
    public ResponseEntity<List<ApplicationResponse>> findAllApplicationByCandidate(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllApplicationByCandidate(connectedUser));
    }
    @GetMapping("/allApps/{position-id}")
    public ResponseEntity<List<ApplicationResponse>> findApplicationsByPosition(
            @PathVariable("position-id") Integer positionId
    ){
        return ResponseEntity.ok(service.findApplicationsByPosition(positionId));
    }
    @PatchMapping("/updateStatus/{applicationId}")
    public ResponseEntity<Void> updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestBody String status) {

        Status statusEnum = Status.valueOf(status); // Convert string to Status enum
        service.updateApplicationStatus(applicationId, statusEnum);
        return ResponseEntity.noContent().build();
    }

}
