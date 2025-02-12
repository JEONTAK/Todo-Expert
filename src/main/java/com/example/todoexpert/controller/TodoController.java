package com.example.todoexpert.controller;

import com.example.todoexpert.dto.request.todo.TodoDeleteRequest;
import com.example.todoexpert.dto.request.todo.TodoRequest;
import com.example.todoexpert.dto.response.todo.TodoCommonResponse;
import com.example.todoexpert.dto.response.todo.TodoFindResponse;
import com.example.todoexpert.dto.response.todo.TodoPageResponse;
import com.example.todoexpert.service.TodoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoCommonResponse> saveTodo(@Valid @RequestBody TodoRequest requestDto) {
        TodoCommonResponse responseDto = todoService.saveTodo(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoFindResponse>> findAll(@RequestParam(required = false) String username,
                                                          @RequestParam(required = false) String userEmail) {
        List<TodoFindResponse> responseDtoList = todoService.findAll(username, userEmail);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/todos/pages")
    public ResponseEntity<Page<TodoPageResponse>> findAllByPage(@PageableDefault(size = 10) Pageable pageable) {
        Page<TodoPageResponse> responseDtoList = todoService.findAllByPage(pageable);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoFindResponse> findById(@PathVariable Long id) {
        TodoFindResponse responseDto = todoService.findByIdWithComment(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoCommonResponse> updateTodo(@PathVariable Long id,
                                                         @Valid @RequestBody TodoRequest requestDto) {
        TodoCommonResponse updateDto = todoService.updateTodo(id, requestDto);
        return new ResponseEntity<>(updateDto, HttpStatus.OK);
    }

    @PostMapping("/todos/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id, @Valid @RequestBody TodoDeleteRequest requestDto) {
        todoService.deleteTodo(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
