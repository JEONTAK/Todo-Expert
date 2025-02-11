package com.example.todoexpert.user.service;

import com.example.todoexpert.util.config.PasswordEncoder;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import com.example.todoexpert.user.dto.request.UserDeleteRequestDto;
import com.example.todoexpert.user.dto.request.UserRequestDto;
import com.example.todoexpert.user.dto.response.UserResponseDto;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User findUser = userRepository.findByEmail(requestDto.getEmail()).orElse(null);

        if (findUser != null) {
            throw new CustomExceptionHandler(ErrorCode.ALREADY_EXIST_USER);
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = User.toEntity(requestDto, encodedPassword);
        userRepository.save(user);

        return UserResponseDto.of(user);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::of)
                .toList();
    }

    public User findById(Long id) {
        return userRepository.findByIdOrElseThrow(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmailOrElseThrow(email);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!findUser.getEmail().equals(requestDto.getEmail())) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_USER);
        }

        findUser.updateUser(requestDto);
        return UserResponseDto.of(findUser);
    }

    @Transactional
    public void deleteUser(Long id, UserDeleteRequestDto requestDto) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!findUser.getEmail().equals(requestDto.getEmail())) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_DELETE_USER);
        }

        userRepository.delete(findUser);
    }
}
