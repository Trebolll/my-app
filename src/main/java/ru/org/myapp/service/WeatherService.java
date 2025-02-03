package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.org.myapp.exception.WeatherServiceException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final OpenWeatherMapClient client;

    public Weather getWeather(String city) {
        try { return client.currentWeather()
                    .single()
                    .byCityName(city)
                    .language(Language.RUSSIAN)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava();
        } catch (Exception e) {throw new WeatherServiceException(e.getMessage()); }}

    public List<WeatherForecast> getForecastInfo(String city) {
        try{ return client.forecast5Day3HourStep()
                    .byCityName(city)
                    .language(Language.RUSSIAN)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava()
                    .getWeatherForecasts();
        } catch (Exception e) {throw new WeatherServiceException(e.getMessage()); }}
}

