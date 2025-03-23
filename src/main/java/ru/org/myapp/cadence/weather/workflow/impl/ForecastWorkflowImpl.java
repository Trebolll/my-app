package ru.org.myapp.cadence.weather.workflow.impl;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.exception.WeatherCadenceException;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.cadence.weather.activity.ForecastActivity;
import ru.org.myapp.cadence.weather.activity.ForecastActivitySave;
import ru.org.myapp.cadence.weather.workflow.ForecastWorkflow;

import java.util.List;
import static ru.org.myapp.util.Constant.TASK_LIST_WEATHER_TRACKER;

@AllArgsConstructor
@NoArgsConstructor
public class ForecastWorkflowImpl implements ForecastWorkflow {

    private WeatherForecastMapper weatherForecastMapper;

    @Override
    public List<WeatherForecastDto> getForecastInfo(String city) {
        try {
            ForecastActivity getActivity = Workflow.newActivityStub(ForecastActivity.class,
                    new ActivityOptions.Builder().setTaskList(TASK_LIST_WEATHER_TRACKER)
                            .build());

            var saveActivity = Workflow.newActivityStub(ForecastActivitySave.class,
                    new ActivityOptions.Builder()
                            .setTaskList(TASK_LIST_WEATHER_TRACKER)
                            .build());

            return weatherForecastMapper.entityToDtoList(
                    saveActivity.saveForecast(
                            weatherForecastMapper.dtoToEntityList(getActivity.getForecastInfo(city))));

        } catch (RuntimeException e) {
            throw new WeatherCadenceException("Error getting forecast for city: " + city + " in class -> " + this.getClass().getName(), e);
        }
    }
}