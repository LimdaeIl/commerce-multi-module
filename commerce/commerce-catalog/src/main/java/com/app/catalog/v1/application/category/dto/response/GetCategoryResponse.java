package com.app.catalog.v1.application.category.dto.response;


import com.app.catalog.v1.domain.entity.Category;

public record GetCategoryResponse(
        Long id,
        String name
) {
    public static GetCategoryResponse from(Category category) {
        return new GetCategoryResponse(category.getId(), category.getName());
    }
}
