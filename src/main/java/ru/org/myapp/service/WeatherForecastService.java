package ru.org.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.repository.WeatherForecastEntityRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WeatherForecastService {
    private final WeatherForecastEntityRepository weatherForecastEntityRepository;
    public List<WeatherForecastEntity> saveList(List<WeatherForecastEntity> weatherForecast) {
      return  weatherForecastEntityRepository.saveAllAndFlush(weatherForecast);
    }
    public List<WeatherForecastEntity> findAll(String city) {
        return weatherForecastEntityRepository.findAllByCity(city);
    }
}
