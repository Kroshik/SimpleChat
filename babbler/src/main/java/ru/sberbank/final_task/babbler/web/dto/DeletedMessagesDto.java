package ru.sberbank.final_task.babbler.web.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class DeletedMessagesDto {
    private
    Long userId;

    private
    List<Long> setDeleted;
}
