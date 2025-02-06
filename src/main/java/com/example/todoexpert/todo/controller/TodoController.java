package com.example.todoexpert.todo.controller;

import com.example.todoexpert.todo.dto.request.TodoSaveRequestDto;
import com.example.todoexpert.todo.dto.request.TodoUpdateRequestDto;
import com.example.todoexpert.todo.dto.response.TodoResponseDto;
import com.example.todoexpert.todo.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoResponseDto> saveTodo(@RequestBody TodoSaveRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.saveTodo(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> findAll(@RequestParam(required = false) String username,
                                                         @RequestParam(required = false) String userEmail) {
        List<TodoResponseDto> responseDtoList = todoService.findAll(username, userEmail);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> findById(@PathVariable Long id) {
        TodoResponseDto responseDto = todoService.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id,
                                                      @RequestBody TodoUpdateRequestDto requestDto) {
        TodoResponseDto updateDto = todoService.updateTodo(id, requestDto);
        return new ResponseEntity<>(updateDto, HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
