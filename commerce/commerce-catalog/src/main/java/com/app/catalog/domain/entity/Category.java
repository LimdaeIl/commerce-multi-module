package com.app.catalog.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "v1_categories")
@Entity
public class Category {

    private static final String SEPARATOR = "/";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @Column(name = "path", nullable = false)
    private String path;

    private Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        this.path = parent.getPath() + SEPARATOR + name;
    }

    private Category(String name) {
        this.name = name;
        this.parent = null;
        this.path = name + SEPARATOR;
    }

    public static Category create(String name, Category parent) {
        if (parent == null) {
            return new Category(name);
        }
        return new Category(name, parent);
    }
}
