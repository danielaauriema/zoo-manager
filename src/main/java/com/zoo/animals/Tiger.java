package com.zoo.animals;

import com.zoo.Animal;

public class Tiger extends Animal {

    public Tiger(String name, Integer age){
        super(name, age);
    }

    public Tiger (final String name, final Integer age, final Integer hungerStatus, final Integer healthStatus){

        super(name, age, hungerStatus, healthStatus);
    }

    @Override
    public void speak() {

       speak("Tiger growls");
    }
}
