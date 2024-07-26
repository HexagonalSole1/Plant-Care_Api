package com.example.APISkeleton.web.exceptions.Plants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UniqueConstraintViolationException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;


    public UniqueConstraintViolationException(String message) {
        super(message);
    }

    public UniqueConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
