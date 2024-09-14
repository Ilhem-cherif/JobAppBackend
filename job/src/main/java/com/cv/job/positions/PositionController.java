package com.cv.job.positions;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("positions")
@RequiredArgsConstructor
@Tag(name = "Position")
public class PositionController {

    private final PositionService service;

    @PostMapping
    public ResponseEntity<Integer> savePosition(
            @Valid @RequestBody PositionDto request
    ){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/offer/{offer-id}")
    public ResponseEntity<List<PositionResponse>> findAllPositionsByOffer(
            @PathVariable("offer-id") Integer offerId
    ){
        return ResponseEntity.ok(service.findAllPositionsByOffer(offerId));
    }
    @GetMapping("{position-id}")
    public ResponseEntity<PositionResponse> findPositionById(
            @PathVariable("position-id") Integer positionId
    ){
        return ResponseEntity.ok(service.findById(positionId));
    }
}
