package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.entity.weather.WeatherEntity;
import ru.org.myapp.exception.WeatherServiceException;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.mapper.WeatherMapper;
import ru.org.myapp.workflow.activity.ForecastActivity;
import ru.org.myapp.workflow.activity.ForecastActivitySave;

import java.util.List;

import static ru.org.myapp.util.Constant.forecast;
import static ru.org.myapp.util.Constant.weather;

@Service
@RequiredArgsConstructor
public class ServiceOpenWeatherApi {
    private final OpenWeatherMapClient client;
    private final WeatherEntityService weatherEntityService;
    private final WeatherForecastService weatherForecastService;
    private final WeatherForecastMapper weatherForecastMapper;
    private final WeatherMapper weatherMapper;
    private final ForecastActivitySave forecastActivitySave;



    @Cacheable(value = weather, key = "#city")
    public WeatherDto getWeather(String city) {
        try {
            WeatherEntity weatherEntity = weatherEntityService.getWeatherByCity(city);
            if (weatherEntity != null) {
                return weatherMapper.entityToDto(weatherEntity);
            } else {
                return weatherMapper.entityToDto(
                        weatherEntityService.saveEntity(
                                weatherMapper.libToEntity(
                                        client.currentWeather()
                                                .single()
                                                .byCityName(city)
                                                .language(Language.ENGLISH)
                                                .unitSystem(UnitSystem.METRIC)
                                                .retrieve()
                                                .asJava())));
            }
        } catch (RuntimeException e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }

    @Cacheable(value = forecast, key = "#city")
    public List<WeatherForecastDto> getForecastInfo(String city) {
        try {
            List<WeatherForecastEntity> forecastEntities = weatherForecastService.findAll(city);
            if (!forecastEntities.isEmpty()) {
                return weatherForecastMapper.entityToDtoList(forecastEntities);
            } else {
                List<WeatherForecast> forecast = client.forecast5Day3HourStep()
                        .byCityName(city)
                        .language(Language.ENGLISH)
                        .unitSystem(UnitSystem.METRIC)
                        .retrieve()
                        .asJava()
                        .getWeatherForecasts();

                List<WeatherForecastEntity> savedForecastEntities = forecastActivitySave.saveForecast(weatherForecastMapper.libToEntityList(forecast, city));
                return weatherForecastMapper.entityToDtoList(savedForecastEntities);
            }
        } catch (RuntimeException e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }
}

