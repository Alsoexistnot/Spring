package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.annotation.PostConstruct;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        productRepository.save(new Product(null, "Product 1"));
        productRepository.save(new Product(null, "Product 2"));
        productRepository.save(new Product(null, "Product 3"));
    }

    public long count() {
        return productRepository.findAll().size();
    }
}
