package com.cv.job.experiences;


import com.cv.job.models.Candidate;
import com.cv.job.profile.Experience;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cv.job.experiences.ExperienceSpecification.withCandidateId;


@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;

    public Integer save(ExperienceRequest request, Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        Experience experience = experienceMapper.toExperience(request);
        experience.setCandidate(candidate);
        return experienceRepository.save(experience).getId();
    }

    public List<ExperienceResponse> findAllExperienceByCandidate(Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        List<Experience> experiences = experienceRepository.findAll(withCandidateId(candidate.getId()));
        return experiences.stream()
                .map(experienceMapper::toExperienceResponse)
                .toList();
    }
}
