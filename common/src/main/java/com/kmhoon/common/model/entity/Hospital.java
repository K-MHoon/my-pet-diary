package com.kmhoon.common.model.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tb_pet_hospital")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public final class Hospital extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이름
    private String name;

    // 운영 시간
    private LocalTime startHour;
    private LocalTime endHour;

    // 주소
    private String address;

}
