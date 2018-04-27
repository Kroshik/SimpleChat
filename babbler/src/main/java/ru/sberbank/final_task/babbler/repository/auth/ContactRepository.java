package ru.sberbank.final_task.babbler.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.final_task.babbler.domain.Contact;

@Repository
@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
