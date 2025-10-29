package com.app.catalog.v1.application.category.usecase;

import com.app.catalog.v1.application.category.dto.request.CreateCategoriesRequest;
import com.app.catalog.v1.application.category.dto.request.CreateCategoryRequest;
import com.app.catalog.v1.application.category.dto.response.GetCategoryResponse;
import java.util.List;

public interface CategoryUseCase {

    GetCategoryResponse createCategory(CreateCategoryRequest request);
    List<GetCategoryResponse> createCategories(CreateCategoriesRequest request);
}
