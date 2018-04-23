package ru.sberbank.final_task.babbler.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User findByLogin(String login);

    User save(UserRegistrationDto registration);

    User save(User user);
}
