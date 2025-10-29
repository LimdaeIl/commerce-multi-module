package com.app.catalog.application.category.service;

import com.app.catalog.application.category.dto.request.CreateCategoriesRequest;
import com.app.catalog.application.category.dto.request.CreateCategoryRequest;
import com.app.catalog.application.category.dto.response.GetCategoryResponse;
import com.app.catalog.application.category.usecase.CategoryUseCase;
import com.app.catalog.domain.entity.Category;
import com.app.catalog.domain.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CategoryService implements CategoryUseCase {

    private final CategoryRepository categoryRepository;

    private void existsByName(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("Category with name " + name + " already exists");
        }
    }

    @Transactional
    @Override
    public GetCategoryResponse createCategory(CreateCategoryRequest request) {
        String name = request.name().trim();

        existsByName(name);

        Category category = Category.builder()
                .name(name)
                .build();

        return GetCategoryResponse.from(categoryRepository.save(category));
    }

    @Transactional
    @Override
    public List<GetCategoryResponse> createCategories(CreateCategoriesRequest request) {
        List<String> names = request.names().stream()
                .map(String::trim)
                .distinct()
                .toList();

        if (names.isEmpty()) {
            throw new IllegalArgumentException("Empty names are not allowed");
        }

        List<String> existingNames = categoryRepository.findExistingNames(names);
        if (!existingNames.isEmpty()) {
            String msg = String.format("이미 존재하는 카테고리: %s", String.join(", ", existingNames));
            throw new IllegalArgumentException(msg);
        }

        List<Category> list = names.stream()
                .map(Category::create)
                .toList();

        List<Category> savedAll = categoryRepository.saveAll(list);

        return savedAll.stream()
                .map(GetCategoryResponse::from)
                .toList();
    }
}
