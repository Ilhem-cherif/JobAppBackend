package com.cv.job.skills;


import com.cv.job.models.Candidate;
import com.cv.job.profile.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cv.job.skills.SkillSpecification.withCandidateId;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public Integer save(SkillRequest request, Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        Skill skill = skillMapper.toskill(request);
        skill.setCandidate(candidate);
        return skillRepository.save(skill).getId();
    }

    public List<SkillResponse> findAllSkillsByCandidate(Authentication connectedUser) {
        Candidate candidate = ((Candidate) connectedUser.getPrincipal());
        List<Skill> skills = skillRepository.findAll(withCandidateId(candidate.getId()));
        return skills.stream()
                .map(skillMapper::toSkillResponse)
                .toList();
    }
}
