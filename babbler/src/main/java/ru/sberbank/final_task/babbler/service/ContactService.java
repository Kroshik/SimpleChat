package ru.sberbank.final_task.babbler.service;


import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.Contact;

@Service
public interface ContactService {
    Contact save(Contact contact);
}
