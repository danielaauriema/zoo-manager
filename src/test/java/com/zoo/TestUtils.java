package com.zoo;

import com.zoo.utils.ColorUtils;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUtils {

    public static String getOutputString(Executable executable) {

        return ColorUtils.clean(getRawOutputStream(executable).trim());
    }

    public static String getRawOutputStream(Executable executable) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream memoryStream = new PrintStream(byteArrayOutputStream);
        PrintStream oldOut = System.out;
        try {
            System.setOut(memoryStream);
            executable.execute();
        } catch (Throwable ignore){
        } finally {
            System.setOut(oldOut);
        }
        return byteArrayOutputStream.toString();
    }

    public static String getErrorOutputString(Executable executable) {

        return ColorUtils.clean(getRawErrorOutputStream(executable).trim());
    }

    public static String getRawErrorOutputStream(Executable executable) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream memoryStream = new PrintStream(byteArrayOutputStream);
        PrintStream oldErr = System.err;
        try {
            System.setErr(memoryStream);
            executable.execute();
        } catch (Throwable ignore){
        } finally {
            System.setErr(oldErr);
        }
        return byteArrayOutputStream.toString();
    }
}
