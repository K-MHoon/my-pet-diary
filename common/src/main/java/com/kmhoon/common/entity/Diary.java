package com.kmhoon.common.entity;

import com.kmhoon.common.enums.Topic;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_diary")
@Getter
@ToString(exclude = {"pet"})
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO 사진/동영상 연관관계

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    private LocalDate targetDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
