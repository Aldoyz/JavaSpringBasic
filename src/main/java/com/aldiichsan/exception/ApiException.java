package com.aldiichsan.exception;

import com.aldiichsan.util.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ApiException {
    private final HttpStatus httpStatus;
    private final String timestamp;
    private final String message;

    public static class NullPointer extends NullPointerException {
        public NullPointer(String msg) {
            super(msg);
        }
    }

    public static class Conflict extends DuplicateKeyException {

        public Conflict(String msg) {
            super(msg);
        }

        public Conflict(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    public static class OutOfBounds extends ArrayIndexOutOfBoundsException {
        public OutOfBounds(String msg) {
            super(msg);
        }
    }

    public static class Arithmetic extends ArithmeticException {
        public Arithmetic(String msg) {
            super(msg);
        }
    }
}
