package com.kmhoon.service.service.disease;

import com.kmhoon.common.enums.DiseaseType;
import com.kmhoon.common.model.entity.disease.Disease;
import com.kmhoon.common.repository.disease.DiseaseRepository;
import com.kmhoon.service.service.disease.response.DiseaseServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Transactional(readOnly = true)
    public DiseaseServiceResponse.GetDiseaseList getPetDiseaseList(String code, String name, DiseaseType type, Pageable pageable) {
        Page<Disease> diseasePage = diseaseRepository.findAllByCodeAndNameAndType(code, name, type, pageable);
        return new DiseaseServiceResponse.GetDiseaseList(diseasePage);
    }
}
