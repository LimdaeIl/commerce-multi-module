package com.app.catalog.presentation.v1;

import com.app.catalog.application.category.dto.request.CreateCategoriesRequest;
import com.app.catalog.application.category.dto.request.CreateCategoryRequest;
import com.app.catalog.application.category.dto.response.GetCategoryResponse;
import com.app.catalog.application.category.usecase.CategoryUseCase;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/categories")
@RestController
public class CategoryController {

    private final CategoryUseCase categoryUsecase;

    @PostMapping
    public ResponseEntity<GetCategoryResponse> createCategory(
            @RequestBody @Valid CreateCategoryRequest request
    ) {
        GetCategoryResponse response = categoryUsecase.createCategory(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<GetCategoryResponse>> createCategories(
            @RequestBody @Valid CreateCategoriesRequest request
    ) {
        List<GetCategoryResponse> response = categoryUsecase.createCategories(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}