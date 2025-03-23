package ru.org.myapp.workflow.activity.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.exception.WeatherCadenceException;
import ru.org.myapp.service.ServiceOpenWeatherApi;
import ru.org.myapp.workflow.activity.ForecastActivity;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class ForecastActivityImpl implements ForecastActivity {

    private final ServiceOpenWeatherApi serviceOpenWeatherApi;


    @Override
    public List<WeatherForecastDto> getForecastInfo(String city) {
        try {
            return serviceOpenWeatherApi.getForecastInfo(city);
        } catch (RuntimeException e) {
            throw new WeatherCadenceException("Error getting forecast for city: " + city + " in class -> " + this.getClass().getName(), e);
        }
    }
}
