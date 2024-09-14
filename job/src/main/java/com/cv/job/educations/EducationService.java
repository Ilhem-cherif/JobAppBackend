package com.cv.job.educations;


import com.cv.job.models.Candidate;
import com.cv.job.profile.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cv.job.educations.EducationSpecification.withCandidateId;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

    public Integer save(EducationRequest request, Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        Education education = educationMapper.toEducation(request);
        education.setCandidate(candidate);
        return educationRepository.save(education).getId();
    }

    public List<EducationResponse> findAllEducationByCandidate(Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        List<Education> educations = educationRepository.findAll(withCandidateId(candidate.getId()));
        return educations.stream()
                .map(educationMapper::toEducationResponse)
                .toList();
    }
}
