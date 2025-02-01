package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.org.myapp.exception.WeatherServiceException;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final OpenWeatherMapClient client;

    public String getTemperature(String city) {
        try {
            return String.format("City: %s, %s", city, client.currentWeather().single()
                    .byCityName(city)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava()
                    .getTemperature().toString());
        } catch (RuntimeException e) {
            throw new WeatherServiceException("Exception in method");
        }
    }
}
