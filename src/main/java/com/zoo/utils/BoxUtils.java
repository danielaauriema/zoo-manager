package com.zoo.utils;

import lombok.val;

import static com.zoo.utils.ColorUtils.ANSI_RESET;

public class BoxUtils {

    public static void print(final String message){

        print(message, 0, ANSI_RESET);
    }

    public static void print(final String message, final Integer lines, final String color){

        print(message, lines, 4, color);
    }

    public static void print(final String message, final Integer lines, final Integer pad, final String color){

        val padStr = " ".repeat(pad);
        val width = message.length() + 2 * pad + 2;

        UserInterface.println(lines);
        UserInterface.setColor(color);
        UserInterface.println("*".repeat(width));
        UserInterface.println("*%s%s%s*", padStr, message, padStr);
        UserInterface.println("*".repeat(width));
        UserInterface.setColor(ANSI_RESET);
        UserInterface.println(lines);
    }
}
