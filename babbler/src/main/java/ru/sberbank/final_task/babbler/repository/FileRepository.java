package ru.sberbank.final_task.babbler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.final_task.babbler.domain.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
//    File findByMessage(Message message);
}
