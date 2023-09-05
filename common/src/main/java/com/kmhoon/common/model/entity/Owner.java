package com.kmhoon.common.model.entity;

import com.kmhoon.common.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_owner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString(exclude = {"pets"})
public class Owner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public List<Pet> getPets() {
        return Collections.unmodifiableList(pets);
    }

    public void addPet(Pet pet) {
        if(Objects.isNull(pet)) return;
        this.pets.add(pet);
    }

    public void removePet(Pet pet) {
        if(Objects.isNull(pet)) return;
        this.pets.remove(pet);
    }
}
