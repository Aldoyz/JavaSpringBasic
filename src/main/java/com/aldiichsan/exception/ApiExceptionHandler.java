package com.aldiichsan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@ControllerAdvice
public class ApiExceptionHandler {

    long currentMillis = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(currentMillis);
    String formattedTimestamp = sdf.format(date);

    @ExceptionHandler(value = ApiException.Conflict.class)
    public ResponseEntity<Object> handleAlreadyExistsException(ApiException.Conflict e) {
        HttpStatus status = HttpStatus.CONFLICT;

        ApiException ex = new ApiException(
                status,
                formattedTimestamp,
                e.getMessage()
        );
        return new ResponseEntity<>(ex, status);
    }

    @ExceptionHandler(value = ApiException.NullPointer.class)
    public ResponseEntity<Object> handleNullPointerException(ApiException.NullPointer e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiException ex = new ApiException(
                status,
                formattedTimestamp,
                e.getMessage()
        );
        return new ResponseEntity<>(ex, status);
    }

    @ExceptionHandler(value = ApiException.OutOfBounds.class)
    public ResponseEntity<Object> handleOutOfBoundsException(ApiException.OutOfBounds e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiException ex = new ApiException(
                status,
                formattedTimestamp,
                e.getMessage()
        );
        return new ResponseEntity<>(ex, status);
    }

    @ExceptionHandler(value = ApiException.Arithmetic.class)
    public ResponseEntity<Object> handleArithmeticException(ApiException.Arithmetic e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiException ex = new ApiException(
                status,
                formattedTimestamp,
                e.getMessage()
        );
        return new ResponseEntity<>(ex, status);
    }
}
