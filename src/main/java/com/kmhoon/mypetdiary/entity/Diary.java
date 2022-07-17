package com.kmhoon.mypetdiary.entity;

import com.kmhoon.mypetdiary.enums.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_diary")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO 사진/동영상 연관관계

    private String content;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    private LocalDate targetDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
