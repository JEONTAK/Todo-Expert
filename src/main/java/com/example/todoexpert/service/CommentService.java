package com.example.todoexpert.service;


import com.example.todoexpert.dto.request.comment.CommentDeleteRequest;
import com.example.todoexpert.dto.request.comment.CommentRequest;
import com.example.todoexpert.dto.response.comment.CommentResponse;
import com.example.todoexpert.entity.Comment;
import com.example.todoexpert.repository.CommentRepository;
import com.example.todoexpert.entity.Todo;
import com.example.todoexpert.entity.User;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TodoService todoService;

    public CommentResponse saveComment(CommentRequest requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoService.findById(requestDto.getTodoId());
        Comment comment = Comment.toEntity(findUser, findTodo, requestDto);
        commentRepository.save(comment);
        return CommentResponse.of(comment);
    }

    public List<CommentResponse> findAll(String username, String email, Long todoId) {
        return commentRepository.findByFilters(username, email, todoId)
                .stream()
                .map(CommentResponse::of)
                .toList();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_COMMENT));
    }

    @Transactional
    public CommentResponse updateComment(Long id, CommentRequest requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoService.findById(requestDto.getTodoId());
        Comment findComment = commentRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_COMMENT));

        if (!findComment.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_COMMENT);
        }

        if (!findComment.getTodo().equals(findTodo)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_TODO_UPDATE_COMMENT);
        }

        findComment.updateComment(requestDto);
        findComment = commentRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_COMMENT));
        return CommentResponse.of(findComment);
    }

    public void deleteComment(Long id, CommentDeleteRequest requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoService.findById(requestDto.getTodoId());
        Comment findComment = commentRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler(ErrorCode.NOT_FOUND_COMMENT));

        if (!findComment.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_COMMENT);
        }

        if (!findComment.getTodo().equals(findTodo)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_TODO_UPDATE_COMMENT);
        }

        commentRepository.delete(findComment);
    }
}
