package com.example.todoexpert.dto.request.comment;

import static com.example.todoexpert.util.Constants.EMAIL_SIZE;
import static com.example.todoexpert.util.Constants.EMAIl_NOT_NULL;
import static com.example.todoexpert.util.Constants.EMAIl_TYPE;
import static com.example.todoexpert.util.Constants.TODO_ID_NOT_NULL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDeleteRequest {

    @NotNull(message = TODO_ID_NOT_NULL)
    private final Long todoId;

    @NotBlank(message = EMAIl_NOT_NULL)
    @Email(message = EMAIl_TYPE)
    @Size(max = 40, message = EMAIL_SIZE)
    private final String email;
}
