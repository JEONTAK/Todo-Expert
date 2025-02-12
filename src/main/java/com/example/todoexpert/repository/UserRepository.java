package com.example.todoexpert.repository;

import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import com.example.todoexpert.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));
    }

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(
                () -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_USER));
    }
}
