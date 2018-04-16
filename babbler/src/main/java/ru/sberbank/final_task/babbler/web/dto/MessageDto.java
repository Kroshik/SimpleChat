package ru.sberbank.final_task.babbler.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private String textMessage;

    private
    LocalDateTime dateMessage;

    private
    String nameFrom;

    private
    Long idFrom;

    private
    List<Long> setDeleted;

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public LocalDateTime getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(LocalDateTime dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public Long getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(Long idFrom) {
        this.idFrom = idFrom;
    }

    public List<Long> getSetDeleted() {
        return setDeleted;
    }

    public void setSetDeleted(List<Long> setDeleted) {
        this.setDeleted = setDeleted;
    }
}
