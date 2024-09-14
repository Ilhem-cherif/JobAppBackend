package com.cv.job.profile;

import com.cv.job.models.Candidate;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String level;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
