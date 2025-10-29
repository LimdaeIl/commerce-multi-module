package com.app.catalog.application.category.dto.response;


import com.app.catalog.domain.entity.Category;

public record GetCategoryResponse(
        Long id,
        String name
) {
    public static GetCategoryResponse from(Category category) {
        return new GetCategoryResponse(category.getId(), category.getName());
    }
}
