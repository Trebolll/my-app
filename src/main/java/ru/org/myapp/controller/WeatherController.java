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

import ru.org.myapp.entity.common.ResponseStatus;
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
            return ResponseEntity.ok(Response.<WeatherDto>builder().status(ResponseStatus.builder()
                                    .code(HttpStatus.OK.value())
                                    .description("Успешный запрос")
                                    .build()).data(serviceOpenWeatherApi.getWeather(city)).build());
        } catch (WeatherServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Response.<WeatherDto>builder()
                            .status(ResponseStatus.builder()
                                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .description("Ошибка сервера: " + e.getMessage())
                                    .build()).data(null).build());
        }
    }
    @Audit
    @Log
    @Override
    public ResponseEntity<Response<List<WeatherForecastDto>>> getForecast(@RequestParam String city) {
        try {
            return ResponseEntity.ok(Response.<List<WeatherForecastDto>>builder().status(ResponseStatus.builder()
                                    .code(HttpStatus.OK.value())
                                    .description("Успешный запрос")
                                    .build())
                            .data(serviceOpenWeatherApi.getForecastInfo(city)).build());
        } catch (WeatherServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Response.<List<WeatherForecastDto>>builder()
                            .status(ResponseStatus.builder()
                                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .description("Ошибка сервера: " + e.getMessage())
                                    .build()).data(null).build());
        }
    }
}

