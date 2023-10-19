package com.kmhoon.common.repository;

import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.model.entity.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByOwnerId(Long ownerId);

    @EntityGraph(attributePaths = {"refrigerator"})
    Optional<Pet> findByIdAndIsUse(Long id, IsUse isUse);
}
