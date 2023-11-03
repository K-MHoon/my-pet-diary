package com.kmhoon.common.repository.owner;

import com.kmhoon.common.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByEmail(String email);
}
