package com.zoo;

import com.zoo.utils.UserInterface;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ZooManagerTest {

    ZooManager unitToTest;

    @BeforeEach
    void setUp() {
        unitToTest = new ZooManager();
    }

    @Test
    void run() {

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("zooUser\n0\n".getBytes());
        System.setIn(in);

        val currentScanner = UserInterface.getScanner();
        UserInterface.setScanner(new Scanner(System.in));

        val result = TestUtils.getOutputString(() -> unitToTest.run());

        System.setIn(sysInBackup);
        UserInterface.setScanner(currentScanner);

        assertTrue(result.contains("Welcome to Zoo Management System"));
        assertTrue(result.contains("Main Menu"));
        assertTrue(result.contains("Goodbye zooUser & Thanks for use Zoo Management Syste"));
    }
}