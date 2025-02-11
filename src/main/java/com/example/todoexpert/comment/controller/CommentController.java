package com.example.todoexpert.comment.controller;


import com.example.todoexpert.comment.dto.request.CommentDeleteRequestDto;
import com.example.todoexpert.comment.dto.request.CommentRequestDto;
import com.example.todoexpert.comment.dto.response.CommentResponseDto;
import com.example.todoexpert.comment.service.CommentService;
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
    public ResponseEntity<CommentResponseDto> saveComment(@RequestBody CommentRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.saveComment(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll(@RequestParam(required = false) String username,
                                                            @RequestParam(required = false) String email,
                                                            @RequestParam(required = false) Long todoId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAll(username, email, todoId);
        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id) {
        CommentResponseDto responseDto = CommentResponseDto.of(commentService.findById(id));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.updateComment(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/comments/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @RequestBody CommentDeleteRequestDto requestDto) {
        commentService.deleteComment(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
