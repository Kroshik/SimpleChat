package ru.ifmo.cse.phms.domain.therapy;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String fullName;

    @NonNull
    @Max(120)
    @Min(0)
    private Integer age;

    @NonNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NonNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    @NonNull
    private String building;

    private String diagnosis;

    @NonNull
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "patient")
    @OrderBy("created_at asc")
    private List<Condition> conditionHistory = new ArrayList<>();

    @NonNull
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "patient")
    @OrderBy("start_date desc")
    private List<Experiment> experiments = new ArrayList<>();
}
