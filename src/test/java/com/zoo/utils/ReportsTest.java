package com.zoo.utils;

import com.zoo.TestUtils;
import com.zoo.Zoo;
import com.zoo.animals.Elephant;
import com.zoo.animals.Lion;
import com.zoo.animals.Peacock;
import com.zoo.animals.Tiger;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportsTest {

    @Test
    void showAnimals_empty() {

        val result = TestUtils.getOutputString(() -> Reports.showAnimals(Map.of()));
        assertTrue(result.contains("There's no animals in the zoo yet"));
    }

    @Test
    void showAnimals_withData() {

        Lion lion = new Lion("Simba", 10);
        val result = TestUtils.getOutputString(() -> Reports.showAnimals(Map.of(1, lion)));
        assertTrue(result.contains("ID.......: 1"));
        assertTrue(result.contains("Specie...: Lion"));
        assertTrue(result.contains("Name.....: Simba"));
    }

    @Test
    void listAnimals_empty() {

        val result = TestUtils.getOutputString(() -> Reports.listAnimals(Map.of()));
        assertTrue(result.contains("There's no animals in the zoo yet"));
    }

    @Test
    void listAnimals_withData() {

        Lion lion = new Lion("Simba", 10);
        val result = TestUtils.getOutputString(() -> Reports.listAnimals(Map.of(1, lion)));
        assertTrue(result.contains("001 Lion       Simba                010"));
    }

    @Test
    void feedAnimal() {

        Lion lion = new Lion("Simba", 10, 4, 7);
        val result = TestUtils.getOutputString(() -> Reports.feedAnimal(1, lion));
        assertTrue(result.contains("Feeding animal"));
        assertTrue(result.contains("ID...........: 1"));
        assertTrue(result.contains("Specie.......: Lion"));
        assertTrue(result.contains("Name.........: Simba"));
        assertTrue(result.contains("Hunger Status: 4/5"));
    }

    @Test
    void healAnimal() {

        Lion lion = new Lion("Simba", 10, 4, 7);
        val result = TestUtils.getOutputString(() -> Reports.healAnimal(1, lion));
        assertTrue(result.contains("Healing animal"));
        assertTrue(result.contains("ID...........: 1"));
        assertTrue(result.contains("Specie.......: Lion"));
        assertTrue(result.contains("Name.........: Simba"));
        assertTrue(result.contains("Health Status: 7/10"));
    }

    @Test
    void closeReport() {

        val zoo = new Zoo();
        zoo.addAnimal(new Lion("Simba", 10, 4, 10));
        zoo.addAnimal(new Peacock("Blue", 5, 5, 10));
        zoo.addAnimal(new Elephant("Gray", 15, 2, 7));
        zoo.addAnimal(new Tiger("Tig", 23, 4, 0));

        val result = TestUtils.getOutputString(() -> Reports.closeReport(zoo));

        assertTrue(result.contains("Close Report"));
        assertTrue(result.contains("Healthy...: 2"));
        assertTrue(result.contains("Fully Fed.: 1"));
        assertTrue(result.contains("Starving..: 2"));
        assertTrue(result.contains("Unhealthy.: 1"));
        assertTrue(result.contains("Dead......: 1"));
    }
}