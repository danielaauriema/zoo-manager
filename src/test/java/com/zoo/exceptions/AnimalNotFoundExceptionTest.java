package com.zoo.exceptions;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalNotFoundExceptionTest {

    @Test
    void testConstructor() {

        val ex = assertThrows(AnimalNotFoundException.class, () -> {throw new AnimalNotFoundException("%s=%s", "KEY", "VALUE");});
        assertEquals("Animal not found. KEY=VALUE", ex.getMessage());
    }
}