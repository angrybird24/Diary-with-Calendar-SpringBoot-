package com.codehows.diary.Dto;

import com.codehows.diary.Domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddDiaryRequest {
    private String title;
    private String content;
    private LocalDate start;

    public Diary toEntity(String author){
        return Diary.builder()
                .title(title)
                .content(content)
                .author(author)
                .start(start != null ? start : LocalDate.now())
                .build();
    }
}
