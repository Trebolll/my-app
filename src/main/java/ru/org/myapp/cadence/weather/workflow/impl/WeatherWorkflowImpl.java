package ru.org.myapp.cadence.weather.workflow.impl;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.exception.WeatherCadenceException;
import ru.org.myapp.cadence.weather.activity.WeatherActivity;
import ru.org.myapp.cadence.weather.workflow.WeatherWorkflow;

import static ru.org.myapp.util.Constant.TASK_LIST_WEATHER_TRACKER;

public class WeatherWorkflowImpl implements WeatherWorkflow {

    @Override
    public WeatherDto getWeather(String city) {
        try {
            WeatherActivity activities = Workflow.newActivityStub(WeatherActivity.class,
                    new ActivityOptions.Builder().setTaskList(TASK_LIST_WEATHER_TRACKER)
                            .build());
            return activities.getWeather(city);
        } catch (RuntimeException e) {
            throw new WeatherCadenceException("Error getting weather for city: " + city + " in class -> " + this.getClass().getName(), e);
        }
    }
}

