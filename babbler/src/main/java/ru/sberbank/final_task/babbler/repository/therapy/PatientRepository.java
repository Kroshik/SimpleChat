package ru.sberbank.final_task.babbler.repository.therapy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.final_task.babbler.domain.therapy.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
