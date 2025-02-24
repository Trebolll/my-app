package ru.org.myapp.mapper;

import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.forecast.Rain;
import com.github.prominence.openweathermap.api.model.forecast.Snow;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.forecast.Wind;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
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
import java.util.stream.Collectors;

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
    List<WeatherForecastDto> entityToDtoList(List<WeatherForecastEntity> entities);

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
    WeatherForecastDto entityToDto(WeatherForecastEntity entity);


    @Mapping(target = "forecastTimeISO", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "dayTime", source = "forecast.dayTime")
    @Mapping(target = "weatherStateForecastEntity", source = "forecast.weatherState", qualifiedByName = "mapWeatherStateForecast")
    @Mapping(target = "temperatureForecastEntity", source = "forecast.temperature")
    @Mapping(target = "atmosphericPressureForecastEntity", source = "forecast.atmosphericPressure")
    @Mapping(target = "humidityForecastEntity", source = "forecast.humidity", qualifiedByName = "mapHumidity")
    @Mapping(target = "windForecastEntity", source = "forecast.wind")
    @Mapping(target = "rainForecastEntity", source = "forecast.rain")
    @Mapping(target = "snowForecastEntity", source = "forecast.snow")
    @Mapping(target = "cloudsForecastEntity", source = "forecast.clouds")
    @Mapping(target = "city", source = "city")
    WeatherForecastEntity libToEntity(WeatherForecast forecast, String city);
    default List<WeatherForecastEntity> libToEntityList(List<WeatherForecast> forecasts, String city) {
        if (forecasts == null) {
            return List.of();
        }
        return forecasts.stream()
                .map(forecast -> libToEntity(forecast, city))
                .collect(Collectors.toList());
    }

    @Named("mapWeatherStateForecast")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "iconId", source = "iconId")
    @Mapping(target = "weatherConditionEnum", source = "weatherConditionEnum")
    @Mapping(target = "weatherIconUrl", source = "weatherIconUrl")
    WeatherStateForecastEntity mapWeatherStateForecast(WeatherState weatherState);
    TemperatureForecastEntity toTemperatureForecastEntity(Temperature temperature);
    AtmosphericPressureForecastEntity toAtmosphericPressureForecastEntity(AtmosphericPressure atmosphericPressure);

    @Named("mapHumidity")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "unit", source = "unit")
    HumidityForecastEntity mapHumidity(Humidity humidity);
    WindForecastEntity toWindForecastEntity(Wind wind);
    RainForecastEntity toRainForecastEntity(Rain rain);
    SnowForecastEntity toSnowForecastEntity(Snow snow);
    CloudsForecastEntity toCloudsForecastEntity(Clouds clouds);
}


