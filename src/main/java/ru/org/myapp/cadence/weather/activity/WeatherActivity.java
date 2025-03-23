package ru.org.myapp.cadence.weather.activity;

import com.uber.cadence.activity.ActivityMethod;
import ru.org.myapp.dto.WeatherDto;

public interface WeatherActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 10)
    WeatherDto getWeather(String city);
}
