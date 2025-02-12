package com.example.todoexpert.entity;

import com.example.todoexpert.dto.request.todo.TodoRequest;
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
@Table(name = "todo")
@Getter
@NoArgsConstructor
public class Todo extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Todo(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public static Todo toEntity(User findUser, TodoRequest requestDto) {
        return new Todo(findUser, requestDto.getTitle(), requestDto.getContents());
    }
    
    public void updateTodo(TodoRequest requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
