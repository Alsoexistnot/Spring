package ru.geekbrains.service;

import ru.geekbrains.service.dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(UserDto user);

    void deleteById(Long id);
}
