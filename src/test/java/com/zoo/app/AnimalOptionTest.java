package com.zoo.app;

import com.zoo.animals.Horse;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalOptionTest {

    @Test
    void findById() {

        assertEquals(AnimalOption.PEACOCK, AnimalOption.findById(4).orElse(null));
    }

    @Test
    void findByLabel() {
        assertEquals(AnimalOption.LION, AnimalOption.findByLabel("Lion").orElse(null));
    }

    @Test
    void isExit() {

        assertTrue(AnimalOption.EXIT.isExit());
        assertFalse(AnimalOption.HORSE.isExit());
    }

    @Test
    void newInstance() {

        val result = AnimalOption.HORSE.newInstance("name", 10, 4, 7);
        assertNotNull(result);
        assertEquals(Horse.class, result.getClass());
        assertEquals("name", result.getName());
        assertEquals(10, result.getAge());
        assertEquals(4, result.getHungerStatus());
        assertEquals(7, result.getHealthStatus());
    }
}