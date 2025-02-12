package com.example.todoexpert.dto.response.comment;

import com.example.todoexpert.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponse {

    private final Long id;
    private final String title;
    private final String email;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private CommentResponse(Long id, String title, String email, String username, String contents,
                            LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentResponse of(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getTodo().getTitle(), comment.getUser().getEmail(),
                comment.getUser().getUsername(), comment.getContents(), comment.getCreatedAt(),
                comment.getModifiedAt());
    }
}
