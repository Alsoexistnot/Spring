package ru.geekbrains.commands;

import org.springframework.stereotype.Component;
import ru.geekbrains.CartService;

import java.util.Scanner;

@Component
public class ShowCommand implements Command {

    @Override
    public String getName() {
        return "SHOW";
    }

    @Override
    public CartService execute(Scanner scn, CartService cartService) {
        cartService.getAll().forEach(System.out::println);
        return cartService;
    }
}
