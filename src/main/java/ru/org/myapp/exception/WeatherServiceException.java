package ru.org.myapp.exception;

public class WeatherServiceException extends RuntimeException{
    public WeatherServiceException(String message) {
        super(message);
    }
}
