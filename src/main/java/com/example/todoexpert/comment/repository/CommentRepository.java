package com.example.todoexpert.comment.repository;

import com.example.todoexpert.comment.entity.Comment;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c " +
            "JOIN c.user u " +
            "JOIN c.todo t " +
            "WHERE (:email IS NULL OR u.email = :email) " +
            "AND (:username IS NULL OR u.username = :username) " +
            "AND (:todoId IS NULL OR t.id = :todoId) ")
    List<Comment> findByFilters(String username, String email, Long todoId);

    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_COMMENT));
    }

    List<Comment> findByTodoId(Long todoId);
}
