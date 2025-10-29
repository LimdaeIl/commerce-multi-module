package com.app.catalog.infrastructure.jpa;

import com.app.catalog.domain.entity.Category;
import com.app.catalog.domain.repository.CategoryRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryJpaRepository extends JpaRepository<Category, Long>, CategoryRepository {

    @Query("select c.name from Category c where c.name in :names")
    List<String> findExistingNames(@Param("names") List<String> names);
}
