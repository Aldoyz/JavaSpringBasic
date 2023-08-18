package com.aldiichsan.exception;

public class PlayerAlreadyExistsException extends Exception {
    public PlayerAlreadyExistsException() {super();}

    public PlayerAlreadyExistsException(String message) {
        super(message);
    }
}
