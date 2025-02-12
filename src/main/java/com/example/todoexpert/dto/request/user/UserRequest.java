package com.example.todoexpert.dto.request.user;

import static com.example.todoexpert.util.Constants.EMAIL_SIZE;
import static com.example.todoexpert.util.Constants.EMAIl_NOT_NULL;
import static com.example.todoexpert.util.Constants.EMAIl_TYPE;
import static com.example.todoexpert.util.Constants.IS_INCLUDE_SMALL_ALPHABET_NUMBER;
import static com.example.todoexpert.util.Constants.PASSWORD_NOT_NULL;
import static com.example.todoexpert.util.Constants.PASSWORD_REQUIREMENT;
import static com.example.todoexpert.util.Constants.PASSWORD_SIZE;
import static com.example.todoexpert.util.Constants.USERNAME_NOT_NULL;
import static com.example.todoexpert.util.Constants.USERNAME_SIZE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = EMAIl_NOT_NULL)
    @Email(message = EMAIl_TYPE)
    @Size(max = 40, message = EMAIL_SIZE)
    private final String email;

    @NotBlank(message = USERNAME_NOT_NULL)
    @Size(max = 20, message = USERNAME_SIZE)
    private final String username;

    @NotBlank(message = PASSWORD_NOT_NULL)
    @Size(max = 20, message = PASSWORD_SIZE)
    @Pattern(regexp = IS_INCLUDE_SMALL_ALPHABET_NUMBER, message = PASSWORD_REQUIREMENT)
    private final String password;
}
