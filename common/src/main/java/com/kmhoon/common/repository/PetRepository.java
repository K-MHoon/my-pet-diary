package com.kmhoon.common.repository;

import com.kmhoon.common.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByOwnerId(Long ownerId);
}
