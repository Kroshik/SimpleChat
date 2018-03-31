package ru.ifmo.cse.phms.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.ifmo.cse.phms.domain.auth.User;
import ru.ifmo.cse.phms.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

    User save(User user);
}
