package ru.geekbrains.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "line_items")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @Column
    private BigDecimal price;

    @Column
    private Integer qty;

    public LineItem() {
    }

    public LineItem(Long id, Customer customer, Product product, BigDecimal price, Integer qty) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.price = price;
        this.qty = qty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
