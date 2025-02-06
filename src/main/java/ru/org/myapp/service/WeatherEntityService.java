package ru.org.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.entity.weather.WeatherEntity;
import ru.org.myapp.mapper.IWeatherMapper;
import ru.org.myapp.repository.WeatherEntityRepository;

@Service
@RequiredArgsConstructor
public class WeatherEntityService {
    private final WeatherEntityRepository weatherEntityRepository;
    private final IWeatherMapper weatherMapper;

    @Transactional
    public WeatherDto saveEntity(WeatherEntity weatherEntity) {
        return weatherMapper.toDto(weatherEntityRepository.save(weatherEntity));
    }
}
