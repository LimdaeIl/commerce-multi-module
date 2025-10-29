package com.app.catalog.application.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank(message = "카테고리: name은 비어있을 수 없습니다.")
        @Size(max = 24, message = "카테고리: name은 최대 24자 이상이어야 합니다.")
        String name
) {

}
