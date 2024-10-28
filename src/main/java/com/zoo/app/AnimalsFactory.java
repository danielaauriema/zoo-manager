package com.zoo.app;

import com.zoo.Animal;
import com.zoo.utils.BoxUtils;
import com.zoo.utils.UserInterface;
import lombok.val;

import java.util.Arrays;
import java.util.Optional;

public class AnimalsFactory {

    public Animal create(){

        val option = chooseAnimal();
        if (option.isEmpty() || option.get().isExit()) {
            return null;
        }
        val name = UserInterface.readLine("Name");
        val age = UserInterface.readInteger("Age");
        try {
            return option.map(AnimalOption::getAnimalClass)
                    .orElseThrow()
                    .getConstructor(String.class, Integer.class)
                    .newInstance(name, age);
        } catch (Exception e) {
            UserInterface.printError("Unable to create the animal: " + e.getMessage());
        }
        return null;
    }

    private Optional<AnimalOption> chooseAnimal(){

        BoxUtils.print("Add New Animal");
        Arrays.stream(AnimalOption.values())
                .forEach(this::printMenuOption);
        UserInterface.println();
        Optional<AnimalOption> option = Optional.empty();
        while (option.isEmpty() ){
            val input = UserInterface.readInteger("Choose one animal type to add");
            option = AnimalOption.findById(input);
            if (option.isEmpty()) {
                UserInterface.printError("Invalid option!");
            }
        }
        return option;
    }

    private void printMenuOption(final AnimalOption option){

        UserInterface.println("%2d. %s", option.getId(), option.getLabel());
    }
}
