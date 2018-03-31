package ru.ifmo.cse.phms.service;

import ru.ifmo.cse.phms.domain.therapy.Patient;

import java.util.List;

public interface TherapyService {
    Patient getPatient(Long patientId);

    List<Patient> getPatients();

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);

    void mockData();

    void mockOnePatient(int count, String building);
}
