package com.zoo.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuOptionTest {

    MenuOption unitToTest;

    private boolean actionResult;

    @BeforeEach
    void setUp() {
        actionResult = false;
        unitToTest = new MenuOption(22, "MyLabel", (z) -> actionResult = true);
    }

    @Test
    void isExit() {
        assertFalse(unitToTest.isExit());
        unitToTest = new MenuOption(0, "MyLabel", (z) -> actionResult = true);
        assertTrue(unitToTest.isExit());
    }

    @Test
    void execute() {
        assertFalse(actionResult);
        unitToTest.execute(null);
        assertTrue(actionResult);
    }

    @Test
    void getId() {
        assertEquals(22, unitToTest.getId());
    }

    @Test
    void getLabel() {
        assertEquals("MyLabel", unitToTest.getLabel());
    }
}