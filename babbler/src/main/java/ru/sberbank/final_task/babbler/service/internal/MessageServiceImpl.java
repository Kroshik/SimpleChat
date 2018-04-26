package ru.sberbank.final_task.babbler.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.repository.MessageRepository;
import ru.sberbank.final_task.babbler.repository.auth.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(MessageDto messageDto) {
//        for (MultipartFile file : messageDto.getFiles()) {
        Message message = new Message();
        message.setNameUser(messageDto.getNameFrom());
        message.setDateMessage(messageDto.getDateMessage());
        message.setIdFromUser(messageDto.getIdFromUser());
        message.setIdToUser(messageDto.getIdToUser());
        message.setTextMessage(messageDto.getTextMessage());
        messageRepository.save(message);
        if (!messageDto.getFiles().get(0).getContentType().equals("application/octet-stream")) {
            saveFiles(messageDto);
        }
//        }
    }

    private void saveFiles(MessageDto messageDto) {
        for (MultipartFile file : messageDto.getFiles()) {
            Message message = new Message();
            message.setNameUser(messageDto.getNameFrom());
            message.setDateMessage(messageDto.getDateMessage());
            message.setIdFromUser(messageDto.getIdFromUser());
            message.setIdToUser(messageDto.getIdToUser());
            try {
                message.setFile(file.getBytes());
            } catch (IOException ignored) {
            }
            message.setTextMessage("");
            String currentType = file.getContentType();
            message.setFileType("." + currentType.substring(currentType.indexOf("/") + 1));

            messageRepository.save(message);
        }
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
//        return messageRepository.findByIdFrom(id);
        /*
        Old method
         */
    }

    @Override
    public Message getMessage(Long idMessage) {
        return messageRepository.getOne(idMessage);
    }

    @Override
    public void deleteMessages(DeletedMessagesDto messageDto) {
        /*
         delete from one user
         */
        messageDto.getSetDeleted().forEach(id -> messageRepository.deleteById(id));
    }

    public List<Message> getDialog(Long idFromUser, Long idToUser) {
        return messageRepository.findDialog(idFromUser, idToUser);
    }

    @Override
    public List<Message> searchMessagesByText(Long idUser, String textMessage) {
        return messageRepository.searchMessagesByText(idUser, textMessage);
    }

    public List<Message> getMessages(Long idFromUser, Long idToUser) {
        List<Message> messages = messageRepository.findByIdFromUser(idFromUser);
        messages.addAll(messageRepository.findByIdToUser(idToUser));

        return messages;
        /*
        Old method
         */
    }

    public void deleteMessagesAllUser(DeletedMessagesDto messageDto) {
        messageDto.getSetDeleted().forEach(id -> messageRepository.deleteById(id));
    }

    public void deleteMessagesOneUser(DeletedMessagesDto messageDto) {
        List<Message> messages = getMessages();
    }
}
