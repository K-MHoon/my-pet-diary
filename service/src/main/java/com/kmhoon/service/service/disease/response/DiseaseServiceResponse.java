package com.kmhoon.service.service.disease.response;

import com.kmhoon.common.enums.DiseaseType;
import com.kmhoon.common.model.dto.PageInfo;
import com.kmhoon.common.model.entity.disease.Disease;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DiseaseServiceResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class GetDiseaseList {

        private PageInfo pageInfo;
        private List<DiseaseDetail> diseaseList = new ArrayList<>();

        public GetDiseaseList(Page<Disease> diseasePage) {
            this.pageInfo = new PageInfo(diseasePage);
            this.diseaseList = diseasePage.getContent().stream().map(DiseaseDetail::of).collect(Collectors.toList());
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class DiseaseDetail {

        private Long id;
        private DiseaseType type;
        private String name;
        private String code;

        public static DiseaseDetail of(Disease disease) {
            return DiseaseDetail.builder()
                    .id(disease.getId())
                    .type(disease.getType())
                    .name(disease.getName())
                    .code(disease.getCode())
                    .build();
        }
    }
}
