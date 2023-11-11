package com.kmhoon.common.model.entity.refrigerator;

import com.kmhoon.common.model.entity.BaseEntity;
import com.kmhoon.common.model.entity.pet.Pet;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 반려동물이 먹은 사료/간식 저장소
 */
@Entity
@Table(name = "tb_refrigerator")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString(exclude = {"pet"})
@Getter
public class Refrigerator extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "refrigerator", fetch = FetchType.LAZY)
    private Pet pet;

    @OneToMany(mappedBy = "refrigerator")
    @Builder.Default
    private List<RefrigeratorItem> refrigeratorItemList = new ArrayList<>();

    // TODO Food 연관 관계, 간식, 사료는 타입에 따라 분류
}
