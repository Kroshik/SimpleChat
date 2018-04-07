package ru.sberbank.final_task.babbler.service.internal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.therapy.Condition;
import ru.sberbank.final_task.babbler.domain.therapy.Experiment;
import ru.sberbank.final_task.babbler.domain.therapy.Patient;
import ru.sberbank.final_task.babbler.repository.therapy.ConditionRepository;
import ru.sberbank.final_task.babbler.repository.therapy.ExperimentRepository;
import ru.sberbank.final_task.babbler.repository.therapy.PatientRepository;
import ru.sberbank.final_task.babbler.service.TherapyService;
import ru.sberbank.final_task.babbler.util.DateUtils;
import ru.sberbank.final_task.babbler.util.RandomUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TherapyServiceImpl implements TherapyService {
    @NonNull
    private PatientRepository patientRepository;

    @NonNull
    private ExperimentRepository experimentRepository;

    @NonNull
    private ConditionRepository conditionRepository;

    @Override
    public Patient getPatient(Long patientId) {
        return patientRepository.getOne(patientId);
    }

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        val existing = patientRepository.getOne(patient.getId());
        if (existing != null) {
            patient.setExperiments(existing.getExperiments());
            patient.setConditionHistory(existing.getConditionHistory());
        }
        return patientRepository.save(patient);
    }

    @Override
    public void mockData() {
        patientRepository.deleteAllInBatch();
        conditionRepository.deleteAllInBatch();
        experimentRepository.deleteAllInBatch();
        for (int i = 1; i <= 10; i += 4) {
            mockOnePatient(i, "1");
            mockOnePatient(i + 1, "2");
            mockOnePatient(i + 2, "3");
            mockOnePatient(i + 3, "4");
        }
    }

    @Override
    public void mockOnePatient(int count, String building) {
        val dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            val patient = Patient.builder()
                    .fullName("Test subject " + count)
                    .age(10)
                    .arrivalDate(new Date())
                    .birthDate(dateFormat.parse("07.07.1992"))
                    .building(building)
                    .build();

            patient.getExperiments().add(Experiment.builder()
                    .description("Test some stuff for patient " + count)
                    .successRate(45)
                    .startDate(DateUtils.nowMinusDays(RandomUtils.intBetween(30, 50)))
                    .endDate(DateUtils.nowMinusDays(RandomUtils.intBetween(10, 20)))
                    .patient(patient)
                    .build());


            patient.getExperiments().add(Experiment.builder()
                    .description("Test some other stuff for patient " + count)
                    .successRate(10)
                    .startDate(DateUtils.nowMinusDays(RandomUtils.intBetween(30, 50)))
                    .endDate(DateUtils.nowMinusDays(RandomUtils.intBetween(10, 20)))
                    .patient(patient)
                    .build());

            patient.getExperiments().add(Experiment.builder()
                    .description("Test 42 for patient " + count)
                    .successRate(42)
                    .startDate(DateUtils.nowMinusDays(RandomUtils.intBetween(30, 50)))
                    .endDate(DateUtils.nowMinusDays(RandomUtils.intBetween(10, 20)))
                    .patient(patient)
                    .build());

            patient.getExperiments().add(Experiment.builder()
                    .description("Ongoing experiment for patient  " + count)
                    .startDate(DateUtils.nowMinusDays(RandomUtils.intBetween(10, 15)))
                    .patient(patient)
                    .build());

            Date conditionMeasurementDate = DateUtils.nowMinusDays(RandomUtils.intBetween(10,20));

            for (int i = 0; i < 50; i++) {
                patient.getConditionHistory().add(Condition.builder()
                        .mental(RandomUtils.intBetween(1,10))
                        .physical(RandomUtils.intBetween(1,10))
                        .createdAt(conditionMeasurementDate)
                        .patient(patient)
                        .build());
                conditionMeasurementDate = DateUtils.datePlusHours(conditionMeasurementDate, 24);
            }


            patientRepository.save(patient);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
