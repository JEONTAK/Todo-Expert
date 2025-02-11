package com.example.todoexpert.todo.dto.request;

import static com.example.todoexpert.util.Constants.CONTENTS_NOT_NULL;
import static com.example.todoexpert.util.Constants.TODO_CONTENTS_SIZE;
import static com.example.todoexpert.util.Constants.EMAIL_SIZE;
import static com.example.todoexpert.util.Constants.EMAIl_NOT_NULL;
import static com.example.todoexpert.util.Constants.EMAIl_TYPE;
import static com.example.todoexpert.util.Constants.TITLE_NOT_NULL;
import static com.example.todoexpert.util.Constants.TITLE_SIZE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoRequest {

    @NotBlank(message = EMAIl_NOT_NULL)
    @Email(message = EMAIl_TYPE)
    @Size(max = 40, message = EMAIL_SIZE)
    private final String email;

    @NotBlank(message = TITLE_NOT_NULL)
    @Size(max = 20, message = TITLE_SIZE)
    private final String title;

    @NotBlank(message = CONTENTS_NOT_NULL)
    @Size(max = 200, message = TODO_CONTENTS_SIZE)
    private final String contents;
}
