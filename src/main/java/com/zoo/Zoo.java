package com.zoo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zoo.exceptions.AnimalNotFoundException;
import com.zoo.utils.Reports;
import com.zoo.utils.UserInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.zoo.utils.ColorUtils.ANSI_GREEN;
import static com.zoo.utils.ColorUtils.ANSI_YELLOW;

@Getter
public class Zoo {

    //NOTE: cages is specified as "ArrayList", but using a Map object makes animals "id" to be the same even after removing any previous animal
    private final Map<Integer, Animal> cages = new TreeMap<>(Map.of());
    @JsonIgnore
    private boolean isOpen;
    @Setter
    private Integer animalSequence = 0;

    public Integer addAnimal (final Animal animal){

        if (animal == null){
            return -1;
        }
        cages.put(++animalSequence, animal);
        return animalSequence;
    }

    public Animal getAnimal(final Integer id){

            val animal = cages.get(id);
            if (animal == null){
                throw new AnimalNotFoundException("Id: %d", id);
            }
            return animal;
    }

    public void showAnimals(){

        Reports.showAnimals(cages);
    }

    public void listAnimals() {

        Reports.listAnimals(cages);
    }

    public void open(){

        cages.values().forEach(a -> a.open(isOpen));
        if (isOpen){
            UserInterface.colorPrintln(ANSI_YELLOW, "The zoo is already open.");
        } else {
            UserInterface.colorPrintln(ANSI_GREEN, "The zoo is now open!" );
        }
        UserInterface.println();
        isOpen = true;
    }

    public void close(){

        if (isOpen){
            UserInterface.colorPrintln(ANSI_GREEN,"The zoo is now closed." );
        } else {
            UserInterface.colorPrintln(ANSI_YELLOW,"The zoo is already closed!");
        }
        UserInterface.println();
        isOpen = false;
    }

    @JsonIgnore
    public Map<Integer, Animal> getLiveAnimals(){

        if (cages.isEmpty()){
            return Map.of();
        }
        return cages.entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getValue().isAlive()))
                .get(true)
                .stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public long countHealthyAnimals() {

        return cages.values()
                .stream()
                .filter(Animal::isHealthy)
                .count();
    }

    public long countFullyFedAnimals() {

        return cages.values()
                .stream()
                .filter(Animal::isFullyFed)
                .count();
    }

    public long countDeadAnimals() {

        return cages.values()
                .stream()
                .filter(Animal::isDead)
                .count();
    }
}
