package ru.org.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.org.myapp.entity.WeatherEntity;
import ru.org.myapp.repository.WeatherEntityRepository;

@Service
@RequiredArgsConstructor
public class WeatherEntityService {
   private final WeatherEntityRepository weatherEntityRepository;

    @Transactional
    public WeatherEntity saveEntity(WeatherEntity weatherEntity) {
        return weatherEntityRepository.save(weatherEntity);
    }
}
