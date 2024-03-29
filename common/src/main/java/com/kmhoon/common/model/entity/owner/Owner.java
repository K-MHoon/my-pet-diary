package com.kmhoon.common.model.entity.owner;

import com.kmhoon.common.converter.gender.UserGenderConverter;
import com.kmhoon.common.converter.use.IsUseConverter;
import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.enums.UserGender;
import com.kmhoon.common.model.entity.BaseEntity;
import com.kmhoon.common.model.entity.pet.Pet;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString(exclude = {"pets"})
public class Owner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Convert(converter = UserGenderConverter.class)
    @Column(nullable = false)
    private UserGender gender;

    @Column(nullable = false)
    private String email;

    @Convert(converter = IsUseConverter.class)
    @ColumnDefault("'Y'")
    @Column(nullable = false)
    private IsUse isUse;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Pet> petList = new ArrayList<>();

    public List<Pet> getPetList() {
        return Collections.unmodifiableList(petList);
    }

    public void addPet(Pet pet) {
        if(Objects.isNull(pet)) return;
        this.petList.add(pet);
    }

    public void removePet(Pet pet) {
        if(Objects.isNull(pet)) return;
        this.petList.remove(pet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
