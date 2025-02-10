package com.example.todoexpert.todo.controller;

import com.example.todoexpert.todo.dto.request.TodoDeleteRequestDto;
import com.example.todoexpert.todo.dto.request.TodoSaveRequestDto;
import com.example.todoexpert.todo.dto.request.TodoUpdateRequestDto;
import com.example.todoexpert.todo.dto.response.TodoCommonResponseDto;
import com.example.todoexpert.todo.dto.response.TodoFindResponseDto;
import com.example.todoexpert.todo.dto.response.TodoPageResponseDto;
import com.example.todoexpert.todo.service.TodoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v6")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoCommonResponseDto> saveTodo(@Valid @RequestBody TodoSaveRequestDto requestDto) {
        TodoCommonResponseDto responseDto = todoService.saveTodo(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoFindResponseDto>> findAll(@RequestParam(required = false) String username,
                                                             @RequestParam(required = false) String userEmail) {
        List<TodoFindResponseDto> responseDtoList = todoService.findAll(username, userEmail);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/todos/pages")
    public ResponseEntity<Page<TodoPageResponseDto>> findAllByPage(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<TodoPageResponseDto> responseDtoList = todoService.findAllByPage(pageNumber, pageSize);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoFindResponseDto> findById(@PathVariable Long id) {
        TodoFindResponseDto responseDto = todoService.findByIdWithComment(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoCommonResponseDto> updateTodo(@PathVariable Long id,
                                                            @Valid @RequestBody TodoUpdateRequestDto requestDto) {
        TodoCommonResponseDto updateDto = todoService.updateTodo(id, requestDto);
        return new ResponseEntity<>(updateDto, HttpStatus.OK);
    }

    @PostMapping("/todos/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id, @Valid @RequestBody TodoDeleteRequestDto requestDto) {
        todoService.deleteTodo(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
