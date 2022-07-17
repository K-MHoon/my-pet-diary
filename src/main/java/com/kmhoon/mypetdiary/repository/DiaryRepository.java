package com.kmhoon.mypetdiary.repository;

import com.kmhoon.mypetdiary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
