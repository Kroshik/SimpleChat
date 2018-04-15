package ru.sberbank.final_task.babbler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.final_task.babbler.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
//    Message findByIdFrom(Long id);
}
