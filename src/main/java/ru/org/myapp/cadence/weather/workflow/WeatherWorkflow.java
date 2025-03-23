package ru.org.myapp.cadence.weather.workflow;

import com.uber.cadence.workflow.WorkflowMethod;
import ru.org.myapp.dto.WeatherDto;

import static ru.org.myapp.util.Constant.TASK_LIST_WEATHER_TRACKER;

public interface WeatherWorkflow {
    @WorkflowMethod(
            executionStartToCloseTimeoutSeconds = 10,
            taskList = TASK_LIST_WEATHER_TRACKER
    )
    WeatherDto getWeather(String city);
}
