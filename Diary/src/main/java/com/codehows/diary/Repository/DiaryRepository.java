package com.codehows.diary.Repository;


import com.codehows.diary.Domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
}
