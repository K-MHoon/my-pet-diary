package com.kmhoon.common.repository.diary;

import com.kmhoon.common.model.entity.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
