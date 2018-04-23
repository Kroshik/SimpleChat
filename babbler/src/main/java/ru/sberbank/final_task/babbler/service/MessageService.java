package ru.sberbank.final_task.babbler.service;

import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.web.dto.DeletedMessagesDto;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;

import java.util.List;

public interface MessageService {
    Message save(MessageDto messageDto);

    List<Message> getMessages();

    List<Message> getMessages(Long idFromUser, Long idToUser);

    List<Message> getDialog(Long idFromUser, Long idToUser);

    void deleteMessages(DeletedMessagesDto messageDto);

}
