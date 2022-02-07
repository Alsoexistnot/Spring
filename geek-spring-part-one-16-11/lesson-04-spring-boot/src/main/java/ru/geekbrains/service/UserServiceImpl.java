package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.*;
import ru.geekbrains.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(convertToDto(user));
        }
        return userDtoList;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(UserServiceImpl::convertToDto);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getLogin(),
                encoder.encode(userDto.getPassword()),
                userDto.getRoles());
        User saved = userRepository.save(user);
        return convertToDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private static UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRoles()
        );
    }
}
