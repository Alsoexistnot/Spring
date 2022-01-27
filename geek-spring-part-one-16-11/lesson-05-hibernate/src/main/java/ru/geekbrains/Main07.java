package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Customer;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class Main07 {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

//        em.createQuery(
//                        "select p " +
//                                "from Customer c " +
//                                "join c.lineItems li " +
//                                "join li.product p " +
//                                "where c.id = :customerId and " +
//                                " p.price >= :minPrice and" +
//                                " p.price <= :maxPrice")
//                .setParameter("customerId", 1L)
//                .setParameter("minPrice", 100)
//                .setParameter("maxPrice", 1000)
//                .getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Customer> root = query.from(Customer.class);

        Join<Object, Object> lineItems = root.join("lineItems", JoinType.INNER);
        Join<Object, Object> products = lineItems.join("product", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("id"), 1L));
        predicates.add(cb.ge(products.get("price"), 100));
        predicates.add(cb.le(products.get("price"), 1000));

        List<Product> product = em.createQuery(query.select(lineItems.get("product")).where(predicates.toArray(new Predicate[0])))
                .getResultList();

        em.close();
    }
}
