package ru.ifmo.cse.phms.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.ifmo.cse.phms.domain.auth.User;
import ru.ifmo.cse.phms.service.UserService;
import ru.ifmo.cse.phms.service.internal.TherapyServiceImpl;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    @NonNull
    private TherapyServiceImpl therapyService;

    @NonNull
    private UserService userService;

    public void run(ApplicationArguments args) {
        therapyService.mockData();
        userService.save(new User("user", "test", "test@t.ru", "$2a$11$JGMVKy4VZaLCHW4u/M6jK.oUKpVukuHtLskGR5RxwgaTHYrnhpr4S"));
    }
}