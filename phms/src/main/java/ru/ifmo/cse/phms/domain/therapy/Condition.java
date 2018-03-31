package ru.ifmo.cse.phms.domain.therapy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Condition {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Max(10)
    @Min(0)
    private Integer mental;

    @NonNull
    @Max(10)
    @Min(0)
    private Integer physical;

    @Builder.Default
    @JsonFormat(pattern="dd.mm.yy hh:MM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
