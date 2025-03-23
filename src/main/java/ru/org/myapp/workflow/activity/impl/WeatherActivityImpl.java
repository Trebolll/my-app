package ru.org.myapp.workflow.activity.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.exception.WeatherCadenceException;
import ru.org.myapp.service.ServiceOpenWeatherApi;
import ru.org.myapp.workflow.activity.WeatherActivity;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherActivityImpl implements WeatherActivity {

    private final ServiceOpenWeatherApi serviceOpenWeatherApi;

    @Override
    public WeatherDto getWeather(String city) {
        try {
            log.info("Getting weather for city: {}", city);
            return serviceOpenWeatherApi.getWeather(city);
        } catch (RuntimeException e) {
            log.error("Error getting weather for city: {}", city, e);
            throw new WeatherCadenceException("Error getting weather for city: " + city, e);
        }
    }
}
