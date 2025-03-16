package ru.org.myapp;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.exception.WeatherServiceException;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.service.ServiceOpenWeatherApi;
import ru.org.myapp.service.WeatherForecastService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceOpenWeatherApiTest {

    @Mock
    private OpenWeatherMapClient client;

    @Mock
    private WeatherForecastService weatherForecastService;

    @Mock
    private WeatherForecastMapper weatherForecastMapper;

    @InjectMocks
    private ServiceOpenWeatherApi serviceOpenWeatherApi;

    @Test
    public void testGetForecastInfoWhenDataPresentThenReturnData() {
        // Arrange
        String city = "TestCity";
        WeatherForecastEntity mockEntity = mock(WeatherForecastEntity.class);
        WeatherForecastDto mockDto = mock(WeatherForecastDto.class);
        List<WeatherForecastEntity> mockEntityList = Collections.singletonList(mockEntity);
        List<WeatherForecastDto> mockDtoList = Collections.singletonList(mockDto);

        when(weatherForecastService.findAll(city)).thenReturn(mockEntityList);
        when(weatherForecastMapper.entityToDtoList(mockEntityList)).thenReturn(mockDtoList);

        // Act
        List<WeatherForecastDto> result = serviceOpenWeatherApi.getForecastInfo(city);

        // Assert
        assertEquals(mockDtoList, result);
        verify(weatherForecastService, times(1)).findAll(city);
        verify(weatherForecastMapper, times(1)).entityToDtoList(mockEntityList);
    }

    @Test
    public void testGetForecastInfoWhenExceptionThrownThenThrowWeatherServiceException() {
        // Arrange
        String city = "TestCity";
        when(weatherForecastService.findAll(city)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(WeatherServiceException.class, () -> serviceOpenWeatherApi.getForecastInfo(city));
        verify(weatherForecastService, times(1)).findAll(city);
    }
}