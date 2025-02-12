package com.example.todoexpert.service;

import com.example.todoexpert.dto.request.auth.LoginRequestDto;
import com.example.todoexpert.dto.response.auth.LoginResponseDto;
import com.example.todoexpert.entity.User;
import com.example.todoexpert.repository.UserRepository;
import com.example.todoexpert.util.config.PasswordEncoder;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
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
        User findUser = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) {
            throw new CustomExceptionHandler(ErrorCode.NOT_MATCH_PASSWORD);
        }

        HttpSession session = httpRequest.getSession();
        session.setAttribute("user", findUser);

        return new LoginResponseDto(findUser, session);
    }

    public void logout(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            session.invalidate();
        } else {
            throw new CustomExceptionHandler(ErrorCode.NOT_LOGIN);
        }
    }

}
