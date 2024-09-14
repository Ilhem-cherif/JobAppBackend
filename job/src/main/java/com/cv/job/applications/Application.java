package com.cv.job.applications;


import com.cv.job.models.Candidate;
import com.cv.job.positions.Position;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt;
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.InProgress;
    @Lob
    @Column(name = "cv", nullable = true)
    @Basic(fetch = FetchType.EAGER)
    private byte[] cv;
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

}
