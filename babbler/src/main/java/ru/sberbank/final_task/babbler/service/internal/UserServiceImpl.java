package ru.sberbank.final_task.babbler.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.repository.auth.UserRepository;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    public User save(UserRegistrationDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setLastSeen(LocalDateTime.now());
        user.setStatus("online");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserRegistrationDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        String email = user.getEmail();
        String firstName = userDto.getFirstName().equals("") ? user.getFirstName() : userDto.getFirstName();
        String lastName = userDto.getLastName().equals("") ? user.getLastName() : userDto.getLastName();
        String login = userDto.getLogin().equals("") ? user.getLogin() : userDto.getLogin();
        String password = userDto.getPassword().equals("") ? user.getPassword() : passwordEncoder.encode(userDto.getPassword());
        try {
            byte[] avatar = userDto.getAvatar().isEmpty() ? user.getAvatar() : userDto.getAvatar().getBytes();
            String avatarType = userDto.getAvatar().isEmpty()
                    ? user.getAvatarType()
                    : "." + userDto.getAvatar().getContentType().substring(userDto.getAvatar().getContentType().indexOf("/") + 1);
            userRepository.updateUserInfo(email, firstName, lastName, login, password, avatar, avatarType);
        } catch (IOException e) {
        }

        return userRepository.findByEmail(email);
    }

    @Override
    public void updateLastSeen(String email, LocalDateTime lastSeen, String status) {
        userRepository.updateLastSeen(email, lastSeen, status);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),new HashSet<GrantedAuthority>());
    }
}
