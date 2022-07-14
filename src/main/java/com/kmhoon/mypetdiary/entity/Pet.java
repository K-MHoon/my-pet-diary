package com.kmhoon.mypetdiary.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long age;

    private Long gender;

    private Double weight;

    private String species;

    private String registeredNumber;

    private Boolean live;

    @Column(updatable = false)
    private LocalDateTime adoptedDate;

    // TODO 가지고 있는 질병 목록
    // TODO 주인(사용자)과의 연관관계 @ManyToOne
    // TODO 산책 이력 정보와 연관관계
    // TODO 병원 이력 정보와 연관관계
    // TODO 다이어리 정보와 연관관계
}
