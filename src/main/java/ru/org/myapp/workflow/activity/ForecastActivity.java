package ru.org.myapp.workflow.activity;

import com.uber.cadence.activity.ActivityMethod;
import ru.org.myapp.dto.WeatherForecastDto;

import java.util.List;

public interface ForecastActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 10)
    List<WeatherForecastDto> getForecastInfo(String city);
}
