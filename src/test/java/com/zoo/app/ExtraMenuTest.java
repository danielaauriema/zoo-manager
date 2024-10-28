package com.zoo.app;

import com.zoo.TestUtils;
import com.zoo.Zoo;
import com.zoo.Zookeeper;
import com.zoo.utils.UserInterface;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtraMenuTest {

    ExtraMenu unitToTest;

    @BeforeEach
    void setUp() {

        unitToTest = new ExtraMenu(new Zookeeper("zookeeperName", new Zoo()));
    }

    @Test
    void execute() {

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(in);

        val currentScanner = UserInterface.getScanner();
        UserInterface.setScanner(new Scanner(System.in));

        val result = TestUtils.getOutputString(() -> unitToTest.execute());

        System.setIn(sysInBackup);
        UserInterface.setScanner(currentScanner);

        assertTrue(result.contains("Extra Menu"));
        assertTrue(result.contains("1. Save Data "));
        assertTrue(result.contains("2. Load Data"));
        assertTrue(result.contains("0. Go back"));
    }
}