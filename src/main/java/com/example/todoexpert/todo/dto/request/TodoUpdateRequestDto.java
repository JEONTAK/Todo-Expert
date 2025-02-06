package com.example.todoexpert.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoUpdateRequestDto {

    private final String title;
    private final String contents;

}
