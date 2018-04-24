package ru.sberbank.final_task.babbler.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    private
    Long idUser;

    private
    String textSearch;
}
