package com.example.todoexpert.service;

import com.example.todoexpert.dto.response.comment.CommentInTodoResponse;
import com.example.todoexpert.dto.request.todo.TodoDeleteRequest;
import com.example.todoexpert.dto.request.todo.TodoRequest;
import com.example.todoexpert.dto.response.todo.TodoCommonResponse;
import com.example.todoexpert.dto.response.todo.TodoFindResponse;
import com.example.todoexpert.dto.response.todo.TodoPageResponse;
import com.example.todoexpert.entity.Todo;
import com.example.todoexpert.repository.TodoRepository;
import com.example.todoexpert.entity.User;
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
