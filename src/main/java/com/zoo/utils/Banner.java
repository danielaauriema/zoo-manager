package com.zoo.utils;

import lombok.val;

import java.io.InputStream;

import static com.zoo.utils.ColorUtils.ANSI_RESET;
import static com.zoo.utils.ColorUtils.ANSI_YELLOW;

public class Banner {

    public static void show(){

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("banner.asc");
            if (is != null) {
                val scanner = new java.util.Scanner(is);
                UserInterface.setColor(ANSI_YELLOW);
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
            }
        } finally {
            UserInterface.setColor(ANSI_RESET);
        }
    }
}
