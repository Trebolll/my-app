package ru.org.myapp.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.forecast.AtmosphericPressureForecastEntity;
import ru.org.myapp.entity.forecast.CloudsForecastEntity;
import ru.org.myapp.entity.forecast.HumidityForecastEntity;
import ru.org.myapp.entity.forecast.RainForecastEntity;
import ru.org.myapp.entity.forecast.SnowForecastEntity;
import ru.org.myapp.entity.forecast.TemperatureForecastEntity;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.entity.forecast.WeatherStateForecastEntity;
import ru.org.myapp.entity.forecast.WindForecastEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IWeatherForecastMapper {
    WeatherForecastDto.WeatherStateDto toWeatherStateDto(WeatherStateForecastEntity entity);

    WeatherForecastDto.TemperatureDto toTemperatureDto(TemperatureForecastEntity entity);

    WeatherForecastDto.AtmosphericPressureDto toAtmosphericPressureDto(AtmosphericPressureForecastEntity entity);

    WeatherForecastDto.HumidityDto toHumidityDto(HumidityForecastEntity entity);

    WeatherForecastDto.WindDto toWindDto(WindForecastEntity entity);

    WeatherForecastDto.RainDto toRainDto(RainForecastEntity entity);

    WeatherForecastDto.SnowDto toSnowDto(SnowForecastEntity entity);

    WeatherForecastDto.CloudsDto toCloudsDto(CloudsForecastEntity entity);

    @Mapping(source = "weatherStateForecastEntity", target = "weatherState")
    @Mapping(source = "temperatureForecastEntity", target = "temperature")
    @Mapping(source = "atmosphericPressureForecastEntity", target = "atmosphericPressure")
    @Mapping(source = "humidityForecastEntity", target = "humidity")
    @Mapping(source = "windForecastEntity", target = "wind")
    @Mapping(source = "rainForecastEntity", target = "rain")
    @Mapping(source = "snowForecastEntity", target = "snow")
    @Mapping(source = "cloudsForecastEntity", target = "clouds")
    @Mapping(source = "forecastTime", target = "forecastTime")
    @Mapping(source = "forecastTimeISO", target = "forecastTimeISO")
    @Mapping(source = "dayTime", target = "dayTime")
    WeatherForecastDto toDto(WeatherForecastEntity entity);

    List<WeatherForecastDto> EntityToDtoList(List<WeatherForecastEntity> entities);
}

