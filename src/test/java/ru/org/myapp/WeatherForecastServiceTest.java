package ru.org.myapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.repository.WeatherForecastEntityRepository;
import ru.org.myapp.service.WeatherForecastService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherForecastServiceTest {
    @Mock
    private WeatherForecastEntityRepository weatherForecastEntityRepository;

    @InjectMocks
    private WeatherForecastService weatherForecastService;

    @Test
    public void testSaveListWhenCalledWithValidListThenReturnsExpectedList() {
        WeatherForecastEntity entity1 = WeatherForecastEntity.builder()
                .uuid(UUID.randomUUID())
                .forecastTime(LocalDateTime.now())
                .city("City")
                .build();
        WeatherForecastEntity entity2 = WeatherForecastEntity.builder()
                .uuid(UUID.randomUUID())
                .forecastTime(LocalDateTime.now())
                .city("Town")
                .build();
        List<WeatherForecastEntity> inputList = Arrays.asList(entity1, entity2);
        when(weatherForecastEntityRepository.saveAll(inputList)).thenReturn(inputList);
        List<WeatherForecastEntity> result = weatherForecastService.saveList(inputList);
        assertThat(result).isEqualTo(inputList);
    }

    @Test
    public void testFindAllWhenCalledWithValidCityThenReturnsExpectedList() {
        String city = "City";
        WeatherForecastEntity entity1 = WeatherForecastEntity.builder()
                .uuid(UUID.randomUUID())
                .forecastTime(LocalDateTime.now())
                .city(city)
                .build();
        WeatherForecastEntity entity2 = WeatherForecastEntity.builder()
                .uuid(UUID.randomUUID())
                .forecastTime(LocalDateTime.now())
                .city(city)
                .build();
        List<WeatherForecastEntity> expectedList = Arrays.asList(entity1, entity2);
        when(weatherForecastEntityRepository.findAllByCity(city)).thenReturn(expectedList);
        List<WeatherForecastEntity> result = weatherForecastService.findAll(city);
        assertThat(result).isEqualTo(expectedList);
    }
}
