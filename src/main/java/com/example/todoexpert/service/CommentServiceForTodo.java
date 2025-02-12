package com.example.todoexpert.service;


import com.example.todoexpert.entity.Comment;
import com.example.todoexpert.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceForTodo {

    private final CommentRepository commentRepository;

    public List<Comment> findByTodoId(Long todoId) {
        return commentRepository.findByTodoId(todoId);
    }
}
