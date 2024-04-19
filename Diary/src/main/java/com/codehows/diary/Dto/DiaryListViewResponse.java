package com.codehows.diary.Dto;

import com.codehows.diary.Domain.Diary;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class DiaryListViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDate start;
    public DiaryListViewResponse(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.start = diary.getStart();
    }
}
