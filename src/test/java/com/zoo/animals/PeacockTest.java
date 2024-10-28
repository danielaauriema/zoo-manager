package com.zoo.animals;

import com.zoo.TestUtils;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeacockTest {

    Peacock unitToTest;

    @BeforeEach
    void setUp() {

        unitToTest = new Peacock("Animal Name", 10);
    }

    @Test
    void speak() {

        val result = TestUtils.getOutputString(() -> unitToTest.speak());
        assertEquals("## Peacock squawks", result);
    }

    @Test
    void getSpecie() {

        assertEquals("Peacock", unitToTest.getSpecie());
    }
}