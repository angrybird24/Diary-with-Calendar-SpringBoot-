package com.codehows.diary.Dto;

import com.codehows.diary.Domain.Diary;
import lombok.Getter;

@Getter
public class DiaryResponse {
    private final String title;
    private final String content;

    public DiaryResponse(Diary diary){
        this.title=diary.getTitle();
        this.content= diary.getContent();
    }

}
