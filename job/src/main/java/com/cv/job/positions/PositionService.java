package com.cv.job.positions;

import com.cv.job.offer.JobOffer;
import com.cv.job.offer.JobOfferRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final JobOfferRepository offerRepository;
    private final PositionMapper positionMapper;
    private final PositionRepository positionRepository;

    public Integer save(PositionDto request) {
        JobOffer offer = offerRepository.findById(request.offerId())
                .orElseThrow(()-> new EntityNotFoundException(("No offer found with the Id: " + request.offerId())));
        if (offer.isConfirmed()){
            throw new IllegalStateException("You cannot add a position. The offer is confirmed.");
        }
        Position position = positionMapper.toPosition(request);
        return positionRepository.save(position).getId();
    }

    public List<PositionResponse> findAllPositionsByOffer(Integer offerId) {
        List<Position> positions = positionRepository.findAllByOfferId(offerId);
        return positions.stream()
                .map(positionMapper::toPositionResponse)
                .toList();
    }
    public PositionResponse findById(Integer positionId) {
        return positionRepository.findById(positionId)
                .map(positionMapper::toPositionResponse)
                .orElseThrow(()->new EntityNotFoundException("No position found with the Id: " + positionId));
    }
}

