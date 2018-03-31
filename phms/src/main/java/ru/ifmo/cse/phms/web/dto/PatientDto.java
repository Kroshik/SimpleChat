package ru.ifmo.cse.phms.web.dto;

import lombok.Data;
import ru.ifmo.cse.phms.domain.therapy.Patient;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PatientDto {

    @NotEmpty
    private String fullName;

    @Max(120)
    @Min(0)
    @NotNull
    private Integer age;

    @NotNull
    private Date birthDate;

    @NotNull
    private Date arrivalDate;

    @NotEmpty
    private String building;

    private String diagnosis;

    public Patient toPatient() {
        return Patient.builder()
                .fullName(fullName)
                .age(age)
                .birthDate(birthDate)
                .arrivalDate(arrivalDate)
                .building(building)
                .diagnosis(diagnosis)
                .build();
    }

}
