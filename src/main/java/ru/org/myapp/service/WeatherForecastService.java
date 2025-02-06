package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.repository.WeatherForecastEntityRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WeatherForecastService {
    private final WeatherForecastEntityRepository weatherForecastEntityRepository;
    public List<WeatherForecastEntity> saveList(List<WeatherForecast> weatherForecast) {
      return  weatherForecastEntityRepository.saveAll(WeatherForecastMapper.LibToEntityList(weatherForecast));
    }
}
