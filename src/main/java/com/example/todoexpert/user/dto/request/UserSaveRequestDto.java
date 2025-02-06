package com.example.todoexpert.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {

    private final String email;
    private final String username;
    private final String password;
    
}
