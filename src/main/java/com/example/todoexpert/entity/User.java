package com.example.todoexpert.entity;

import com.example.todoexpert.dto.request.user.UserRequest;
import com.example.todoexpert.util.TimeStamped;
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

    private User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static User toEntity(UserRequest requestDto, String encodedPassword) {
        return new User(requestDto.getEmail(), requestDto.getUsername(), encodedPassword);
    }

    public void updateUser(UserRequest requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
