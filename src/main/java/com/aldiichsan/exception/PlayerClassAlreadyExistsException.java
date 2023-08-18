package com.aldiichsan.exception;

public class PlayerClassAlreadyExistsException extends Exception {
    public PlayerClassAlreadyExistsException() {super();}

    public PlayerClassAlreadyExistsException(String message) {
        super(message);
    }
}
