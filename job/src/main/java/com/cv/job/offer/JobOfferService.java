package com.cv.job.offer;


import com.cv.job.common.PageResponse;
import com.cv.job.models.Employer;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cv.job.offer.OfferSpecification.withPublisherId;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository offerRepository;
    private final JobOfferMapper offerMapper;

    public Integer save(JobOfferRequest request, Authentication connectedUser) {
        Employer employer = ((Employer) connectedUser.getPrincipal());
        System.out.println("Employer ID: " + employer.getId());
        JobOffer offer = offerMapper.toOffer(request);
        offer.setPublisher(employer);
        return offerRepository.save(offer).getId();
    }

    public OfferResponse findById(Integer offerId) {
        return offerRepository.findById(offerId)
                .map(offerMapper::toOfferResponse)
                .orElseThrow(()->new EntityNotFoundException("No offer found with the Id: " + offerId));
    }

    public PageResponse<OfferResponse> findAllOffers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<JobOffer> offers = offerRepository.findAllDisplayableOffers(pageable);
        List<OfferResponse> offerResponse = offers.stream()
                .map(offerMapper::toOfferResponse)
                .toList();
        return new PageResponse<>(
                offerResponse,
                offers.getNumber(),
                offers.getSize(),
                offers.getTotalElements(),
                offers.getTotalPages(),
                offers.isFirst(),
                offers.isLast()
        );
    }

    public PageResponse<OfferResponse> findAllOffersByPublisher(int page, int size, Authentication connectedUser) {
        Employer employer = ((Employer) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<JobOffer> offers = offerRepository.findAll(withPublisherId(employer.getId()), pageable);
        List<OfferResponse> offerResponse = offers.stream()
                .map(offerMapper::toOfferResponse)
                .toList();
        return new PageResponse<>(
                offerResponse,
                offers.getNumber(),
                offers.getSize(),
                offers.getTotalElements(),
                offers.getTotalPages(),
                offers.isFirst(),
                offers.isLast()
        );
    }

    public Integer updateConfirmedStatus(Integer offerId) {
        JobOffer offer = offerRepository.findById(offerId)
                .orElseThrow(()-> new EntityNotFoundException("No offer found with the Id: " + offerId));
        offer.setConfirmed(!offer.isConfirmed());
        offerRepository.save(offer);
        return offerId;
    }

    public PageResponse<OfferResponse> searchOffers(String query,int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<JobOffer> offers = offerRepository.searchOffers(query, pageable);
        List<OfferResponse> offerResponse = offers.stream()
                .map(offerMapper::toOfferResponse)
                .toList();
        return new PageResponse<>(
                offerResponse,
                offers.getNumber(),
                offers.getSize(),
                offers.getTotalElements(),
                offers.getTotalPages(),
                offers.isFirst(),
                offers.isLast()
        );
    }
}
