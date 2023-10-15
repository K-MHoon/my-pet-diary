package com.kmhoon.common.model.entity;

import com.kmhoon.common.enums.ItemType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_refrigerator_item")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class RefrigeratorItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType itemType;

    // 이름
    @Column(nullable = false)
    private String name;

    // Item 정보
    @Column(nullable = false)
    private String information;
}
