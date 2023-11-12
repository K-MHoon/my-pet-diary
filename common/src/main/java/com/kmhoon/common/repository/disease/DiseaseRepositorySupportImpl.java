package com.kmhoon.common.repository.disease;

import com.kmhoon.common.enums.DiseaseType;
import com.kmhoon.common.model.entity.disease.Disease;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.kmhoon.common.model.entity.disease.QDisease.disease;

public class DiseaseRepositorySupportImpl extends QuerydslRepositorySupport implements DiseaseRepositorySupport {

    public DiseaseRepositorySupportImpl() {
        super(Disease.class);
    }


    @Override
    public Page<Disease> findAllByCodeAndNameAndType(String code, String name, DiseaseType type, Pageable pageable) {
        List<Disease> content = from(disease)
                .where(searchByCode(code),
                        searchByName(name),
                        searchByType(type))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<Long> countQuery = from(disease)
                .select(disease.count())
                .where(searchByCode(code),
                        searchByName(name),
                        searchByType(type));

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchOne());
    }

    private BooleanExpression searchByCode(String code) {
        if(!StringUtils.hasText(code)) return null;
        return disease.code.contains(code);
    }

    private BooleanExpression searchByName(String name) {
        if(!StringUtils.hasText(name)) return null;
        return disease.name.contains(name);
    }

    private BooleanExpression searchByType(DiseaseType type) {
        if(Objects.isNull(type)) return null;
        return disease.type.eq(type);
    }
}
