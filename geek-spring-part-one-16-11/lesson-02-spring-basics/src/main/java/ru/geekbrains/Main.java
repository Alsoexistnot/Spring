package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

public class Main {

    public static void main(String[] args) {
//        ProductRepository productRepository = new ProductRepositoryImpl();
//        ProductService productService = new ProductService(productRepository);

        // IOC container
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CartService cartService = context.getBean("cartService", CartService.class);
        CartService cartService1 = context.getBean("cartService", CartService.class);
        CartService cartService2 = context.getBean("cartService", CartService.class);

        System.out.println("Product count: " + cartService);
    }
}
