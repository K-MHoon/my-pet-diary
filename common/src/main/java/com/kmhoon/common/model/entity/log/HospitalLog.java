package com.kmhoon.common.model.entity.log;


import com.kmhoon.common.model.entity.BaseEntity;
import com.kmhoon.common.model.entity.place.hospital.Hospital;
import com.kmhoon.common.model.entity.pet.PetDisease;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pet_hospital_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public final class HospitalLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_disease_id")
    private PetDisease petDisease;

    private LocalDate visitDate;
}
