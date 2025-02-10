package com.example.todoexpert.todo.service;

import com.example.todoexpert.comment.dto.response.CommentInTodoResponseDto;
import com.example.todoexpert.comment.repository.CommentRepository;
import com.example.todoexpert.todo.dto.request.TodoDeleteRequestDto;
import com.example.todoexpert.todo.dto.request.TodoSaveRequestDto;
import com.example.todoexpert.todo.dto.request.TodoUpdateRequestDto;
import com.example.todoexpert.todo.dto.response.TodoCommonResponseDto;
import com.example.todoexpert.todo.dto.response.TodoFindResponseDto;
import com.example.todoexpert.todo.entity.Todo;
import com.example.todoexpert.todo.repository.TodoRepository;
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
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public TodoCommonResponseDto saveTodo(TodoSaveRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo todo = new Todo(findUser, requestDto);
        todoRepository.save(todo);
        return TodoCommonResponseDto.toDto(todo);
    }

    public List<TodoFindResponseDto> findAll(String username, String email) {
        return todoRepository.findByFilters(username, email)
                .stream()
                .map(todo -> TodoFindResponseDto.toDto(todo, commentRepository.findByTodoId(todo.getId())
                        .stream()
                        .map(CommentInTodoResponseDto::toDto)
                        .toList()))
                .toList();

    }

    public TodoFindResponseDto findByIdWithComment(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        List<CommentInTodoResponseDto> comments = commentRepository.findByTodoId(todo.getId())
                .stream()
                .map(CommentInTodoResponseDto::toDto)
                .toList();
        return TodoFindResponseDto.toDto(todo, comments);
    }

    public Todo findById(Long id) {
        return todoRepository.findByIdOrElseThrow(id);
    }

    @Transactional
    public TodoCommonResponseDto updateTodo(Long id, TodoUpdateRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if (!findTodo.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_TODO);
        }

        findTodo.updateTodo(requestDto);
        return TodoCommonResponseDto.toDto(findTodo);
    }

    @Transactional
    public void deleteTodo(Long id, TodoDeleteRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if (!findTodo.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_DELETE_TODO);
        }

        todoRepository.delete(findTodo);
    }


}
