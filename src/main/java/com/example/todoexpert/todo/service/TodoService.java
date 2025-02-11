package com.example.todoexpert.todo.service;

import com.example.todoexpert.comment.dto.response.CommentInTodoResponseDto;
import com.example.todoexpert.comment.service.CommentServiceForTodo;
import com.example.todoexpert.todo.dto.request.TodoDeleteRequestDto;
import com.example.todoexpert.todo.dto.request.TodoRequestDto;
import com.example.todoexpert.todo.dto.response.TodoCommonResponseDto;
import com.example.todoexpert.todo.dto.response.TodoFindResponseDto;
import com.example.todoexpert.todo.dto.response.TodoPageResponseDto;
import com.example.todoexpert.todo.entity.Todo;
import com.example.todoexpert.todo.repository.TodoRepository;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.user.service.UserService;
import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final String MODIFIED_DESC = "modifiedAt";
    private final TodoRepository todoRepository;
    private final UserService userService;
    private final CommentServiceForTodo commentServiceForTodo;

    public TodoCommonResponseDto saveTodo(TodoRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo todo = Todo.toEntity(findUser, requestDto);
        todoRepository.save(todo);
        return TodoCommonResponseDto.of(todo);
    }

    public List<TodoFindResponseDto> findAll(String username, String email) {
        return todoRepository.findByFilters(username, email)
                .stream()
                .map(todo -> TodoFindResponseDto.of(todo, commentServiceForTodo.findByTodoId(todo.getId())
                        .stream()
                        .map(CommentInTodoResponseDto::of)
                        .toList()))
                .toList();
    }

    public Page<TodoPageResponseDto> findAllByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.DESC, MODIFIED_DESC));
        return todoRepository.findAll(pageable)
                .map(todo -> TodoPageResponseDto.of(todo, commentServiceForTodo.findByTodoId(todo.getId()).size()));
    }

    public TodoFindResponseDto findByIdWithComment(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        List<CommentInTodoResponseDto> comments = commentServiceForTodo.findByTodoId(todo.getId())
                .stream()
                .map(CommentInTodoResponseDto::of)
                .toList();
        return TodoFindResponseDto.of(todo, comments);
    }

    public Todo findById(Long id) {
        return todoRepository.findByIdOrElseThrow(id);
    }

    @Transactional
    public TodoCommonResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if (!findTodo.getUser().equals(findUser)) {
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_TODO);
        }

        findTodo.updateTodo(requestDto);
        return TodoCommonResponseDto.of(findTodo);
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
