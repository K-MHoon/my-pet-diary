package com.kmhoon.service.controller.disease;

import com.kmhoon.common.enums.DiseaseType;
import com.kmhoon.service.service.disease.DiseaseService;
import com.kmhoon.service.service.disease.response.DiseaseServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user/disease")
public class DiseaseController {

    private final DiseaseService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DiseaseServiceResponse.GetDiseaseList getDiseaseList(@RequestParam(name = "code", required = false) String code,
                                                                @RequestParam(name = "name", required = false) String name,
                                                                @RequestParam(name = "type", required = false)DiseaseType diseaseType,
                                                                Pageable pageable) {
        return service.getPetDiseaseList(code, name, diseaseType, pageable);
    }
}
