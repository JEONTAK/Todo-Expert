package com.example.todoexpert.login.service;

import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import com.example.todoexpert.login.dto.request.LoginRequestDto;
import com.example.todoexpert.login.dto.request.LogoutRequestDto;
import com.example.todoexpert.login.dto.response.LoginResponseDto;
import com.example.todoexpert.login.dto.response.LogoutResponseDto;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto request, HttpServletRequest httpRequest) {
        User findUser = userRepository.findByEmailOrElseThrow(request.getEmail());

        if (!findUser.getPassword().equals(request.getPassword())) {
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
        } else{
            throw new CustomExceptionHandler(ErrorCode.NOT_LOGIN);
        }

        return new LogoutResponseDto(findUser);
    }

}
