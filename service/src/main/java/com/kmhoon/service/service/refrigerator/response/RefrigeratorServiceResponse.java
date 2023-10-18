package com.kmhoon.service.service.refrigerator.response;

import com.kmhoon.common.enums.ItemType;
import com.kmhoon.common.model.entity.Refrigerator;
import com.kmhoon.common.model.entity.RefrigeratorItem;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RefrigeratorServiceResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class GetRefrigeratorDetail {

        private Long id;
        private List<RefrigeratorItemDto> refrigeratorItemDtoList;

        public static GetRefrigeratorDetail of(Refrigerator refrigerator) {
            List<RefrigeratorItemDto> itemDtoList = refrigerator.getRefrigeratorItemList().stream()
                    .map(RefrigeratorItemDto::of)
                    .collect(Collectors.toList());

            return GetRefrigeratorDetail.builder()
                    .id(refrigerator.getId())
                    .refrigeratorItemDtoList(itemDtoList)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class RefrigeratorItemDto {

        private Long id;
        private ItemType itemType;
        private String name;
        private String information;

        public static RefrigeratorItemDto of(RefrigeratorItem refrigeratorItem) {
            return RefrigeratorItemDto.builder()
                    .id(refrigeratorItem.getId())
                    .itemType(refrigeratorItem.getItemType())
                    .name(refrigeratorItem.getName())
                    .information(refrigeratorItem.getInformation())
                    .build();
        }
    }
}
