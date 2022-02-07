package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.RoleRepository;
import ru.geekbrains.service.UserService;
import ru.geekbrains.service.dto.UserDto;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final RoleRepository roleRepository;

    private final UserService userService;

    @Autowired
    public UserController(RoleRepository roleRepository,
                          UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Secured({"ROLE_SUPER_ADMIN","ROLE_ADMIN"})
    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user";
    }

    @Secured({"ROLE_SUPER_ADMIN"})
    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        return "user_form";
    }

    @Secured({"ROLE_SUPER_ADMIN"})
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    @Secured({"ROLE_SUPER_ADMIN"})
    @PostMapping
    public String save(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            return "user_form";
        }
        userService.save(user);
        return "redirect:/user";
    }

    @Secured({"ROLE_SUPER_ADMIN"})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
