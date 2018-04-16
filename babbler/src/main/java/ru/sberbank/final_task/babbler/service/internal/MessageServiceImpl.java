package ru.sberbank.final_task.babbler.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.repository.MessageRepository;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(MessageDto messageDto) {
        Message message = new Message();
        message.setIdFrom(messageDto.getIdFrom());
        message.setTextMessage(messageDto.getTextMessage());
        message.setNameUser(messageDto.getNameFrom());
        message.setDateMessage(messageDto.getDateMessage());
        messageRepository.save(message);
        return message;
    }

    @Override
    public List <Message> getMessages() {
        return messageRepository.findAll();
//        return messageRepository.findByIdFrom(id);
    }

    @Override
    public void deleteMessages(MessageDto messageDto) {
        messageDto.getSetDeleted().forEach(id->messageRepository.deleteById(id));
    }
}
