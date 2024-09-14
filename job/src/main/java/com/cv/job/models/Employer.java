package com.cv.job.models;


import com.cv.job.offer.JobOffer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employers")
public class Employer extends ApplicationUser{

    private String entrepriseName;
    private String entrepriseDescription;
    private String entrepriseLogo;
    private String entrepriseWebsite;
    private String entrepriseLocation;
    @OneToMany(mappedBy = "publisher")
    private List<JobOffer> offers;

}
