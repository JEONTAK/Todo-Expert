package com.example.todoexpert.comment.dto.response;

import com.example.todoexpert.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long id;
    private final String title;
    private final String email;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getTodo().getTitle(), comment.getUser().getEmail(),
                comment.getUser().getUsername(), comment.getContents(), comment.getCreatedAt(),
                comment.getModifiedAt());
    }
}
