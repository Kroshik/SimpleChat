package ru.sberbank.final_task.babbler.service;

import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;

import java.util.List;

public interface MessageService {
    Message save (MessageDto messageDto);
    List<Message> getMessages();
//    String getNameOwner()
}
