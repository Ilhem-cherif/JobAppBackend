package com.cv.job.positions;


import com.cv.job.applications.Application;
import com.cv.job.common.BaseEntity;
import com.cv.job.offer.JobOffer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Position extends BaseEntity {

    private String requirements;
    private String skills;
    private double salary;
    private String location;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private JobOffer offer;
    @OneToMany(mappedBy = "position")
    private List<Application> applications;
}
