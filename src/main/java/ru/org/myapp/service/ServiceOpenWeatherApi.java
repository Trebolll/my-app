package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.exception.WeatherServiceException;
import ru.org.myapp.mapper.IWeatherForecastMapper;
import ru.org.myapp.mapper.IWeatherMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceOpenWeatherApi {
    private final OpenWeatherMapClient client;
    private final WeatherEntityService weatherEntityService;
    private final WeatherForecastService weatherForecastService;
    private final IWeatherMapper weatherMapper;
    private final IWeatherForecastMapper weatherForecastMapper;

    @Cacheable(value = "weather", key = "#city")
    public WeatherDto getWeather(String city) {
        try {
            return Optional.ofNullable(weatherEntityService.getWeatherByCity(city))
                    .map(weatherMapper::entityToDto)
                    .orElseGet(() -> weatherMapper.entityToDto(
                            weatherEntityService.saveEntity(
                                    weatherMapper.libToEntity(
                                            client.currentWeather()
                                                    .single()
                                                    .byCityName(city)
                                                    .language(Language.ENGLISH)
                                                    .unitSystem(UnitSystem.METRIC)
                                                    .retrieve()
                                                    .asJava()))));
        } catch (Exception e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }

    @Cacheable(value = "forecast", key = "#city")
    public List<WeatherForecastDto> getForecastInfo(String city) {
        try {
            return Optional.ofNullable(weatherForecastService.findAll(city))
                    .filter(forecast -> !forecast.isEmpty())
                    .map(weatherForecastMapper::entityToDtoList)
                    .orElseGet(() -> weatherForecastMapper.entityToDtoList(
                            weatherForecastService.saveList(
                                    weatherForecastMapper.libToEntityList(
                                            client.forecast5Day3HourStep()
                                                    .byCityName(city)
                                                    .language(Language.ENGLISH)
                                                    .unitSystem(UnitSystem.METRIC)
                                                    .retrieve()
                                                    .asJava()
                                                    .getWeatherForecasts(), city))));
        } catch (Exception e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }
}

