package com.example.todoexpert.todo.dto.response;

import com.example.todoexpert.todo.entity.Todo;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TodoCommonResponseDto {

    private final Long id;
    private final String email;
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private TodoCommonResponseDto(Long id, String email, String username, String title, String contents,
                                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static TodoCommonResponseDto of(Todo todo) {
        return new TodoCommonResponseDto(todo.getId(), todo.getUser().getEmail(), todo.getUser().getUsername(),
                todo.getTitle(), todo.getContents(),
                todo.getCreatedAt(), todo.getModifiedAt());
    }
}
