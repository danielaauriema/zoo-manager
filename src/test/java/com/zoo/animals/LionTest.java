package com.zoo.animals;

import com.zoo.TestUtils;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LionTest {

    Lion unitToTest;

    @BeforeEach
    void setUp() {

        unitToTest = new Lion("Animal Name", 10);
    }

    @Test
    void speak() {

        val result = TestUtils.getOutputString(() -> unitToTest.speak());
        assertEquals("## Lion growls", result);
    }

    @Test
    void getSpecie() {

        assertEquals("Lion", unitToTest.getSpecie());
    }
}