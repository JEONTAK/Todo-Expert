package com.example.todoexpert.auth.dto.request;

import static com.example.todoexpert.util.Constants.EMAIL_SIZE;
import static com.example.todoexpert.util.Constants.EMAIl_NOT_NULL;
import static com.example.todoexpert.util.Constants.EMAIl_TYPE;
import static com.example.todoexpert.util.Constants.PASSWORD_NOT_NULL;
import static com.example.todoexpert.util.Constants.PASSWORD_SIZE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = EMAIl_NOT_NULL)
    @Email(message = EMAIl_TYPE)
    @Size(max = 40, message = EMAIL_SIZE)
    private final String email;

    @NotBlank(message = PASSWORD_NOT_NULL)
    @Size(max = 20, message = PASSWORD_SIZE)
    private final String password;

}
