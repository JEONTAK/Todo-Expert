package com.example.todoexpert.controller;


import com.example.todoexpert.dto.request.comment.CommentDeleteRequest;
import com.example.todoexpert.dto.request.comment.CommentRequest;
import com.example.todoexpert.dto.response.comment.CommentResponse;
import com.example.todoexpert.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentResponse> saveComment(@RequestBody CommentRequest requestDto) {
        CommentResponse responseDto = commentService.saveComment(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> findAll(@RequestParam(required = false) String username,
                                                         @RequestParam(required = false) String email,
                                                         @RequestParam(required = false) Long todoId) {
        List<CommentResponse> commentResponseList = commentService.findAll(username, email, todoId);
        return new ResponseEntity<>(commentResponseList, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        CommentResponse responseDto = CommentResponse.of(commentService.findById(id));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id,
                                                         @RequestBody CommentRequest requestDto) {
        CommentResponse responseDto = commentService.updateComment(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/comments/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @RequestBody CommentDeleteRequest requestDto) {
        commentService.deleteComment(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
