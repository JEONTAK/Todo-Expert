package com.example.todoexpert.todo.dto.request;

import static com.example.todoexpert.util.Constants.EMAIL_SIZE;
import static com.example.todoexpert.util.Constants.EMAIl_NOT_NULL;
import static com.example.todoexpert.util.Constants.EMAIl_TYPE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoDeleteRequest {

    @NotBlank(message = EMAIl_NOT_NULL)
    @Email(message = EMAIl_TYPE)
    @Size(max = 40, message = EMAIL_SIZE)
    private final String email;
}
