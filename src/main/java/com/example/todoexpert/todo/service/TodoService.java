package com.example.todoexpert.todo.service;

import com.example.todoexpert.todo.dto.TodoRequestDto;
import com.example.todoexpert.todo.dto.TodoResponseDto;
import com.example.todoexpert.todo.entity.Todo;
import com.example.todoexpert.todo.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getUsername(), requestDto.getTitle(), requestDto.getContents());
        todoRepository.save(todo);
        return TodoResponseDto.toDto(todo);
    }

    public List<TodoResponseDto> findAll(String username) {
        if (username == null) {
            return todoRepository.findAll()
                    .stream()
                    .map(TodoResponseDto::toDto)
                    .toList();
        }

        return todoRepository.findTodosByUsername(username)
                .stream()
                .map(TodoResponseDto::toDto)
                .toList();
    }

    public TodoResponseDto findById(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        return TodoResponseDto.toDto(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);

        todo.updateTodo(requestDto);
        return TodoResponseDto.toDto(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);
        todoRepository.delete(todo);
    }
}
