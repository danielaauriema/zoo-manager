package com.zoo.animals;

import com.zoo.TestUtils;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TigerTest {

    Tiger unitToTest;

    @BeforeEach
    void setUp() {

        unitToTest = new Tiger("Animal Name", 10);
    }

    @Test
    void speak() {

        val result = TestUtils.getOutputString(() -> unitToTest.speak());
        assertEquals("## Tiger growls", result);
    }

    @Test
    void getSpecie() {

        assertEquals("Tiger", unitToTest.getSpecie());
    }
}