package ru.org.myapp.config;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherMapConfig {
    @Value("${open.weather.sdk.key}")
    private String key;

    @Bean
    public OpenWeatherMapClient openWeatherMapClient() {
        return new OpenWeatherMapClient(key);
    }
}
