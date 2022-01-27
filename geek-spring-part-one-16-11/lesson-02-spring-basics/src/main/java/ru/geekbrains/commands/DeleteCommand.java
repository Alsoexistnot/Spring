package ru.geekbrains.commands;

import org.springframework.stereotype.Component;
import ru.geekbrains.CartService;

import java.util.Scanner;

@Component
public class DeleteCommand implements Command {

    @Override
    public String getName() {
        return "DELETE";
    }

    @Override
    public CartService execute(Scanner scn, CartService cartService) {
        System.out.println("Command is not fully implemented");
        return cartService;
    }
}
