package com.kmhoon.mypetdiary.repository;

import com.kmhoon.mypetdiary.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
