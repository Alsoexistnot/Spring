package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.RoleRepository;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        return "user_form";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user_form";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
