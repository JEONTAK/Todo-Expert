package com.example.todoexpert.user.dto.response;

import com.example.todoexpert.user.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String email;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private UserResponse(Long id, String email, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getUsername(), user.getCreatedAt(),
                user.getModifiedAt());
    }
}
