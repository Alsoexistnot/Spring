package ru.geekbrains.commands;

import ru.geekbrains.CartService;

import java.util.Scanner;

public interface Command {

    String getName();

    CartService execute(Scanner scn, CartService cartService);
}
