package com.kmhoon.common.repository;

import com.kmhoon.common.model.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}