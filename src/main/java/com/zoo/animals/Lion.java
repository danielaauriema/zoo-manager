package com.zoo.animals;

import com.zoo.Animal;

public class Lion extends Animal {

    public Lion(String name, Integer age){

        super(name, age);
    }

    public Lion (final String name, final Integer age, final Integer hungerStatus, final Integer healthStatus){

        super(name, age, hungerStatus, healthStatus);
    }

    @Override
    public void speak() {

       speak("Lion growls");
    }
}
