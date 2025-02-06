package com.example.todoexpert.todo.repository;

import com.example.todoexpert.todo.entity.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    default Todo findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 존재하지 않습니다."));
    }

    @Query("SELECT t FROM Todo t " +
            "JOIN t.user u " +
            "WHERE (:email IS NULL OR u.email = :email) " +
            "AND (:username IS NULL OR u.username = :username) ")
    List<Todo> findByFilters(
            @Param("email") String email,
            @Param("username") String username
    );
}
