package com.codehows.diary.Controller;

import com.codehows.diary.Domain.Diary;
import com.codehows.diary.Dto.AddDiaryRequest;
import com.codehows.diary.Dto.DiaryResponse;
import com.codehows.diary.Dto.UpdateDiaryRequest;
import com.codehows.diary.Repository.DiaryRepository;
import com.codehows.diary.Service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryApiController {
    private final DiaryService diaryService;

    @PostMapping("/api/diaries")
    //@Request body 로 요청 본문 값 매핑
    public ResponseEntity<Diary> addDiary(@RequestBody AddDiaryRequest request){
        Diary savedDiary = diaryService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedDiary);
    }

    @GetMapping("/api/diaries")
    public ResponseEntity<List<DiaryResponse>> findAllDiaries(){
        List<DiaryResponse> diaries = diaryService.findAll()
                .stream()
                .map(DiaryResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(diaries);
    }

    @GetMapping("/api/diaries/{id}")
    public ResponseEntity<DiaryResponse> findDiary(@PathVariable long id){
        Diary diary = diaryService.findById(id);

        return ResponseEntity.ok()
                .body(new DiaryResponse(diary));
    }


    @DeleteMapping("/api/diaries/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable long id){
        diaryService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/diaries/{id}")
    public ResponseEntity<Diary> updateDiary(@PathVariable long id,
                                             @RequestBody UpdateDiaryRequest request){
        Diary updatedDiary = diaryService.update(id,request);

        return ResponseEntity.ok()
                .body(updatedDiary);
    }
}
