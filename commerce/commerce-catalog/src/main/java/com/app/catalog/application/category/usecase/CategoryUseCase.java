package com.app.catalog.application.category.usecase;

import com.app.catalog.application.category.dto.request.CreateCategoriesRequest;
import com.app.catalog.application.category.dto.request.CreateCategoryRequest;
import com.app.catalog.application.category.dto.response.GetCategoryResponse;
import java.util.List;

public interface CategoryUseCase {

    GetCategoryResponse createCategory(CreateCategoryRequest request);
    List<GetCategoryResponse> createCategories(CreateCategoriesRequest request);
}
