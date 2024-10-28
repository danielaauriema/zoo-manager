package com.zoo.animals;

import com.zoo.TestUtils;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElephantTest {

    Elephant unitToTest;

    @BeforeEach
    void setUp() {

        unitToTest = new Elephant("Animal Name", 10);
    }

    @Test
    void speak() {

        val result = TestUtils.getOutputString(() -> unitToTest.speak());
        assertEquals("## Elephant trumpets", result);
    }

    @Test
    void getSpecie() {

        assertEquals("Elephant", unitToTest.getSpecie());
    }
}