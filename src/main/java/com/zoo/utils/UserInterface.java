package com.zoo.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.util.Scanner;

import static com.zoo.utils.ColorUtils.ANSI_RED;
import static com.zoo.utils.ColorUtils.ANSI_RESET;

public class UserInterface {

    private static String margin = " ".repeat(3);

    @Getter
    @Setter
    private static Scanner scanner = new Scanner(System.in);

    public static void setColor(final String color){

        System.out.print(color);
    }

    public static void println(){

        System.out.println();
    }

    public static void println(final Integer count){

        System.out.printf("%n".repeat(count));
    }

    public static void println(final String msg){

        System.out.println(margin + msg);
    }

    public static void println(final String msg, final Object... args){

        System.out.printf(margin + msg + "%n", args);
    }

    public static void colorPrintln(final String color, final String msg){

        setColor(color);
        println(msg);
        setColor(ANSI_RESET);
    }

    public static void colorPrintln(final String color, final String msg, final Object... args){

        colorPrintln(color, margin + String.format(msg, args));
    }

    public static void printf(final String format, final Object... args){

        System.out.printf(margin + format, args);
    }

    public static void rawPrintln(final String message) {

        System.out.println(message);
    }

    public static void rawColorPrintln(final String color, final String msg){

        setColor(color);
        rawPrintln(msg);
        setColor(ANSI_RESET);
    }

    public static String readLine(final String prompt){

        String input = "";
        while (input.isEmpty()){
            System.out.print(">> " + prompt + ": ");
            input = scanner.nextLine();
        }
        return input;
    }

    public static Integer readInteger(final String prompt) {

        while (true){
            System.out.print(">> " + prompt + ": ");
            try {
                val str = scanner.nextLine();
                val value = Integer.parseInt(str);
                if (value < 0) {
                    printError("Invalid input!");
                } else {
                    return value;
                }
            } catch (Exception e){
                printError("Invalid input!");
            }
        }
    }

    public static void printError(final String message) {

        setColor(ANSI_RED);
        System.err.println("** " + message);
        setColor(ANSI_RESET);
        println();
    }

    public static void printError(final String message, final Object... args) {

        printError(String.format(message, args));
    }
}
