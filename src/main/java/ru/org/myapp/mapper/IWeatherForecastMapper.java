package ru.org.myapp.mapper;

import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.Rain;
import com.github.prominence.openweathermap.api.model.forecast.Snow;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.forecast.Wind;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.forecast.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IWeatherForecastMapper {


    List<WeatherForecastDto> toDtoList(List<WeatherForecastEntity> weatherForecastEntities);

    @Mapping(source = "forecastTime", target = "forecastTime")
    @Mapping(source = "weatherStateForecastEntity", target = "weatherState")
    @Mapping(source = "temperatureForecastEntity", target = "temperature")
    @Mapping(source = "atmosphericPressureForecastEntity", target = "atmosphericPressure")
    @Mapping(source = "humidityForecastEntity", target = "humidity")
    @Mapping(source = "windForecastEntity", target = "wind")
    @Mapping(source = "rainForecastEntity", target = "rain")
    @Mapping(source = "snowForecastEntity", target = "snow")
    @Mapping(source = "cloudsForecastEntity", target = "clouds")
    @Mapping(source = "forecastTimeISO", target = "forecastTimeISO")
    @Mapping(source = "dayTime", target = "dayTime", qualifiedByName = "dayTimeToString")
    WeatherForecastDto toDto(WeatherForecastEntity weatherForecastEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "iconId", target = "iconId")
    @Mapping(source = "weatherConditionEnum", target = "weatherConditionEnum")
    @Mapping(source = "weatherIconUrl", target = "weatherIconUrl")
    WeatherForecastDto.WeatherStateDto toWeatherStateDto(WeatherStateForecastEntity weatherStateForecastEntity);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "maxTemperature", target = "maxTemperature")
    @Mapping(source = "minTemperature", target = "minTemperature")
    @Mapping(source = "feelsLike", target = "feelsLike")
    @Mapping(source = "unit", target = "unit")
    WeatherForecastDto.TemperatureDto toTemperatureDto(TemperatureForecastEntity temperatureForecastEntity);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "seaLevelValue", target = "seaLevelValue")
    @Mapping(source = "groundLevelValue", target = "groundLevelValue")
    @Mapping(source = "unit", target = "unit")
    WeatherForecastDto.AtmosphericPressureDto toAtmosphericPressureDto(AtmosphericPressureForecastEntity atmosphericPressureForecastEntity);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "unit", target = "unit")
    WeatherForecastDto.HumidityDto toHumidityDto(HumidityForecastEntity humidityForecastEntity);

    @Mapping(source = "speed", target = "speed")
    @Mapping(source = "degrees", target = "degrees")
    @Mapping(source = "unit", target = "unit")
    WeatherForecastDto.WindDto toWindDto(WindForecastEntity windForecastEntity);

    @Mapping(source = "threeHourLevel", target = "threeHourLevel")
    @Mapping(source = "threeHourLevel", target = "oneHourLevel", qualifiedByName = "divideByThree")
    @Mapping(source = "unit", target = "unit")
    WeatherForecastDto.RainDto toRainDto(RainForecastEntity rainForecastEntity);

    @Mapping(source = "threeHourLevel", target = "threeHourLevel")
    @Mapping(source = "threeHourLevel", target = "oneHourLevel", qualifiedByName = "divideByThree")
    @Mapping(source = "unit", target = "unit")
    WeatherForecastDto.SnowDto toSnowDto(SnowForecastEntity snowForecastEntity);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "unit", target = "unit")
    WeatherForecastDto.CloudsDto toCloudsDto(CloudsForecastEntity cloudsForecastEntity);

    @Named("dayTimeToString")
    default String dayTimeToString(String dayTime) {
        return dayTime != null ? dayTime : null;
    }

    @Named("divideByThree")
    default Double divideByThree(Double value) {
        return value != null ? value / 3 : 0.0;
    }

    List<WeatherForecastEntity> toEntityList(List<WeatherForecast> weatherForecasts);

    @Mapping(source = "forecastTime", target = "forecastTime")
    @Mapping(source = "weatherState", target = "weatherStateForecastEntity")
    @Mapping(source = "temperature", target = "temperatureForecastEntity")
    @Mapping(source = "atmosphericPressure", target = "atmosphericPressureForecastEntity")
    @Mapping(source = "humidity", target = "humidityForecastEntity")
    @Mapping(source = "wind", target = "windForecastEntity")
    @Mapping(source = "rain", target = "rainForecastEntity")
    @Mapping(source = "snow", target = "snowForecastEntity")
    @Mapping(source = "clouds", target = "cloudsForecastEntity")
    @Mapping(source = "forecastTimeISO", target = "forecastTimeISO")
    @Mapping(source = "dayTime", target = "dayTime", qualifiedByName = "dayTimeToString")
    WeatherForecastEntity toEntity(WeatherForecast weatherForecast);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "iconId", target = "iconId")
    @Mapping(source = "weatherConditionEnum.name", target = "weatherConditionEnum")
    @Mapping(source = "weatherIconUrl", target = "weatherIconUrl")
    WeatherStateForecastEntity toWeatherStateForecastEntity(WeatherState weatherState);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "maxTemperature", target = "maxTemperature")
    @Mapping(source = "minTemperature", target = "minTemperature")
    @Mapping(source = "feelsLike", target = "feelsLike")
    @Mapping(source = "unit", target = "unit")
    TemperatureForecastEntity toTemperatureForecastEntity(Temperature temperature);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "seaLevelValue", target = "seaLevelValue")
    @Mapping(source = "groundLevelValue", target = "groundLevelValue")
    @Mapping(source = "unit", target = "unit")
    AtmosphericPressureForecastEntity toAtmosphericPressureForecastEntity(AtmosphericPressure atmosphericPressure);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "unit", target = "unit")
    HumidityForecastEntity toHumidityForecastEntity(Humidity humidity);

    @Mapping(source = "speed", target = "speed")
    @Mapping(source = "degrees", target = "degrees")
    @Mapping(source = "unit", target = "unit")
    WindForecastEntity toWindForecastEntity(Wind wind);

    @Mapping(source = "threeHourLevel", target = "threeHourLevel")
    @Mapping(source = "threeHourLevel", target = "oneHourLevel", qualifiedByName = "divideByThree")
    @Mapping(source = "unit", target = "unit")
    RainForecastEntity toRainForecastEntity(Rain rain);

    @Mapping(source = "threeHourLevel", target = "threeHourLevel")
    @Mapping(source = "threeHourLevel", target = "oneHourLevel", qualifiedByName = "divideByThree")
    @Mapping(source = "unit", target = "unit")
    SnowForecastEntity toSnowForecastEntity(Snow snow);

    @Mapping(source = "value", target = "value")
    @Mapping(source = "unit", target = "unit")
    CloudsForecastEntity toCloudsForecastEntity(Clouds clouds);
}