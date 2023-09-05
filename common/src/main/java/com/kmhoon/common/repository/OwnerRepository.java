package com.kmhoon.common.repository;

import com.kmhoon.common.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
