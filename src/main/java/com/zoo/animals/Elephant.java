package com.zoo.animals;

import com.zoo.Animal;

public class Elephant extends Animal {

    public Elephant(String name, Integer age){

        super(name, age);
    }

    public Elephant (final String name, final Integer age, final Integer hungerStatus, final Integer healthStatus){

        super(name, age, hungerStatus, healthStatus);
    }

    @Override
    public void speak() {

       speak("Elephant trumpets");
    }
}
