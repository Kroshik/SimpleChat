package ru.ifmo.cse.phms.repository.therapy;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.cse.phms.domain.therapy.Experiment;

@Repository
public interface ConditionRepository extends JpaRepository<Experiment, Long> {

}
