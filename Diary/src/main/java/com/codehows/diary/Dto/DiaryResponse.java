package com.codehows.diary.Dto;

import com.codehows.diary.Domain.Diary;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DiaryResponse {
    private final String title;
    private final String content;
    private final LocalDate start;


    public DiaryResponse(Diary diary){
        this.title=diary.getTitle();
        this.content= diary.getContent();
        this.start=diary.getStart();
    }

}
