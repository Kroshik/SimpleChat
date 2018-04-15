package ru.sberbank.final_task.babbler.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.service.internal.TherapyServiceImpl;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    @NonNull
    private TherapyServiceImpl therapyService;

    @NonNull
    private UserService userService;

    @NonNull
    private MessageService messageService;

    public void run(ApplicationArguments args) {
        therapyService.mockData();
        userService.save(new User("Karina", "Dergun", "lala@mail.ru", "$2a$10$2fJ7ufJb1Pk3jMSWbixsluiYq/L97Uvm9UAAnDkg0XDqhm5Fdc.zG"));
    }
}