package com.example.todoexpert.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoRequestDto {

    private final String title;
    private final String username;
    private final String contents;

}
