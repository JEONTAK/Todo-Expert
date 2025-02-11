package com.example.todoexpert.comment.service;


import com.example.todoexpert.comment.dto.request.CommentDeleteRequestDto;
import com.example.todoexpert.comment.dto.request.CommentRequestDto;
import com.example.todoexpert.comment.dto.response.CommentResponseDto;
import com.example.todoexpert.comment.entity.Comment;
import com.example.todoexpert.comment.repository.CommentRepository;
import com.example.todoexpert.todo.entity.Todo;
import com.example.todoexpert.todo.service.TodoService;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.user.service.UserService;
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

    public CommentResponseDto saveComment(CommentRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoService.findById(requestDto.getTodoId());
        Comment comment = Comment.toEntity(findUser, findTodo, requestDto);
        commentRepository.save(comment);
        return CommentResponseDto.of(comment);
    }

    public List<CommentResponseDto> findAll(String username, String email, Long todoId) {
        return commentRepository.findByFilters(username, email, todoId)
                .stream()
                .map(CommentResponseDto::of)
                .toList();
    }

    public Comment findById(Long id) {
        return commentRepository.findByIdOrElseThrow(id);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoService.findById(requestDto.getTodoId());
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        if (!findComment.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_COMMENT);
        }

        if (!findComment.getTodo().equals(findTodo)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_TODO_UPDATE_COMMENT);
        }

        findComment.updateComment(requestDto);
        return CommentResponseDto.of(findComment);
    }

    @Transactional
    public void deleteComment(Long id, CommentDeleteRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoService.findById(requestDto.getTodoId());
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        if (!findComment.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_COMMENT);
        }

        if (!findComment.getTodo().equals(findTodo)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_TODO_UPDATE_COMMENT);
        }

        commentRepository.delete(findComment);
    }
}
