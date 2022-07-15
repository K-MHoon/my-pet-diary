package com.kmhoon.mypetdiary.repository;

import com.kmhoon.mypetdiary.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
