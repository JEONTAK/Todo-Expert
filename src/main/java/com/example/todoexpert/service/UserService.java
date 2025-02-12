package com.example.todoexpert.service;

import com.example.todoexpert.util.config.PasswordEncoder;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import com.example.todoexpert.dto.request.user.UserDeleteRequest;
import com.example.todoexpert.dto.request.user.UserRequest;
import com.example.todoexpert.dto.response.user.UserResponse;
import com.example.todoexpert.entity.User;
import com.example.todoexpert.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse saveUser(UserRequest requestDto) {
        User findUser = userRepository.findByEmail(requestDto.getEmail()).orElse(null);

        if (findUser != null) {
            throw new CustomExceptionHandler(ErrorCode.ALREADY_EXIST_USER);
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = User.toEntity(requestDto, encodedPassword);
        userRepository.save(user);

        return UserResponse.of(user);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::of)
                .toList();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest requestDto) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));

        if (!findUser.getEmail().equals(requestDto.getEmail())) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_USER);
        }

        findUser.updateUser(requestDto);
        findUser = userRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));
        return UserResponse.of(findUser);
    }

    @Transactional
    public void deleteUser(Long id, UserDeleteRequest requestDto) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));

        if (!findUser.getEmail().equals(requestDto.getEmail())) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_DELETE_USER);
        }

        userRepository.delete(findUser);
    }
}
