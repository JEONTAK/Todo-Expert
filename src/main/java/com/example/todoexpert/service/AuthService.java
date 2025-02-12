package com.example.todoexpert.service;

import com.example.todoexpert.util.config.PasswordEncoder;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import com.example.todoexpert.dto.request.auth.LoginRequestDto;
import com.example.todoexpert.dto.request.auth.LogoutRequestDto;
import com.example.todoexpert.dto.response.auth.LoginResponseDto;
import com.example.todoexpert.dto.response.auth.LogoutResponseDto;
import com.example.todoexpert.entity.User;
import com.example.todoexpert.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto request, HttpServletRequest httpRequest) {
        User findUser = userRepository.findByEmailOrElseThrow(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) {
            throw new CustomExceptionHandler(ErrorCode.NOT_MATCH_PASSWORD);
        }

        HttpSession session = httpRequest.getSession();
        session.setAttribute("user", findUser);

        return new LoginResponseDto(findUser, session);
    }

    public LogoutResponseDto logout(HttpServletRequest httpRequest, LogoutRequestDto requestDto) {
        User findUser = userRepository.findByEmailOrElseThrow(requestDto.getEmail());

        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            session.invalidate();
        } else {
            throw new CustomExceptionHandler(ErrorCode.NOT_LOGIN);
        }

        return new LogoutResponseDto(findUser);
    }

}
