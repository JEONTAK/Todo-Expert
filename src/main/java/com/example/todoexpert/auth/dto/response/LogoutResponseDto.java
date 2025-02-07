package com.example.todoexpert.auth.dto.response;

import com.example.todoexpert.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogoutResponseDto {

    private final Long id;
    private final String email;
    private final String username;

    public LogoutResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }

}
