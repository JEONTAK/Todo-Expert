package com.example.todoexpert.controller;

import com.example.todoexpert.dto.request.user.UserDeleteRequest;
import com.example.todoexpert.dto.request.user.UserRequest;
import com.example.todoexpert.dto.response.user.UserResponse;
import com.example.todoexpert.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v6")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest requestDto) {
        UserResponse responseDto = userService.saveUser(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> userResponseList = userService.findAll();
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        UserResponse responseDto = UserResponse.of(userService.findById(id));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserRequest requestDto) {
        UserResponse responseDto = userService.updateUser(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @Valid @RequestBody UserDeleteRequest requestDto) {
        userService.deleteUser(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
