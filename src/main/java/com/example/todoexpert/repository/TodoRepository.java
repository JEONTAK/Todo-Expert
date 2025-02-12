package com.example.todoexpert.repository;

import com.example.todoexpert.entity.Todo;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    default Todo findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_TODO));
    }

    @Query("SELECT t FROM Todo t " +
            "JOIN t.user u " +
            "WHERE (:email IS NULL OR u.email = :email) " +
            "AND (:username IS NULL OR u.username = :username) ")
    List<Todo> findByFilters(
            @Param("email") String email,
            @Param("username") String username
    );

    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
