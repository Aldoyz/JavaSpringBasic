package com.aldiichsan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiExceptionHandling {
    public void ExceptionHandling (Exception e) {
        if (e instanceof DuplicateKeyException) {
            throw new ApiException.Conflict(e.getMessage());
        } else if (e instanceof NullPointerException) {
            throw new ApiException.NullPointer(e.getMessage());
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            throw new ApiException.OutOfBounds(e.getMessage());
        } else if (e instanceof ArithmeticException) {
            throw new ApiException.Arithmetic(e.getMessage());
        } else {
            log.error("Unexpected Error Happened : " + e.getMessage());
        }
    }
}
