package ru.org.myapp.exception

class WeatherServiceException(message: String) : RuntimeException("We sorry, now found not this: $message")
