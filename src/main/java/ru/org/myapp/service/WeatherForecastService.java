package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.mapper.IWeatherForecastMapper;
import ru.org.myapp.repository.WeatherForecastEntityRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WeatherForecastService {
    private final WeatherForecastEntityRepository weatherForecastEntityRepository;
    private final IWeatherForecastMapper weatherForecastMapper;
    public List<WeatherForecastEntity> saveList(List<WeatherForecast> weatherForecast,  String city) {
      return  weatherForecastEntityRepository.saveAllAndFlush(weatherForecastMapper.LibToEntityList(weatherForecast, city));
    }
    public List<WeatherForecastEntity> findAll(String city) {
        return weatherForecastEntityRepository.findAllByCity(city);
    }
}
