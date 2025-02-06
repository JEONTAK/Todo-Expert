package com.example.todoexpert.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSaveRequestDto {

    private final String email;
    private final String username;
    private final String password;

}
