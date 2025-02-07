package com.example.todoexpert.user.entity;

import com.example.todoexpert.util.TimeStamped;
import com.example.todoexpert.user.dto.request.UserSaveRequestDto;
import com.example.todoexpert.user.dto.request.UserUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(UserSaveRequestDto requestDto, String encodedPassword) {
        this.email = requestDto.getEmail();
        this.username = requestDto.getUsername();
        this.password = encodedPassword;
    }

    public void updateUser(UserUpdateRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
