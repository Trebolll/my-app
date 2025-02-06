package ru.org.myapp.controller;

import com.example.config.annotation.AuditExecution;
import com.example.config.annotation.LogExecution;

import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.org.myapp.api.WeatherRestApi;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.service.ServiceOpenWeatherApi;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class WeatherController implements WeatherRestApi {

    private final ServiceOpenWeatherApi serviceOpenWeatherApi;

    @AuditExecution
    @LogExecution
    public ResponseEntity<WeatherDto> getWeather(String city) {
        return new ResponseEntity<>(serviceOpenWeatherApi.getWeather(city), HttpStatus.OK);
    }

    @AuditExecution
    @LogExecution
    public ResponseEntity<List<WeatherForecastDto>> getForecast(@RequestParam String city) {
        var sda = serviceOpenWeatherApi.getForecastInfo(city);
        return new ResponseEntity<>(sda, HttpStatus.OK);
    }
}
