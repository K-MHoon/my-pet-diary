package com.kmhoon.common.repository.disease;

import com.kmhoon.common.model.entity.disease.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long>, DiseaseRepositorySupport {
}
