package ru.org.myapp.cadence.weather.workflow;

import com.uber.cadence.workflow.WorkflowMethod;
import ru.org.myapp.dto.WeatherForecastDto;

import java.util.List;

import static ru.org.myapp.util.Constant.TASK_LIST_WEATHER_TRACKER;

public interface ForecastWorkflow {
    @WorkflowMethod(
            executionStartToCloseTimeoutSeconds = 10,
            taskList = TASK_LIST_WEATHER_TRACKER
    )
    List<WeatherForecastDto> getForecastInfo(String city);
}
