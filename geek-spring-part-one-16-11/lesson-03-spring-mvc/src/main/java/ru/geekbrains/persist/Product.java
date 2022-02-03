package ru.geekbrains.persist;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private String name;

    // TODO
    private String description;

    // TODO
    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
