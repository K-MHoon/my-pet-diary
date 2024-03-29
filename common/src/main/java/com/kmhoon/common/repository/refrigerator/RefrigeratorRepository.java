package com.kmhoon.common.repository.refrigerator;

import com.kmhoon.common.model.entity.pet.Pet;
import com.kmhoon.common.model.entity.refrigerator.Refrigerator;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {

    @EntityGraph(attributePaths = {"refrigeratorItemList"})
    Optional<Refrigerator> findByPet(@Param("pet") Pet pet);
}
