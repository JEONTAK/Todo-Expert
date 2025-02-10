package com.example.todoexpert.comment.dto.response;

import com.example.todoexpert.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentInTodoResponseDto {

    private final Long id;
    private final String email;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static CommentInTodoResponseDto toDto(Comment comment) {
        return new CommentInTodoResponseDto(comment.getId(), comment.getUser().getEmail(),
                comment.getUser().getUsername(), comment.getContents(), comment.getCreatedAt(),
                comment.getModifiedAt());
    }
}
