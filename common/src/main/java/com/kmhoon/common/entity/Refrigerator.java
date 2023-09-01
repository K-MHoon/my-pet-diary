package com.kmhoon.common.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 반려동물이 먹은 사료/간식 저장소
 */
@Entity
@Table(name = "tb_refrigerator")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"pet"})
@Getter
public class Refrigerator extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "refrigerator", fetch = FetchType.LAZY)
    private Pet pet;

    // TODO Food 연관 관계, 간식, 사료는 타입에 따라 분류
}
