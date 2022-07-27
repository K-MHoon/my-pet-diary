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
@ToString(exclude = {"owner", "diaryList", "refrigerator"})
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

    @OneToMany(mappedBy = "pet")
    private List<Diary> diaries = new ArrayList<>();

    public List<Diary> getDiaryList() {
        return Collections.unmodifiableList(diaries);
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    // TODO 가지고 있는 질병 목록
    // TODO 병원 이력 정보와 연관관계


    public void setAge(Long age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public void setAdoptedDate(LocalDateTime adoptedDate) {
        this.adoptedDate = adoptedDate;
    }
}
