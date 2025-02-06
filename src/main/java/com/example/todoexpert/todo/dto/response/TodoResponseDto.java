package com.example.todoexpert.todo.dto;

import com.example.todoexpert.todo.entity.Todo;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getUsername(), todo.getTitle(), todo.getContents(),
                todo.getCreatedAt(), todo.getModifiedAt());
    }
}
