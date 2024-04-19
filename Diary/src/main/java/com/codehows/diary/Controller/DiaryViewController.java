package com.codehows.diary.Controller;

import com.codehows.diary.Domain.Diary;
import com.codehows.diary.Dto.DiaryListViewResponse;
import com.codehows.diary.Dto.DiaryResponse;
import com.codehows.diary.Dto.DiaryViewResponse;
import com.codehows.diary.Service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DiaryViewController {
    private final DiaryService diaryService;

    @GetMapping("/diaries")
    public String getDiaries(Model model){
        List<DiaryListViewResponse> diaries = diaryService.findAll().stream()
                .map(DiaryListViewResponse::new)
                .toList();
        model.addAttribute("diaries", diaries);

        return "diaryList";
    }


    @GetMapping("/diaries/{id}")
    public String getDiary(@PathVariable Long id, Model model){
        Diary diary = diaryService.findById(id);
        model.addAttribute("diary", new DiaryViewResponse(diary));

        return "diary";
    }
}
