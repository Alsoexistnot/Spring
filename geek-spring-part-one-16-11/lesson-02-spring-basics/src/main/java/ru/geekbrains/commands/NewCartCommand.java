package ru.geekbrains.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.CartService;

import java.util.Scanner;

@Component
public class NewCartCommand implements Command {

    @Autowired
    private CartService cartService;

    @Override
    public String getName() {
        return "NEW";
    }

    @Override
    public CartService execute(Scanner scn, CartService cart) {
        return cartService;
    }
}
