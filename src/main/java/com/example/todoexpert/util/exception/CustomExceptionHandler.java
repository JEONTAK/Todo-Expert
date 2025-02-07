package com.example.todoexpert.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExceptionHandler extends RuntimeException {
    private final ErrorCode errorCode;
}
