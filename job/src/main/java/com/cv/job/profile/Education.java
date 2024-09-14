package com.cv.job.profile;

import com.cv.job.models.Candidate;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Education{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
