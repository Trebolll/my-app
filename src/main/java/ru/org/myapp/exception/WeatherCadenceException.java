package ru.org.myapp.exception;

import java.io.Serial;

public class WeatherCadenceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public WeatherCadenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
