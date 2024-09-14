package com.cv.job.applications;

import com.cv.job.models.Candidate;
import com.cv.job.positions.Position;
import com.cv.job.positions.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.cv.job.applications.ApplicationSpecification.withCandidateId;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final PositionRepository positionRepository;
    private final ApplicationMapper applicationMapper;


    public Integer saveApplication(MultipartFile file, Authentication connectedUser, Integer positionId) throws IOException {

        Candidate candidate = ((Candidate) connectedUser.getPrincipal());

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        Application application = applicationMapper.toApplication(candidate,position);
        application.setCv(file.getBytes());  // Save CV as byte[]
        application.setFileName(file.getOriginalFilename());
        application.setFileType(file.getContentType());

        applicationRepository.save(application);
        return application.getId();
    }
    // Fetching the Application and returning the ApplicationResponse
    public ApplicationResponse getApplicationById(Integer id) {
        return applicationRepository.findById(id)
                .map(applicationMapper::toApplicationResponse)
                .orElse(null);
    }

    public List<ApplicationResponse> findAllApplicationByCandidate(Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        List<Application> applications = applicationRepository.findAll(withCandidateId(candidate.getId()));
        return applications.stream()
                .map(applicationMapper::toApplicationResponse)
                .toList();
    }
    @Transactional(readOnly = true)
    public List<ApplicationResponse> findApplicationsByPosition(Integer positionId) {
        List<Application> applications = applicationRepository.findAllByPositionId(positionId);
        return applications.stream()
                .map(applicationMapper::toApplicationResponse)
                .toList();
    }

    @Transactional
    public void updateApplicationStatus(Integer applicationId, Status status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);  // Setting the enum value
        applicationRepository.save(application);
    }

}
