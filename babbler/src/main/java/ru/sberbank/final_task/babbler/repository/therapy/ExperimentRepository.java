package ru.sberbank.final_task.babbler.repository.therapy;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.final_task.babbler.domain.therapy.Experiment;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long> {

}
