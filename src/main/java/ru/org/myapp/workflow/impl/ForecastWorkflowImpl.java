package ru.org.myapp.workflow.impl;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.exception.WeatherCadenceException;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.workflow.ForecastWorkflow;
import ru.org.myapp.workflow.activity.ForecastActivity;
import ru.org.myapp.workflow.activity.ForecastActivitySave;

import java.util.List;

import static ru.org.myapp.util.Constant.TASK_LIST_WEATHER_TRACKER;

@Component
public class ForecastWorkflowImpl implements ForecastWorkflow {

    private WeatherForecastMapper weatherForecastMapper;

    public ForecastWorkflowImpl() {
    }
     @Autowired
    public ForecastWorkflowImpl(WeatherForecastMapper weatherForecastMapper) {
        this.weatherForecastMapper = weatherForecastMapper;
    }

    @Override
    public List<WeatherForecastDto> getForecastInfo(String city) {
        try {
            ForecastActivity activities = Workflow.newActivityStub(ForecastActivity.class,
                    new ActivityOptions.Builder().setTaskList(TASK_LIST_WEATHER_TRACKER)
                            .build());

            var saveActivities = Workflow.newActivityStub(ForecastActivitySave.class,
                    new ActivityOptions.Builder()
                            .setTaskList(TASK_LIST_WEATHER_TRACKER)
                            .build());

            return weatherForecastMapper.entityToDtoList(
                    saveActivities.saveForecast(
                            weatherForecastMapper.dtoToEntityList(activities.getForecastInfo(city))));

        } catch (RuntimeException e) {
            throw new WeatherCadenceException("Error getting forecast for city: " + city + " in class -> " + this.getClass().getName(), e);
        }
    }
}