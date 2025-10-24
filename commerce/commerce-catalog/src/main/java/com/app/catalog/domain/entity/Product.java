package com.app.catalog.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "v1_products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false)
    private Long id;

    private String name;

    private String description;

    private Integer price;

    private Integer stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> productCategories = new ArrayList<>();

    private Product(
            String name,
            String description,
            Integer price,
            Integer stock
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public static Product create(
            String name,
            String description,
            Integer price,
            Integer stock
    ) {
        return new Product(name, description, price, stock);
    }

    public void addCategory(Category category) {
        ProductCategory productCategory = ProductCategory.create(category);

        productCategory.markProduct(this);
        this.productCategories.add(productCategory);
    }

}
