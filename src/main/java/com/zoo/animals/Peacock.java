package com.zoo.animals;

import com.zoo.Animal;

public class Peacock extends Animal {

    public Peacock(String name, Integer age){
        super(name, age);
    }

    public Peacock (final String name, final Integer age, final Integer hungerStatus, final Integer healthStatus){

        super(name, age, hungerStatus, healthStatus);
    }

    @Override
    public void speak() {

       speak("Peacock squawks");
    }
}
