package com.zoo.utils;

public class ColorUtils {

    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_GREEN = "\u001B[32m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_CYAN = "\u001B[36m";

    public static boolean colorEnabled = true;

    public static String clean(final String message){

        return message.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    public static void switchColorProfiles(){

        colorEnabled = !colorEnabled;
        if (colorEnabled) {
            ANSI_RESET = "\u001B[0m";
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_CYAN = "\u001B[36m";
        } else {
            ANSI_RESET = "";
            ANSI_RED = "";
            ANSI_GREEN = "";
            ANSI_YELLOW = "";
            ANSI_CYAN = "";
        }
    }

    public static void warningDisclaimer() {

        UserInterface.println("* ***************************************************");
        UserInterface.println("*    NOTES:                                         *");
        UserInterface.println("*    If text colors looks weird in your console,    *");
        UserInterface.println("*    or your console don't support colors at all,   *");
        UserInterface.println("*    you can disable the color profile by typing    *");
        UserInterface.println("*    9 (nine) in the main menu.                     *");
        UserInterface.println("* ***************************************************");
        UserInterface.println();
    }
}
