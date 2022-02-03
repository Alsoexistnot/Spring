package ru.geekbrains;

import ru.geekbrains.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(long id);

    void save(User user);

    void delete(long id);
}
