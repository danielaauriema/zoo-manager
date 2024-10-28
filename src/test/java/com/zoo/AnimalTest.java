package com.zoo;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalTest {

    Animal unitToTest;

    private final String name = "Legolas";
    private final Integer age = 10;

    private static class AnimalTestClass extends Animal {

        public AnimalTestClass(String name, Integer age, Integer hungerStatus, Integer healthStatus) {

            super(name, age, hungerStatus, healthStatus);
        }
    }

    @BeforeEach
    void setUp() {

        unitToTest = new AnimalTestClass(name, age, 3, 5);
    }

    @Test
    void getSpecie() {

        assertEquals("AnimalTestClass", unitToTest.getSpecie());
    }

    @Test
    void eatFood_toMax() {

        val currentHungerStatus = unitToTest.getHungerStatus();
        val amount = Animal.MAX_HUNGER_STATUS - currentHungerStatus;
        unitToTest.eatFood(amount);
        assertEquals(Animal.MAX_HUNGER_STATUS, unitToTest.getHungerStatus());
    }

    @Test
    void eatFood_overFed() {

        val currentHungerStatus = unitToTest.getHungerStatus();
        val amount = Animal.MAX_HUNGER_STATUS - currentHungerStatus + 1;
        val result = TestUtils.getErrorOutputString(() ->  unitToTest.eatFood(amount));
        assertTrue(result.contains("Overfed is not allowed!"));
    }

    @Test
    void takeMedicine_toMax() {

        val currentHealthStatus = unitToTest.getHealthStatus();
        val amount = Animal.MAX_HEALTH_STATUS - currentHealthStatus;
        unitToTest.takeMedicine(amount);
        assertEquals(Animal.MAX_HEALTH_STATUS, unitToTest.getHealthStatus());
    }

    @Test
    void takeMedicine_overMedicated() {

        val currentHealthStatus = unitToTest.getHealthStatus();
        val amount = Animal.MAX_HEALTH_STATUS - currentHealthStatus + 1;
        val result = TestUtils.getErrorOutputString(() -> unitToTest.takeMedicine(amount));
        assertTrue(result.contains("Over medication is not allowed!"));
    }

    @Test
    void speak() {

        val result = TestUtils.getOutputString(() -> unitToTest.speak());
        assertTrue(result.contains("make noise"));
    }

    @Test
    void getName() {

        assertEquals(name, unitToTest.getName());
    }

    @Test
    void getAge() {

        assertEquals(age, unitToTest.getAge());
    }

    @Test
    void open(){

        unitToTest = new AnimalTestClass(name, age, 1, 10);

        val result = TestUtils.getErrorOutputString(() -> unitToTest.open(false));
        assertTrue(result.contains("Legolas has starved to death!"));
    }

    @Test
    void open_alreadyOpen_notDie(){

        unitToTest = new AnimalTestClass(name, age, 2, 3);

        unitToTest.open(true);
        assertEquals(1, unitToTest.getHungerStatus());
        assertEquals(2, unitToTest.getHealthStatus());
    }

    @Test
    void open_shouldDieBySickness(){

        unitToTest = new AnimalTestClass(name, age, 3, 1);

        val result = TestUtils.getErrorOutputString(() ->unitToTest.open(true));
        assertEquals(2, unitToTest.getHungerStatus());
        assertEquals(0, unitToTest.getHealthStatus());
        assertTrue(unitToTest.isDead());
        assertTrue(result.contains("Legolas has died by sickness"));
    }
}