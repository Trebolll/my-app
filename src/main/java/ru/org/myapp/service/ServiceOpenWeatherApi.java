package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.WeatherEntity;
import ru.org.myapp.exception.WeatherServiceException;
import ru.org.myapp.mapper.IWeatherMapper;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.mapper.WeatherMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOpenWeatherApi {
    private final OpenWeatherMapClient client;
    private final WeatherEntityService weatherEntityService;
    private final IWeatherMapper weatherMapper;

    public WeatherDto getWeather(String city) {
        try {
            var weather = client.currentWeather()
                    .single()
                    .byCityName(city)
                    .language(Language.RUSSIAN)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava();
            var weatherEntity = weatherEntityService.saveEntity(WeatherMapper.toEntity(weather));
            return weatherEntity;
        } catch (Exception e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }

    public List<WeatherForecastDto> getForecastInfo(String city) {
        try {
            return WeatherForecastMapper.toDtoList(client.forecast5Day3HourStep()
                    .byCityName(city)
                    .language(Language.RUSSIAN)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava()
                    .getWeatherForecasts());
        } catch (Exception e) {
            throw new WeatherServiceException(e.getMessage());
        }
    }
}

