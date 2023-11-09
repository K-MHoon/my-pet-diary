package com.kmhoon.common.model.entity.pet;

import com.kmhoon.common.model.entity.BaseEntity;
import com.kmhoon.common.model.entity.disease.Disease;
import com.kmhoon.common.model.entity.pet.Pet;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pet_disease_map")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class PetDisease extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id")
    private Disease disease;

    // 병이 시작된 일자
    private LocalDate startDate;

    // 병이 종료된 일자
    private LocalDate endDate;
}
