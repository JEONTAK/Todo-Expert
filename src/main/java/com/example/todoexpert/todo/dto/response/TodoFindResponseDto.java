package com.example.todoexpert.todo.dto.response;

import com.example.todoexpert.comment.dto.response.CommentInTodoResponseDto;
import com.example.todoexpert.todo.entity.Todo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class TodoFindResponseDto {

    private final Long id;
    private final String email;
    private final String username;
    private final String title;
    private final String contents;
    private final List<CommentInTodoResponseDto> comments;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private TodoFindResponseDto(Long id, String email, String username, String title, String contents,
                                List<CommentInTodoResponseDto> comments, LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.comments = comments;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static TodoFindResponseDto of(Todo todo, List<CommentInTodoResponseDto> comments) {
        return new TodoFindResponseDto(todo.getId(), todo.getUser().getEmail(), todo.getUser().getUsername(),
                todo.getTitle(), todo.getContents(), comments,
                todo.getCreatedAt(), todo.getModifiedAt());
    }
}
