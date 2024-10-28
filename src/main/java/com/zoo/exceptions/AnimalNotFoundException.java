package com.zoo.exceptions;

public class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException (final String message){

        super("Animal not found. " + message);
    }

    public AnimalNotFoundException (final String message, final Object... args){

        this (String.format(message, args));
    }
}
