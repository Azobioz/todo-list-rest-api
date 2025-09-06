package com.azobioz.api;


import lombok.Data;

@Data
public class TaskUpdateDTO {
    private String title;
    private String description;
    private Boolean completed;
}
