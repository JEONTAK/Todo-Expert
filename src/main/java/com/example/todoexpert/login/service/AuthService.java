package com.example.todoexpert.login.service;

import com.example.todoexpert.login.dto.request.LoginRequestDto;
import com.example.todoexpert.login.dto.request.LogoutRequestDto;
import com.example.todoexpert.login.dto.response.LoginResponseDto;
import com.example.todoexpert.login.dto.response.LogoutResponseDto;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto request, HttpServletRequest httpRequest) {
        User findUser = userRepository.findByEmailOrElseThrow(request.getEmail());

        if (!findUser.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 한 상태가 아닙니다.");
        }

        return new LogoutResponseDto(findUser);
    }

}
