package com.codehows.diary.Service;

import com.codehows.diary.Domain.Diary;
import com.codehows.diary.Dto.AddDiaryRequest;
import com.codehows.diary.Dto.UpdateDiaryRequest;
import com.codehows.diary.Repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
 //일기장 글추가 메서드
    public Diary save(AddDiaryRequest request){
        return diaryRepository.save(request.toEntity());
    }

    public List<Diary> findAll(){
        return diaryRepository.findAll();
    }

    public Diary findById(long id){
        return diaryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id){
         diaryRepository.deleteById(id);
    }


    @Transactional
    public Diary update(long id, UpdateDiaryRequest request){
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not foound : " + id));

        diary.update(request.getTitle(), request.getContent());
        return diary;
    }
}
