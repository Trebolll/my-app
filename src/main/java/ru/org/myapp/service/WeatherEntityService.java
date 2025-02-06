package ru.org.myapp.service;

import com.github.prominence.openweathermap.api.model.weather.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.entity.weather.WeatherEntity;
import ru.org.myapp.mapper.WeatherMapper;
import ru.org.myapp.repository.WeatherEntityRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class WeatherEntityService {
    private final WeatherEntityRepository weatherEntityRepository;
    public WeatherEntity saveEntity(Weather weather) {
        return weatherEntityRepository.save(WeatherMapper.LibToEntity(weather));
    }
}
