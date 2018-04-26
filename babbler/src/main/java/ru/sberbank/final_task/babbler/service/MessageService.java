package ru.sberbank.final_task.babbler.service;

import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.web.dto.DeletedMessagesDto;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;

import java.util.List;

public interface MessageService {
    void save(MessageDto messageDto);

    void save(Message message);

    List<Message> getMessages();

    Message getMessage(Long idMessage);

    List<Message> getMessages(Long idFromUser, Long idToUser);

    List<Message> getDialog(Long idFromUser, Long idToUser);

    List<Message> searchMessagesByText(Long idUser, String textMessage);

    void deleteMessages(DeletedMessagesDto messageDto);

}
