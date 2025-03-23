package ru.org.myapp.workflow.activity.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.service.WeatherForecastService;
import ru.org.myapp.workflow.activity.ForecastActivitySave;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ForecastActivitySaveImpl implements ForecastActivitySave {

    private final WeatherForecastService weatherForecastService;

    @Override
    public List<WeatherForecastEntity> saveForecast(List<WeatherForecastEntity> weatherForecast) {
        return weatherForecastService.saveList(weatherForecast);
    }
}
