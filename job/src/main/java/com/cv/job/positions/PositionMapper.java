package com.cv.job.positions;

import com.cv.job.offer.JobOffer;
import org.springframework.stereotype.Service;

@Service
public class PositionMapper {


    public Position toPosition(PositionDto request) {

        return Position.builder()
                .id(request.id())
                .title(request.title())
                .description(request.description())
                .requirements(request.requirements())
                .skills(request.skills())
                .location(request.Location())
                .salary(request.salary())
                .offer(JobOffer.builder()
                        .id(request.offerId())
                        .isConfirmed(false)
                        .build()
                )
                .build();
    }

    public PositionResponse toPositionResponse(Position position) {
        return PositionResponse.builder()
                .id(position.getId())
                .title(position.getTitle())
                .description(position.getDescription())
                .requirements(position.getRequirements())
                .skills(position.getSkills())
                .location(position.getLocation())
                .salary(position.getSalary())
                .offerId(position.getOffer().getId())
                .build();
    }
}
