package ru.org.myapp.controller;

import com.example.config.annotation.Audit;
import com.example.config.annotation.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.org.myapp.api.WeatherRestApi;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.common.Response;

import ru.org.myapp.entity.common.StatusCode;
import ru.org.myapp.exception.WeatherServiceException;
import ru.org.myapp.service.ServiceOpenWeatherApi;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherRestApi {
    private final ServiceOpenWeatherApi serviceOpenWeatherApi;

    @Audit
    @Log
    @Override
    public ResponseEntity<Response<WeatherDto>> getWeather(@RequestParam String city) {
        try {
            return new ResponseEntity<>(new Response<>(new StatusCode(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase()), "Успешный запрос",
                    serviceOpenWeatherApi.getWeather(city)), HttpStatus.OK);

        } catch (WeatherServiceException e) {
            return new ResponseEntity<>(new Response<>(new StatusCode(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                    "Ошибка сервера: " + e.getMessage(),
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Audit
    @Log
    @Override
    public ResponseEntity<Response<List<WeatherForecastDto>>> getForecast(@RequestParam String city) {
        try {
            return new ResponseEntity<>(new Response<>(new StatusCode(
                            HttpStatus.OK.value(),
                            HttpStatus.OK.getReasonPhrase()),
                    "Успешный запрос",
                    serviceOpenWeatherApi.getForecastInfo(city)), HttpStatus.OK);

        } catch (WeatherServiceException e) {
            return new ResponseEntity<>(new Response<>(new StatusCode(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                    "Ошибка сервера: " + e.getMessage(),
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

