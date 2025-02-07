package com.example.todoexpert.filter;

import com.example.todoexpert.exception.CustomExceptionHandler;
import com.example.todoexpert.exception.ErrorCode;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.util.PatternMatchUtils;

public class AuthFilter implements Filter {

    private static final String[] WHITE_LIST = {"*/auth/login", "*/users/register", "*/auth/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        if (isNotWhileList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                throw new CustomExceptionHandler(ErrorCode.NOT_LOGIN);
            }
        }

        if (requestURI.startsWith("/api/v4/users/register")) {
            HttpSession session = httpRequest.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                throw new CustomExceptionHandler(ErrorCode.ALREADY_LOGIN);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isNotWhileList(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
