package com.cv.job.applications;

import com.cv.job.file.FileUtils;
import com.cv.job.models.Candidate;
import com.cv.job.positions.Position;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {
    public Application toApplication(Candidate candidate, Position position) {

        return Application.builder()
                .candidate(candidate)
                .position(position)
                .status(Status.InProgress)
                .build();
    }
    public ApplicationResponse toApplicationResponse(Application application){

       return ApplicationResponse.builder()
               .id(application.getId())
               .status(application.getStatus().name())
               .submittedDate(application.getSubmittedAt().toString())
               .cv(application.getCv())
               .cvName(application.getFileName())
               .positionName(application.getPosition().getTitle())
               .candidateId(application.getCandidate().getId())
               .build();
    }
}
