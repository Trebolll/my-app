package ru.org.myapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.org.myapp.entity.weather.WeatherEntity;
import ru.org.myapp.repository.WeatherEntityRepository;
import ru.org.myapp.service.WeatherEntityService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class WeatherEntityServiceTest {
    @Mock
    private WeatherEntityRepository weatherEntityRepository;

    @InjectMocks
    private WeatherEntityService weatherEntityService;

    @Test
    public void testSaveEntityWhenWeatherEntityThenSaveAndFlushCalled() {
        WeatherEntity weatherEntity = WeatherEntity.builder().build();
        weatherEntityService.saveEntity(weatherEntity);
        verify(weatherEntityRepository, times(1)).saveAndFlush(weatherEntity);
    }

    @Test
    public void testGetWeatherByCityWhenCityNameThenFindByCityCalled() {
        String cityName = "TestCity";
        weatherEntityService.getWeatherByCity(cityName);
        verify(weatherEntityRepository, times(1)).findByCity(cityName);
    }
}
