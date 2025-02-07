package com.example.todoexpert.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

public class AuthFilter implements Filter {

    private static final String[] WHITE_LIST = {"/api/v4/auth/login", "/api/v4/users/register", "/api/v4/auth/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        if (isNotWhileList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 상태가 아닙니다.");
            }
        }

        if (requestURI.startsWith("/api/v4/users/register")) {
            HttpSession session = httpRequest.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 로그인한 사용자는 회원가입이 불가능합니다.");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isNotWhileList(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
