package ru.ifmo.cse.phms.domain.therapy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Experiment {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String description;

    @Builder.Default
    @Max(100)
    @Min(0)
    private Integer successRate = 0;

    @NonNull
    @JsonFormat(pattern="dd.mm.yy hh:MM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @JsonFormat(pattern="dd.mm.yy hh:MM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
