package ru.org.myapp.controller;

import com.example.config.annotation.AuditExecution;
import com.example.config.annotation.LogExecution;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.weather.Weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.org.myapp.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    //http://localhost:8080/api/v1/weather?city=Novosibirsk
    @AuditExecution
    @LogExecution
    @GetMapping("/weather")
    public ResponseEntity<Weather> getWeather(@RequestParam String city) {
        return new ResponseEntity<>(weatherService.getWeather(city), HttpStatus.OK);
    }
 //http://localhost:8080/api/v1/forecast?city=Novosibirsk
    @AuditExecution
    @LogExecution
    @GetMapping("/forecast")
    public ResponseEntity<List<WeatherForecast>> getForecast(@RequestParam String city) {
        return new ResponseEntity<>(weatherService.getForecastInfo(city), HttpStatus.OK);
    }
}
