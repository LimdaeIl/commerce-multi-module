package com.app.catalog.application.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.hibernate.validator.constraints.UniqueElements;

public record CreateCategoriesRequest(
        @NotEmpty(message = "카테고리: names는 한 개 이상이어야 합니다.")
        @Size(max = 100, message = "카테고리: 한 번에 100개를 초과할 수 없습니다.")
        @UniqueElements(message = "카테고리: 입력된 카테고리명에서 중복된 카테고리명이 존재합니다.")
        List<
                @NotBlank(message = "카테고리: 각 name은 비어있을 수 없습니다.")
                @Size(max = 24, message = "카테고리: 각 name은 최대 24자 이하여야 합니다.")
                        String
                > names
) {

}