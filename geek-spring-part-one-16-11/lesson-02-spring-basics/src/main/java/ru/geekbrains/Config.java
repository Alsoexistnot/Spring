package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

@ComponentScan("ru.geekbrains")
@Configuration
public class Config {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductService();
    }

    @Bean
    @Scope("prototype")
    public CartService cartService() {
        return new CartService(productRepository());
    }
}
