package com.kmhoon.common.model.dto;

import lombok.*;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public final class PageInfo {

    private long totalSize;
    private int totalPage;
    private int number;
    private int size;

    public PageInfo(Page page) {
        this.totalSize = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.number = page.getNumber();
        this.size = page.getSize();
    }
}
