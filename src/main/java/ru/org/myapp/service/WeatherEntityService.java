package ru.org.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.entity.weather.WeatherEntity;
import ru.org.myapp.repository.WeatherEntityRepository;


@Service
@RequiredArgsConstructor
public class WeatherEntityService {
    private final WeatherEntityRepository weatherEntityRepository;

    @Transactional
    public WeatherEntity saveEntity(WeatherEntity weather) {
        return weatherEntityRepository.saveAndFlush(weather);
    }

    @Transactional(readOnly = true)
    public WeatherEntity getWeatherByCity(@Param("city") String city) {
        return weatherEntityRepository.findByCity(city);
    }
}
