package ru.ifmo.cse.phms.repository.therapy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.cse.phms.domain.therapy.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
