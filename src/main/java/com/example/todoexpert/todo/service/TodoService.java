package com.example.todoexpert.todo.service;

import com.example.todoexpert.util.exception.CustomExceptionHandler;
import com.example.todoexpert.util.exception.ErrorCode;
import com.example.todoexpert.todo.dto.request.TodoDeleteRequestDto;
import com.example.todoexpert.todo.dto.request.TodoSaveRequestDto;
import com.example.todoexpert.todo.dto.request.TodoUpdateRequestDto;
import com.example.todoexpert.todo.dto.response.TodoResponseDto;
import com.example.todoexpert.todo.entity.Todo;
import com.example.todoexpert.todo.repository.TodoRepository;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    public TodoResponseDto saveTodo(TodoSaveRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo todo = new Todo(findUser, requestDto.getTitle(), requestDto.getContents());
        todoRepository.save(todo);
        return TodoResponseDto.toDto(todo);
    }

    public List<TodoResponseDto> findAll(String username, String email) {
        return todoRepository.findByFilters(username, email)
                .stream()
                .map(TodoResponseDto::toDto)
                .toList();
    }

    public TodoResponseDto findById(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        return TodoResponseDto.toDto(findTodo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoUpdateRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if(!findTodo.getUser().equals(findUser)){
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_UPDATE_TODO);
        }

        findTodo.updateTodo(requestDto);
        return TodoResponseDto.toDto(findTodo);
    }

    @Transactional
    public void deleteTodo(Long id, TodoDeleteRequestDto requestDto) {
        User findUser = userService.findByEmail(requestDto.getEmail());
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        if(!findTodo.getUser().equals(findUser)){
            throw new CustomExceptionHandler(ErrorCode.INVALID_USER_DELETE_TODO);
        }

        todoRepository.delete(findTodo);
    }
}
