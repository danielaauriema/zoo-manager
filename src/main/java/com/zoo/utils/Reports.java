package com.zoo.utils;

import com.zoo.Animal;
import com.zoo.Zoo;
import lombok.val;

import java.util.Map;
import java.util.Optional;

import static com.zoo.utils.ColorUtils.*;

public class Reports {

    public static void showAnimals(final Map<Integer, Animal> cages){

        BoxUtils.print("Show Animals");
        UserInterface.println();
        if (cages.isEmpty()){
            UserInterface.rawColorPrintln(ANSI_YELLOW, "** There's no animals in the zoo yet.");
            UserInterface.println();
        } else {
            cages.forEach(Reports::showAnimal);
        }
    }

    public static void listAnimals(final Map<Integer, Animal> cages) {

        BoxUtils.print("List Animals");
        UserInterface.println();
        if (cages.isEmpty()){
            UserInterface.rawColorPrintln(ANSI_YELLOW, "** There's no animals in the zoo yet.");
        } else {
            Reports.listHeader();
            cages.forEach(Reports::listAnimal);
        }
        UserInterface.println();
    }

    private static void showAnimal(Integer id, Animal animal){

        UserInterface.println("ID.......: %d", id);
        UserInterface.println("Specie...: %s", animal.getSpecie());
        UserInterface.println("Name.....: %s", animal.getName());
        UserInterface.println("Age......: %d", animal.getAge());
        UserInterface.println("Hunger status..: %d/%d", animal.getHungerStatus(), Animal.MAX_HUNGER_STATUS);
        UserInterface.println("Health status..: %d/%d", animal.getHealthStatus(), Animal.MAX_HEALTH_STATUS);
        UserInterface.println("Is alive.......: %B", animal.isAlive());
        UserInterface.println();
    }

    public static void feedAnimal(Integer id, Animal animal) {

        UserInterface.println("Feeding animal");
        UserInterface.println("ID...........: %d", id);
        UserInterface.println("Name.........: %s", animal.getName());
        UserInterface.println("Specie.......: %s", animal.getSpecie());
        UserInterface.println("Hunger Status: %d/%d", animal.getHungerStatus(), Animal.MAX_HUNGER_STATUS);
    }

    public static void healAnimal(final Integer id, final Animal animal) {

        UserInterface.println("Healing animal");
        UserInterface.println("ID...........: %d", id);
        UserInterface.println("Name.........: %s", animal.getName());
        UserInterface.println("Specie.......: %s", animal.getSpecie());
        UserInterface.println("Health Status: %d/%d", animal.getHealthStatus(), Animal.MAX_HEALTH_STATUS);
    }

    public static void closeReport(final Zoo zoo) {

        val healthAnimals = zoo.countHealthyAnimals();
        val fullyFed = zoo.countFullyFedAnimals();
        val deadAnimals = zoo.countDeadAnimals();
        val total = zoo.getCages().size();

        val liveAnimals = total - deadAnimals;

        BoxUtils.print("Close Report",0, ANSI_YELLOW);
        UserInterface.println();
        closeReportLine("Healthy..", healthAnimals, ANSI_GREEN);
        closeReportLine("Fully Fed", fullyFed, ANSI_GREEN);
        closeReportLine("Starving.", liveAnimals - fullyFed, ANSI_RED);
        closeReportLine("Unhealthy", liveAnimals - healthAnimals, ANSI_RED);
        closeReportLine("Dead.....", deadAnimals, ANSI_RED);
        UserInterface.println();
    }

    private static void closeReportLine(final String label, final long value, final String color){

        UserInterface.println("%s.: %s%d%s", label, value > 0 ? color : ANSI_RESET, value, ANSI_RESET);
    }

    private static void listHeader() {

        UserInterface.println("%-3s %-10s %-20s %-3s %-6s %-6s %-5s", "ID", "Specie", "Name", "Age", "Hunger", "Health", "Alive");
    }

    private static void listAnimal(final Integer id, Animal animal) {

        val name = Optional.of(animal)
                .map(Animal::getName)
                .map(s -> s.substring(0, Math.min(s.length(), 20)))
                .orElse("");

        UserInterface.println("%03d %-10s %-20s %03d   %02d    %02d    %-5B", id, animal.getSpecie(),
                name, animal.getAge(), animal.getHungerStatus(), animal.getHealthStatus(), animal.isAlive());
    }
}
