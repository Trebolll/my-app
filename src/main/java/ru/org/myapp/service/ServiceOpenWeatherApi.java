package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.exception.WeatherServiceException;
import ru.org.myapp.mapper.IWeatherForecastMapper;
import ru.org.myapp.mapper.IWeatherMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOpenWeatherApi {
    private final OpenWeatherMapClient client;
    private final WeatherEntityService weatherEntityService;
    private final WeatherForecastService weatherForecastService;
    private final IWeatherMapper weatherMapper;
    private final IWeatherForecastMapper weatherForecastMapper;

    public WeatherDto getWeather(String city) {
        try {
            var weather = weatherEntityService.getWeatherByCity(city);
            if (weather != null) {
                return weatherMapper.EntityToDto(weather);
            } else {
                return weatherMapper.EntityToDto(
                        weatherEntityService.saveEntity(
                                client.currentWeather()
                                        .single()
                                        .byCityName(city)
                                        .language(Language.RUSSIAN)
                                        .unitSystem(UnitSystem.METRIC)
                                        .retrieve()
                                        .asJava()));
            }
        } catch (Exception e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }

    public List<WeatherForecastDto> getForecastInfo(String city) {
        try {
            var forecast = weatherForecastService.findAll(city);
            if (!forecast.isEmpty()) {
                return weatherForecastMapper.EntityToDtoList(forecast);
            } else {
                return weatherForecastMapper.EntityToDtoList(
                        weatherForecastService.saveList(
                                client.forecast5Day3HourStep()
                                        .byCityName(city)
                                        .language(Language.RUSSIAN)
                                        .unitSystem(UnitSystem.METRIC)
                                        .retrieve()
                                        .asJava()
                                        .getWeatherForecasts(), city));
            }
        } catch (Exception e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }
}

