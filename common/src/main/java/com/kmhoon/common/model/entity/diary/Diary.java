package com.kmhoon.common.model.entity.diary;

import com.kmhoon.common.converter.use.IsUseConverter;
import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.enums.Topic;
import com.kmhoon.common.model.entity.BaseEntity;
import com.kmhoon.common.model.entity.pet.Pet;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_diary")
@Getter
@ToString(exclude = {"pet"})
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO 사진/동영상 연관관계

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    private LocalDate targetDate;

    @Convert(converter = IsUseConverter.class)
    @ColumnDefault("'Y'")
    @Column(nullable = false)
    private IsUse isUse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
