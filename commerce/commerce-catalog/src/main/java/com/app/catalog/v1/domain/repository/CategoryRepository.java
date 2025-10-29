package com.app.catalog.v1.domain.repository;

import com.app.catalog.v1.domain.entity.Category;
import java.util.List;

public interface CategoryRepository {

    Category save(Category category);

    List<Category> findByNameIn(List<String> name);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    boolean existsByName(String name);

    List<String> findExistingNames(List<String> names);
}
