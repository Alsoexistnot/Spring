package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Contact;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main06 {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        // INSERT 1
//        em.getTransaction().begin();
//
//        User user = new User(null, "ivan", "pass123");
//        em.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact(null, "home phone", "1444467", user));
//        contacts.add(new Contact(null, "work phone", "7677881", user));
//        contacts.add(new Contact(null, "mobile phone", "5566564", user));
//        contacts.add(new Contact(null, "email", "ivan@mail.eu", user));
//        contacts.forEach(em::persist);
//
//        em.getTransaction().commit();

        // INSERT 2 (with cascade)
//        em.getTransaction().begin();
//
//        User user = em.find(User.class, 1L);
//        user.getContacts().add(new Contact(null, "home address", "NewTown, HomeStreet, 123/4", user));
//        em.merge(user);
//
//        em.getTransaction().commit();
        // SELECT 1
//        List<User> users = em.createQuery("" +
//                        "select distinct u " +
//                        "from User u " +
//                        "inner join u.contacts " +
//                        "where u.id = :id", User.class)
//                .setParameter("id", 1L)
//                .getResultList();
//        //System.out.println("Users: " + users);
//
//        users.forEach(System.out::println);

        // SELECT 2 (N+1 problem)
//        List<Contact> contacts = em.createQuery(
//                        "select distinct c " +
//                                "from Contact c " +
//                                "join fetch c.user " +
//                                "where c.type = :type", Contact.class)
//                .setParameter("type", "home phone")
//                .getResultList();
//        contacts.forEach(System.out::println);
        // DELETE 1
//        em.getTransaction().begin();
//
//        em.createQuery("delete from Contact c where c.id = :id")
//                .setParameter("id", 1L)
//                .executeUpdate();
//
//        em.getTransaction().commit();

        // DELETE 2 - with orphanRemoval = true
//        em.getTransaction().begin();
//
//        User user = em.find(User.class, 1L);
//
//        user.getContacts().remove(0);
//        em.merge(user);
//
//        em.getTransaction().commit();
        // DELETE 3 - cascade delete
//        em.getTransaction().begin();
//
//        User user = em.find(User.class, 3L);
//        em.remove(user);
//
//        em.getTransaction().commit();

        em.close();
    }
}
