package ru.org.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.mapper.IWeatherForecastMapper;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.repository.WeatherForecastEntityRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WeatherForecastService {
    private final WeatherForecastEntityRepository weatherForecastEntityRepository;
    private final IWeatherForecastMapper weatherForecastMapper;

    public List<WeatherForecastDto> saveList(List<WeatherForecastEntity> weatherForecastEntityList) {
     return weatherForecastMapper.toDtoList(weatherForecastEntityRepository.saveAll(weatherForecastEntityList));
    }
}
