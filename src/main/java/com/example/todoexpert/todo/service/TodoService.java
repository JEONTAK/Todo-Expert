package com.example.todoexpert.todo.service;

import com.example.todoexpert.comment.dto.response.CommentInTodoResponse;
import com.example.todoexpert.comment.service.CommentServiceForTodo;
import com.example.todoexpert.todo.dto.request.TodoDeleteRequest;
import com.example.todoexpert.todo.dto.request.TodoRequest;
import com.example.todoexpert.todo.dto.response.TodoCommonResponse;
import com.example.todoexpert.todo.dto.response.TodoFindResponse;
import com.example.todoexpert.todo.dto.response.TodoPageResponse;
import com.example.todoexpert.todo.entity.Todo;
import com.example.todoexpert.todo.repository.TodoRepository;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.user.service.UserService;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;
    private final CommentServiceForTodo commentServiceForTodo;

    @Transactional
    public TodoCommonResponse saveTodo(TodoRequest requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo todo = Todo.toEntity(findUser, requestDto);
        todoRepository.save(todo);
        return TodoCommonResponse.of(todo);
    }

    public List<TodoFindResponse> findAll(String username, String email) {
        return todoRepository.findByFilters(username, email)
                .stream()
                .map(todo -> TodoFindResponse.of(todo, commentServiceForTodo.findByTodoId(todo.getId())
                        .stream()
                        .map(CommentInTodoResponse::of)
                        .toList()))
                .toList();
    }

    public Page<TodoPageResponse> findAllByPage(Pageable pageable) {
        return todoRepository.findAllByOrderByModifiedAtDesc(pageable)
                .map(todo -> TodoPageResponse.of(todo, commentServiceForTodo.findByTodoId(todo.getId()).size()));
    }

    public TodoFindResponse findByIdWithComment(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        List<CommentInTodoResponse> comments = commentServiceForTodo.findByTodoId(todo.getId())
                .stream()
                .map(CommentInTodoResponse::of)
                .toList();
        return TodoFindResponse.of(todo, comments);
    }

    public Todo findById(Long id) {
        return todoRepository.findByIdOrElseThrow(id);
    }

    @Transactional
    public TodoCommonResponse updateTodo(Long id, TodoRequest requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if (!findTodo.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_TODO);
        }

        findTodo.updateTodo(requestDto);
        findTodo = todoRepository.findByIdOrElseThrow(id);
        return TodoCommonResponse.of(findTodo);
    }

    @Transactional
    public void deleteTodo(Long id, TodoDeleteRequest requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if (!findTodo.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_DELETE_TODO);
        }

        todoRepository.delete(findTodo);
    }
}
