package ru.sberbank.final_task.babbler.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.Contact;
import ru.sberbank.final_task.babbler.repository.auth.ContactRepository;
import ru.sberbank.final_task.babbler.service.ContactService;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}
