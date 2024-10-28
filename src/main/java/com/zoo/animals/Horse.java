package com.zoo.animals;

import com.zoo.Animal;

public class Horse extends Animal {

    public Horse(String name, Integer age){

        super(name, age);
    }

    public Horse (final String name, final Integer age, final Integer hungerStatus, final Integer healthStatus){

        super(name, age, hungerStatus, healthStatus);
    }

    @Override
    public void speak() {

       speak("Horse neighs");
    }
}
