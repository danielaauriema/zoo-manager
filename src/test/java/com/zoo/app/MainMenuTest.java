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

class MainMenuTest {

    private MainMenu unitToTest;

    private Zookeeper zookeeper;
    private Zoo zoo;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        zookeeper = new Zookeeper("zookeeperName", zoo);
        unitToTest = new MainMenu(zookeeper);
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

        assertTrue(result.contains("Main Menu "));
        assertTrue(result.contains("1. Add animal          6. Open Zoo"));
        assertTrue(result.contains("2. View animals        7. Close Zoo"));
        assertTrue(result.contains("3. List animals        8. Extra Options"));
        assertTrue(result.contains("4. Feed animals        9. Switch Color Profile **"));
        assertTrue(result.contains("5. Heal animals        0. Exit"));
    }
}