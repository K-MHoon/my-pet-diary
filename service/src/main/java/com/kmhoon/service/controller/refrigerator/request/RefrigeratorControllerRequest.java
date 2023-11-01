package com.kmhoon.service.controller.refrigerator.request;

import com.kmhoon.common.enums.ItemType;
import com.kmhoon.service.service.refrigerator.response.RefrigeratorServiceResponse;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefrigeratorControllerRequest {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class AddRefrigeratorItem {

        @NotNull(message = "{refrigerator.item-type.not-null}")
        private ItemType itemType;

        @NotBlank(message = "{refrigerator.name.not-blank}")
        private String name;

        @NotBlank(message = "{refrigerator.information.not-blank}")
        private String information;

        public RefrigeratorServiceResponse.RefrigeratorItemDto toServiceRequest() {
            return RefrigeratorServiceResponse.RefrigeratorItemDto.builder()
                    .itemType(this.itemType)
                    .name(this.name)
                    .information(this.information)
                    .build();
        }
    }
}
