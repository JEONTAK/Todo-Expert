package com.example.todoexpert.todo.dto.response;

import com.example.todoexpert.todo.entity.Todo;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoPageResponseDto {

    private final String title;
    private final String contents;
    private final String username;
    private final Integer commentsCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static TodoPageResponseDto toDto(Todo todo, int count) {
        return new TodoPageResponseDto(todo.getTitle(), todo.getContents(), todo.getUser().getUsername(), count,
                todo.getCreatedAt(), todo.getModifiedAt());
    }
}
