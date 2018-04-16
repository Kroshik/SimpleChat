package ru.sberbank.final_task.babbler.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private String name;


    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }
}
