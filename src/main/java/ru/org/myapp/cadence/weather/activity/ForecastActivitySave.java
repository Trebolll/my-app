package ru.org.myapp.cadence.weather.activity;

import com.uber.cadence.activity.ActivityMethod;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;

import java.util.List;

public interface ForecastActivitySave {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 10)
    List<WeatherForecastEntity> saveForecast(List<WeatherForecastEntity> weatherForecast);
}
