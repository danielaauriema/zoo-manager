package com.zoo;

import com.zoo.app.AnimalsFactory;
import com.zoo.utils.BoxUtils;
import com.zoo.utils.Reports;
import com.zoo.utils.UserInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@Getter
@AllArgsConstructor
public class Zookeeper {

    private final String name;
    private final Zoo zoo;

    private final AnimalsFactory factory = new AnimalsFactory();

    public void addAnimal(){

        val animal = factory.create();
        zoo.addAnimal(animal);
        UserInterface.println();
    }

    public void feedAnimals(){

        BoxUtils.print("Feed Animals");
        UserInterface.println();
        zoo.getLiveAnimals().forEach(this::feedAnimal);
    }

    public void healAnimals(){

        BoxUtils.print("Heal Animals");
        UserInterface.println();
        zoo.getLiveAnimals().forEach(this::healAnimal);
    }

    public void showAnimals(){

        zoo.showAnimals();
    }

    public void listAnimals() {

        zoo.listAnimals();
    }

    public void open(){

        zoo.open();
    }

    public void close(){

        zoo.close();
        printCloseReport();
    }

    private void feedAnimal(final Integer id, final Animal animal){

        Reports.feedAnimal(id, animal);
        boolean status;
        do {
            val amount = UserInterface.readInteger("Amount to feed");
            status = animal.eatFood(amount);
        } while (!status);
        UserInterface.println();
    }

    private void healAnimal(final Integer id, final Animal animal){

        Reports.healAnimal(id, animal);
        boolean status;
        do {
            val amount = UserInterface.readInteger("Medicine amount");
            status = animal.takeMedicine(amount);
        } while (!status);
        UserInterface.println();
    }

    private void printCloseReport(){

        Reports.closeReport(zoo);
    }
}
