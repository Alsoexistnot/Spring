package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Repository
//public class ProductRepositoryImpl implements ProductRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public List<Product> findAll() {
//        return em.createQuery("select p from Product p", Product.class)
//                .getResultList();
//    }
//
//    @Override
//    public Optional<Product> findById(long id) {
//        return Optional.ofNullable(em.find(Product.class, id));
//    }
//
//    @Transactional
//    @Override
//    public Product save(Product product) {
//        if (product.getId() == null) {
//            em.persist(product);
//            return product;
//        }
//        return em.merge(product);
//    }
//
//    @Transactional
//    @Override
//    public void delete(long id) {
//        em.createQuery("delete from Product p where id = :id")
//                .setParameter("id", id)
//                .executeUpdate();
//    }
//}
