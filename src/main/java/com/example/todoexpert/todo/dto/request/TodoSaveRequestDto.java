package com.example.todoexpert.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoSaveRequestDto {

    private final String email;
    private final String title;
    private final String contents;

}
