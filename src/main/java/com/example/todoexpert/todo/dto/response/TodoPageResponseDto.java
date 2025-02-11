package com.example.todoexpert.todo.dto.response;

import com.example.todoexpert.todo.entity.Todo;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TodoPageResponseDto {

    private final String title;
    private final String contents;
    private final String username;
    private final Integer commentsCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private TodoPageResponseDto(String title, String contents, String username, Integer commentsCount,
                                LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static TodoPageResponseDto of(Todo todo, int count) {
        return new TodoPageResponseDto(todo.getTitle(), todo.getContents(), todo.getUser().getUsername(), count,
                todo.getCreatedAt(), todo.getModifiedAt());
    }
}
