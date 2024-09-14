package com.cv.job.offer;


import org.springframework.stereotype.Service;

@Service
public class JobOfferMapper {


    public JobOffer toOffer(JobOfferRequest request) {

        return JobOffer.builder()
                .id(request.id())
                .title(request.title())
                .description(request.description())
                .isConfirmed(request.isConfirmed())
                .build();
    }

    public OfferResponse toOfferResponse(JobOffer jobOffer) {

        return OfferResponse.builder()
                .id(jobOffer.getId())
                .title(jobOffer.getTitle())
                .description(jobOffer.getDescription())
                .isConfirmed(jobOffer.isConfirmed())
                .publisher(jobOffer.getPublisher().fullName())
                .companyName(jobOffer.getPublisher().getEntrepriseName())
                .companyDescription(jobOffer.getPublisher().getEntrepriseDescription())
                .companyLocation(jobOffer.getPublisher().getEntrepriseLocation())
                .companyWebSite(jobOffer.getPublisher().getEntrepriseWebsite())
                .build();
    }

}
