package ru.sberbank.final_task.babbler.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

import java.time.LocalDateTime;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User findByLogin(String login);

    User findById(Long id);

    User save(UserRegistrationDto userRegistrationDto);

    User updateUser(UserRegistrationDto userRegistrationDto);

    void updateLastSeen(String email, LocalDateTime lastSeen, String status);

    User save(User user);
}
