package com.example.todoexpert.comment.entity;

import com.example.todoexpert.comment.dto.request.CommentRequest;
import com.example.todoexpert.todo.entity.Todo;
import com.example.todoexpert.user.entity.User;
import com.example.todoexpert.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Comment(User findUser, Todo findTodo, String contents) {
        this.contents = contents;
        this.user = findUser;
        this.todo = findTodo;
    }

    public static Comment toEntity(User findUser, Todo findTodo, CommentRequest requestDto) {
        return new Comment(findUser, findTodo, requestDto.getContents());
    }

    public void updateComment(CommentRequest requestDto) {
        this.contents = requestDto.getContents();
    }
}
