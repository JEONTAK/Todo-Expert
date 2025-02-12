package com.example.todoexpert.dto.response.auth;

import com.example.todoexpert.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private final Long id;
    private final String email;
    private final String username;
    private final String sessionId;
    private final Integer getMaxInActiveInterval;
    private final Long creationTime;
    private final Long lastAccessedTime;

    public LoginResponseDto(User user, HttpSession session) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.sessionId = session.getId();
        this.getMaxInActiveInterval = session.getMaxInactiveInterval();
        this.creationTime = session.getCreationTime();
        this.lastAccessedTime = session.getLastAccessedTime();
    }

}
