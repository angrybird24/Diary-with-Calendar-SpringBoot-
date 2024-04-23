package com.codehows.diary.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "start" , nullable = false)
    private LocalDate start;

    @Column(name = "author" , nullable = false)
    private String author;

    @Builder
    public Diary( String author , String title, String content,LocalDate start){
        this.author = author;
        this.title =  title;
        this.content = content;
        this.start = start ;
    }

    public void update(String title, String content ){
        this.title = title;
        this.content= content;
    }

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
