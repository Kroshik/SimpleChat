package ru.ifmo.cse.phms.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.cse.phms.domain.auth.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
