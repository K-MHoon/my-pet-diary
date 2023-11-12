package com.kmhoon.common.repository.disease;

import com.kmhoon.common.enums.DiseaseType;
import com.kmhoon.common.model.entity.disease.Disease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiseaseRepositorySupport {

    Page<Disease> findAllByCodeAndNameAndType(String code, String name, DiseaseType type, Pageable pageable);
}
