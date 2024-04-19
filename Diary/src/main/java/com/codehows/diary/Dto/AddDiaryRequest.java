package com.codehows.diary.Dto;

import com.codehows.diary.Domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddDiaryRequest {
    private String title;
    private String content;

    public Diary toEntity(){
        return Diary.builder()
                .title(title)
                .content(content)
                .build();
    }
}
