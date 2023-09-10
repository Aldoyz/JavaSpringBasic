package com.aldiichsan.exception;

import org.springframework.dao.DuplicateKeyException;

public class AlreadyExistsException extends DuplicateKeyException {
    public AlreadyExistsException(String msg) {
        super(msg);
    }

    public AlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
