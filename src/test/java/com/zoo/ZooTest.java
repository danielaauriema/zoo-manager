package com.zoo;

import com.zoo.animals.Horse;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZooTest {

    Zoo unitToTest;

    private final String animalName = "Daisy";
    private final Integer animalAge = 4;

    @BeforeEach
    void setUp() {

        unitToTest = new Zoo();
        unitToTest.addAnimal(new Horse(animalName, animalAge));
    }

    @Test
    void addAnimal() {

        val animal = new Horse(animalName, animalAge);
        val id = unitToTest.addAnimal(animal);

        assertEquals(2, id);
        assertEquals(2, unitToTest.getCages().size());
    }

    @Test
    void getAnimal() {

        val result = unitToTest.getAnimal(1);
        assertEquals(Horse.class, result.getClass());
        assertEquals(animalName, result.getName());
        assertEquals(animalAge, result.getAge());
    }

    @Test
    void showAnimals() {

        val output = TestUtils.getRawOutputStream(() -> unitToTest.showAnimals());

        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(output);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        scanner.close();
        val result = sb.toString();

        assertTrue(result.contains("ID.......: 1"));
        assertTrue(result.contains("Name.....: " + animalName));
        assertTrue(result.contains("Specie...: Horse"));
        assertTrue(result.contains("Age......: " + animalAge));
        assertTrue(result.contains("Is alive.......: TRUE"));
    }
}