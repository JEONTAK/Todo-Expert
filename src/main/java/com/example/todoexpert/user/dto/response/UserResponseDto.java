package com.example.todoexpert.user.dto.response;

import com.example.todoexpert.user.entity.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private final Long id;
    private final String email;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getEmail(), user.getUsername(), user.getCreatedAt(),
                user.getModifiedAt());
    }
}
