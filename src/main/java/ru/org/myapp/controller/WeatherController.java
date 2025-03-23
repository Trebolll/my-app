package ru.org.myapp.controller;

import com.example.config.annotation.Audit;
import com.example.config.annotation.Log;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.org.myapp.api.WeatherRestApi;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.common.Response;

import ru.org.myapp.entity.common.ResponseStatus;
import ru.org.myapp.exception.WeatherServiceException;
import ru.org.myapp.cadence.weather.workflow.ForecastWorkflow;
import ru.org.myapp.cadence.weather.workflow.WeatherWorkflow;

import java.util.List;

import static ru.org.myapp.util.Constant.TASK_LIST_WEATHER_TRACKER;

@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherRestApi {
    private final WorkflowClient workflowClient;

    @Audit
    @Log
    @Override
    public ResponseEntity<Response<WeatherDto>> getWeather(@RequestParam String city) {
        try {
            WeatherWorkflow workflowWeather = workflowClient.newWorkflowStub(WeatherWorkflow.class, new WorkflowOptions.Builder()
                    .setTaskList(TASK_LIST_WEATHER_TRACKER)
                    .build());
            WeatherDto weather = workflowWeather.getWeather(city);
            return ResponseEntity.ok(Response.<WeatherDto>builder().status(ResponseStatus.builder()
                    .code(HttpStatus.OK.value())
                    .description("Успешный запрос")
                    .build()).data(weather).build());
        } catch (WeatherServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Response.<WeatherDto>builder()
                            .status(ResponseStatus.builder()
                                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .description("Ошибка сервера: " + e.getMessage())
                                    .build()).data(null).build());
        }
    }
    @Audit
    @Log
    @Override
    public ResponseEntity<Response<List<WeatherForecastDto>>> getForecast(@RequestParam String city) {
        try {
          ForecastWorkflow forecastWorkflow = workflowClient.newWorkflowStub(ForecastWorkflow.class,
                  new WorkflowOptions.Builder()
                    .setTaskList(TASK_LIST_WEATHER_TRACKER)
                    .build());
            List<WeatherForecastDto> forecastInfo = forecastWorkflow.getForecastInfo(city);
            return ResponseEntity.ok(Response.<List<WeatherForecastDto>>builder().status(ResponseStatus.builder()
                                    .code(HttpStatus.OK.value())
                                    .description("Успешный запрос")
                                    .build())
                            .data(forecastInfo).build());
        } catch (WeatherServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Response.<List<WeatherForecastDto>>builder()
                            .status(ResponseStatus.builder()
                                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .description("Ошибка сервера: " + e.getMessage())
                                    .build()).data(null).build());
        }
    }
}

