package ru.org.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.repository.WeatherForecastEntityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {
    private final WeatherForecastEntityRepository weatherForecastEntityRepository;

    @Transactional
    public List<WeatherForecastEntity> saveList(List<WeatherForecastEntity> weatherForecast) {
        return weatherForecastEntityRepository.saveAll(weatherForecast);
    }

    @Transactional(readOnly = true)
    public List<WeatherForecastEntity> findAll(@Param("city")String city) {
        return weatherForecastEntityRepository.findAllByCity(city);
    }
}
