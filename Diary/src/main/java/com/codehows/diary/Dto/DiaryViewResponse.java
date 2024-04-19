
package com.codehows.diary.Dto;

import com.codehows.diary.Domain.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class DiaryViewResponse {
    private  Long id;
    private  String title;
    private  String content;
    private LocalDateTime createdAt;
    public DiaryViewResponse(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.createdAt= diary.getCreatedAt();
    }
}
