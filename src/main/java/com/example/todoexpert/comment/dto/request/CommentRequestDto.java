package com.example.todoexpert.comment.dto.request;

import static com.example.todoexpert.util.Constants.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {

    @NotNull(message = TODO_ID_NOT_NULL)
    private final Long todoId;

    @NotBlank(message = EMAIl_NOT_NULL)
    @Email(message = EMAIl_TYPE)
    @Size(max = 40, message = EMAIL_SIZE)
    private final String email;

    @NotBlank(message = CONTENTS_NOT_NULL)
    @Size(max = 50, message = COMMENT_CONTENTS_SIZE)
    private final String contents;


}
