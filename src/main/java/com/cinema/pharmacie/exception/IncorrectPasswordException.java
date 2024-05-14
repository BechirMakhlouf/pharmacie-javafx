package com.cinema.pharmacie.exception;

public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException() {
        super("Incorrect password");
    }
}