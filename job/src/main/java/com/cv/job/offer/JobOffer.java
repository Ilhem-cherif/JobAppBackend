package com.cv.job.offer;


import com.cv.job.common.BaseEntity;
import com.cv.job.models.Employer;
import com.cv.job.positions.Position;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobOffer extends BaseEntity {
    private boolean isConfirmed;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer publisher;
    @OneToMany(mappedBy = "offer")
    private List<Position> positions;
}
