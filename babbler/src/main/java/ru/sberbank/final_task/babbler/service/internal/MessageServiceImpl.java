package ru.sberbank.final_task.babbler.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.repository.MessageRepository;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.web.dto.DeletedMessagesDto;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(MessageDto messageDto) {
        Message message = new Message();
//        message.setIdFrom(messageDto.getIdFrom());
        message.setTextMessage(messageDto.getTextMessage());
        message.setNameUser(messageDto.getNameFrom());
        message.setDateMessage(messageDto.getDateMessage());
        message.setIdFromUser(messageDto.getIdFromUser());
        message.setIdToUser(messageDto.getIdToUser());
        try {
            if(messageDto.getFile().length > 0) {
                message.setFile(messageDto.getFile()[0].getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageRepository.save(message);
        return message;
    }

    @Override
    public List <Message> getMessages() {
        return messageRepository.findAll();
//        return messageRepository.findByIdFrom(id);
    }

    @Override
    public void deleteMessages(DeletedMessagesDto messageDto) {
        messageDto.getSetDeleted().forEach(id->messageRepository.deleteById(id));
    }

    public List<Message> getDialog(Long idFromUser, Long idToUser) {
        return messageRepository.findDialog(idFromUser, idToUser);
    }

    public List <Message> getMessages(Long idFromUser, Long idToUser) {
        List<Message> messages = messageRepository.findByIdFromUser(idFromUser);
        messages.addAll(messageRepository.findByIdToUser(idToUser));

        return messages;
//        List<Message> messages = messageRepository.findByPairIdUsers(Pair.of(idFromUser, idToUser));
//        messages.addAll(messageRepository.findByPairIdUsers(Pair.of(idToUser, idFromUser)));
//        return messages;
//        return messageRepository.findAll();
//        return messageRepository.findDialog(idFromUser, idToUser);
    }

    public void deleteMessagesAllUser(DeletedMessagesDto messageDto) {
        messageDto.getSetDeleted().forEach(id->messageRepository.deleteById(id));
    }

    public void deleteMessagesOneUser(DeletedMessagesDto messageDto) {
        List<Message> messages = getMessages();
    }
}
