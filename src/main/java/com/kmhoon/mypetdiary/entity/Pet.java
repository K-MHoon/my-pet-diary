package com.kmhoon.mypetdiary.entity;

import com.kmhoon.mypetdiary.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tb_pet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Pet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long age;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double weight;

    private String species;

    private String registeredNumber;

    private Boolean live;

    private LocalDateTime adoptedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "diary")
    private List<Diary> diaryList = new ArrayList<>();

    public List<Diary> getDiaryList() {
        return Collections.unmodifiableList(diaryList);
    }

    // TODO 가지고 있는 질병 목록
    // TODO 병원 이력 정보와 연관관계
}
