package com.example.todoexpert.user.service;

import com.example.todoexpert.user.dto.request.UserSaveRequestDto;
import com.example.todoexpert.user.dto.request.UserUpdateRequestDto;
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

    public UserResponseDto saveUser(UserSaveRequestDto requestDto) {
        User user = new User(requestDto);
        userRepository.save(user);
        return UserResponseDto.toDto(user);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return UserResponseDto.toDto(findUser);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmailOrElseThrow(email);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto requestDto) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateUser(requestDto);
        return UserResponseDto.toDto(findUser);
    }

    public void deleteUser(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }
}
