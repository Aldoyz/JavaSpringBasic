package com.aldiichsan.exception;

public class PlayerWeaponAlreadyExistsException extends Exception {
    public PlayerWeaponAlreadyExistsException() {super();}

    public PlayerWeaponAlreadyExistsException(String message) {
        super(message);
    }
}
