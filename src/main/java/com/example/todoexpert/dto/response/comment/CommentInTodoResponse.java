package com.example.todoexpert.dto.response.comment;

import com.example.todoexpert.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentInTodoResponse {

    private final Long id;
    private final String email;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private CommentInTodoResponse(Long id, String email, String username, String contents, LocalDateTime createdAt,
                                  LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentInTodoResponse of(Comment comment) {
        return new CommentInTodoResponse(comment.getId(), comment.getUser().getEmail(),
                comment.getUser().getUsername(), comment.getContents(), comment.getCreatedAt(),
                comment.getModifiedAt());
    }
}
