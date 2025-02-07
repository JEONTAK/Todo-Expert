package com.example.todoexpert.login.controller;

import com.example.todoexpert.login.dto.request.LoginRequestDto;
import com.example.todoexpert.login.dto.response.LoginResponseDto;
import com.example.todoexpert.login.dto.request.LogoutRequestDto;
import com.example.todoexpert.login.dto.response.LogoutResponseDto;
import com.example.todoexpert.login.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v4")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto,
                                                  HttpServletRequest httpRequest) {
        LoginResponseDto responseDto = authService.login(requestDto, httpRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<LogoutResponseDto> logout(HttpServletRequest httpRequest,
                                                    @RequestBody LogoutRequestDto requestDto) {
        LogoutResponseDto responseDto = authService.logout(httpRequest, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
