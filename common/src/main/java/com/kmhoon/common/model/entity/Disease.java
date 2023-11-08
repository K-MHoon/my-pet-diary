package com.kmhoon.common.model.entity;

import com.kmhoon.common.enums.DiseaseType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_pet_disease")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public final class Disease extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = DiseaseType.class)
    @Column(name = "type", nullable = true)
    private DiseaseType type;

    private String name;

    private String code;
}
