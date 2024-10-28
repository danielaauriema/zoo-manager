package com.zoo.app;

import com.zoo.Animal;
import com.zoo.animals.*;
import com.zoo.utils.UserInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum AnimalOption {
    ELEPHANT(1, "Elephant", Elephant.class),
    HORSE(2, "Horse", Horse.class),
    LION(3, "Lion", Lion.class),
    PEACOCK(4, "Peacock", Peacock.class),
    TIGER(5, "Tiger", Tiger.class),
    EXIT(0, "Go back", null);

    private final Integer id;
    private final String label;
    private final Class<? extends Animal> animalClass;

    public static Optional<AnimalOption> findById(final Integer id){

        return Arrays.stream(AnimalOption.values())
                .filter(o -> o.getId().equals(id))
                .findAny();
    }

    public static Optional<AnimalOption> findByLabel(final String label){

        return Arrays.stream(AnimalOption.values())
                .filter(o -> o.getLabel().equals(label))
                .findAny();
    }

    public boolean isExit(){

        return this.equals(EXIT);
    }

    public Animal newInstance(String name, Integer age, Integer hungerStatus, Integer healthStatus){
        try {
            return this.getAnimalClass()
                    .getConstructor(String.class, Integer.class, Integer.class, Integer.class)
                    .newInstance(name, age, hungerStatus, healthStatus);
        } catch (Exception e) {
            UserInterface.printError("Unable to create the animal: " + e.getMessage());
        }
        return null;
    }
}
