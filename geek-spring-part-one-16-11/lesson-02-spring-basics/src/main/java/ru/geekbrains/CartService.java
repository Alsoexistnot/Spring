package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private ProductRepository productRepository;

    private Map<Product, Integer> productCount;

    @Autowired
    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productCount = new HashMap<>();
    }

    public void addProduct(long id, int count) {
        Product prod = getProductId(id);
        productCount.merge(prod, count, Integer::sum);
    }

    public void removeProduct(long id, int count) {
        Product prod = getProductId(id);
        Integer curr = productCount.get(prod);
        if (curr <= count) {
            productCount.remove(prod);
        } else {
            productCount.merge(prod, -count, Integer::sum);
        }
    }

    public List<Product> getAll() {
        return new ArrayList<>(productCount.keySet());
    }

    private Product getProductId(long id) {
        Product prod = productRepository.findById(id);
        if (prod == null) {
            throw new IllegalArgumentException("Product with id not exists");
        }
        return prod;
    }
}
