package ru.sberbank.final_task.babbler.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.service.UserService;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    @NonNull
    private UserService userService;

    @NonNull
    private MessageService messageService;

    public void run(ApplicationArguments args) {
        userService.save(new User("Karina", "Dergun", "karina777", "karina@mail.ru", "$2a$10$RGQGawn9qO4ZQNNl7bU4.eTUcXj1iKYOzWbl1vhAt87obvAbyzaFW"));
        userService.save(new User("Anton", "Polushin", "anton777", "anton@mail.ru", "$2a$10$LCkE5V7pXqmBYWopvhDW7OabJ9EA8Vo5.Xwl9ZAbQuo3nfi6eaRSa"));
        userService.save(new User("Ruslan", "Kvasov", "ruslan777", "ruslan@mail.ru", "$2a$10$QNvd7YyJOxXsnTzrHTmVl.MNEB6G.6kzjm4DgYFN82oYC0C/b1r7G"));
    }
}