package com.cv.job.models;

import com.cv.job.applications.Application;
import com.cv.job.profile.Education;
import com.cv.job.profile.Experience;
import com.cv.job.profile.Skill;
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
@Table(name = "Candidates")
public class Candidate extends ApplicationUser{

    private String personalResume;
    private String phoneNumber;
    private int age;
    private String photo;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;

    @OneToMany(mappedBy = "candidate")
    private List<Education> educations;

    @OneToMany(mappedBy = "candidate")
    private List<Skill> skills;

    @OneToMany(mappedBy = "candidate")
    private List<Experience> experiences;
}
