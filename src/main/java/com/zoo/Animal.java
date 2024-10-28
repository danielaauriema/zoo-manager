package com.zoo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zoo.utils.UserInterface;
import lombok.Getter;

import java.util.Random;

@Getter
public abstract class Animal {

    private final String name;
    private final Integer age;
    private Integer hungerStatus;
    private Integer healthStatus;

    private static final Random random = new Random();

    public static final Integer MAX_HUNGER_STATUS = 5;
    public static final Integer MAX_HEALTH_STATUS = 10;

    //NOTE: the Animal class is specified to initialize the object with default values, but has there's no purpose
    //      for an animal to have no "name" and "age", these values are initialized by the constructor params
    //      and marked as "final".
    //      The specie property is retrieved by the method "getSpecie()" and it's not stored because it is
    //      computed according to the instantiated class.
    public Animal (final String name, final Integer age){

        this(name, age,
                random.nextInt(MAX_HUNGER_STATUS) + 1,
                random.nextInt(MAX_HEALTH_STATUS) + 1);
    }

    public Animal (final String name, final Integer age, final Integer hungerStatus, final Integer healthStatus){

        this.name = name;
        this.age = age;
        this.hungerStatus = hungerStatus;
        this.healthStatus = healthStatus;
    }

    public String getSpecie(){

        return this.getClass().getSimpleName();
    }

    public boolean eatFood(final Integer amount){

        if (amount < 0) {
            UserInterface.printError("Invalid amount!");
            return false;
        }
        if (hungerStatus + amount > MAX_HUNGER_STATUS) {
            UserInterface.printError("Overfed is not allowed!");
            return false;
        }
        hungerStatus += amount;
        for (int i = 0; i < amount; i++){
            speak();
        }
        return true;
    }

    public boolean takeMedicine(final Integer amount){

        if (amount < 0) {
            UserInterface.printError("Invalid amount!");
            return false;
        }
        if (healthStatus + amount > MAX_HEALTH_STATUS) {
            UserInterface.printError("Over medication is not allowed!");
            return false;
        }
        healthStatus += amount;
        return true;
    }

    public void speak(){

        speak("make noise");
    }

    protected void speak(final String noise){

        UserInterface.rawPrintln("## " + noise);
    }

    @JsonIgnore
    public boolean isAlive(){

        return healthStatus > 0;
    }

    @JsonIgnore
    public boolean isDead(){

        return !isAlive();
    }

    @JsonIgnore
    public boolean isHealthy(){

        return healthStatus >= 8;
    }

    @JsonIgnore
    public boolean isFullyFed(){

        return MAX_HUNGER_STATUS.equals(hungerStatus);
    }

    @JsonIgnore
    public void open(boolean isOpen){

        if (isAlive()) {
            Random random = new Random();
            decreaseHangerStatus(isOpen ? 1 :  random.nextInt(2) + 1);
            if (isAlive()) {
                decreaseHealthStatus(isOpen ? 1 : random.nextInt(4) * (MAX_HUNGER_STATUS - hungerStatus));
            }
        }
    }

    private void killMe(final String message){

        healthStatus = 0;
        UserInterface.printError("%s %s has %s!", getSpecie(), name, message );
    }

    private void decreaseHangerStatus(final Integer amount){

        hungerStatus = Math.max(hungerStatus - amount, 0);
        if (hungerStatus == 0) {
            killMe("starved to death");
        }
    }

    private void decreaseHealthStatus(final Integer amount){

        healthStatus = Math.max(healthStatus - amount, 0);
        if (healthStatus == 0) {
            killMe("died by sickness");
        }
    }
}
