package com.app.catalog.domain.repository;

import com.app.catalog.domain.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    List<Category> findByNameIn(List<String> name);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    Optional<Category> findByName(String name);

    boolean existsByName(String name);

    List<String> findExistingNames(List<String> names);
}
