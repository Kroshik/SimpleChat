package ru.sberbank.final_task.babbler.service;

import ru.sberbank.final_task.babbler.domain.therapy.Patient;

import java.util.List;

public interface TherapyService {
    Patient getPatient(Long patientId);

    List<Patient> getPatients();

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);

    void mockData();

    void mockOnePatient(int count, String building);
}
