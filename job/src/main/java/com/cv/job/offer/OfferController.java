package com.cv.job.offer;

import com.cv.job.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("offers")
@RequiredArgsConstructor
@Tag(name = "JobOffer")
public class OfferController {

    private final JobOfferService service;

    @PostMapping
    public ResponseEntity<Integer> saveOffer(
            @Valid @RequestBody JobOfferRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("{offer-id}")
    public ResponseEntity<OfferResponse> findOfferById(
            @PathVariable("offer-id") Integer offerId
    ){
        return ResponseEntity.ok(service.findById(offerId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<OfferResponse>> findAllOffers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.findAllOffers(page, size));
    }

    @GetMapping("/publisher")
    public ResponseEntity<PageResponse<OfferResponse>> findAllOffersByPublisher(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllOffersByPublisher(page, size, connectedUser));
    }

    @PatchMapping("/confirmed/{offer-id}")
    public ResponseEntity<Integer> updateConfirmedStatus(
            @PathVariable("offer-id") Integer offerId
    ){
        return ResponseEntity.ok(service.updateConfirmedStatus(offerId));
    }
    @GetMapping("/search")
    public ResponseEntity<PageResponse<OfferResponse>> searchOffers(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.searchOffers(query, page, size));
    }
}
