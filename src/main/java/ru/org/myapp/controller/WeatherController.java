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
import ru.org.myapp.service.ServiceOpenWeatherApi;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherRestApi {
    private final ServiceOpenWeatherApi serviceOpenWeatherApi;
    @Audit
    @Log
    @Override
    public ResponseEntity<WeatherDto> getWeather(String city) {
        return new ResponseEntity<>(serviceOpenWeatherApi.getWeather(city), HttpStatus.OK);
    }
    @Audit
    @Log
    @Override
    public ResponseEntity<List<WeatherForecastDto>> getForecast(@RequestParam String city) {
        return new ResponseEntity<>(serviceOpenWeatherApi.getForecastInfo(city), HttpStatus.OK);
    }
}
