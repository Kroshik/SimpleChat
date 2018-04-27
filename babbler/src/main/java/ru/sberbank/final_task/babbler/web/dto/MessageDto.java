package ru.sberbank.final_task.babbler.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private String textMessage;
    
    private
    LocalDateTime dateMessage;

    private
    String nameFrom;

    private
    Long idFromUser;

    private
    Long idToUser;

    private
    MultipartFile[] file;
}
