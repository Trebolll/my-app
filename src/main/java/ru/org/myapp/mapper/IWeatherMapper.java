package ru.org.myapp.mapper;

import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.model.WeatherState;
import com.github.prominence.openweathermap.api.model.weather.Location;
import com.github.prominence.openweathermap.api.model.weather.Rain;
import com.github.prominence.openweathermap.api.model.weather.Snow;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.model.weather.Wind;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.entity.weather.AtmosphericPressureEntity;
import ru.org.myapp.entity.weather.CloudsEntity;
import ru.org.myapp.entity.weather.CoordinateEntity;
import ru.org.myapp.entity.weather.HumidityEntity;
import ru.org.myapp.entity.weather.LocationEntity;
import ru.org.myapp.entity.weather.RainEntity;
import ru.org.myapp.entity.weather.SnowEntity;
import ru.org.myapp.entity.weather.TemperatureEntity;
import ru.org.myapp.entity.weather.WeatherEntity;
import ru.org.myapp.entity.weather.WindEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IWeatherMapper {

    @Mapping(target = "calculationTime", source = "calculationTime")
    @Mapping(target = "temperatureEntity", source = "temperature")
    @Mapping(target = "atmosphericPressureEntity", source = "atmosphericPressure")
    @Mapping(target = "humidityEntity", source = "humidity")
    @Mapping(target = "windEntity", source = "wind")
    @Mapping(target = "rainEntity", source = "rain")
    @Mapping(target = "snowEntity", source = "snow")
    @Mapping(target = "clouds", source = "clouds")
    @Mapping(target = "location", source = "location")
    WeatherEntity toEntity(Weather weather);

    @Mapping(target = "calculationTimeDto", source = "calculationTime")
    @Mapping(target = "temperatureDto", source = "temperatureEntity")
    @Mapping(target = "atmosphericPressureDto", source = "atmosphericPressureEntity")
    @Mapping(target = "humidityDto", source = "humidityEntity")
    @Mapping(target = "windDto", source = "windEntity")
    @Mapping(target = "rainDto", source = "rainEntity")
    @Mapping(target = "snowDto", source = "snowEntity")
    @Mapping(target = "cloudsDto", source = "clouds")
    @Mapping(target = "locationDto", source = "location")
    @Mapping(target = "weatherStateDto", source = "weatherStateEntity")
    WeatherDto toDto(WeatherEntity weatherEntity);

    WeatherDto.WeatherStateDto toWeatherStateDto(WeatherState weatherState);

    WeatherDto.TemperatureDto toTemperatureDto(Temperature temperature);

    WeatherDto.AtmosphericPressureDto toAtmosphericPressureDto(AtmosphericPressure atmosphericPressure);

    WeatherDto.HumidityDto toHumidityDto(Humidity humidity);

    WeatherDto.WindDto toWindDto(Wind wind);

    @Mapping(target = "oneHourLevel", defaultValue = "0.0")
    @Mapping(target = "threeHourLevel", defaultValue = "0.0")
    WeatherDto.RainDto toRainDto(Rain rain);

    @Mapping(target = "oneHourLevel", defaultValue = "0.0")
    @Mapping(target = "threeHourLevel", defaultValue = "0.0")
    WeatherDto.SnowDto toSnowDto(Snow snow);

    WeatherDto.CloudsDto toCloudsDto(Clouds clouds);

    WeatherDto.LocationDto toLocationDto(Location location);

    WeatherDto.CoordinateDto toCoordinateDto(Coordinate coordinate);

    TemperatureEntity toTemperatureEntity(Temperature temperature);

    AtmosphericPressureEntity toAtmosphericPressureEntity(AtmosphericPressure atmosphericPressure);

    HumidityEntity toHumidityEntity(Humidity humidity);

    WindEntity toWindEntity(Wind wind);

    @Mapping(target = "oneHourLevel", defaultValue = "0.0")
    @Mapping(target = "threeHourLevel", defaultValue = "0.0")
    RainEntity toRainEntity(Rain rain);

    @Mapping(target = "oneHourLevel", defaultValue = "0.0")
    @Mapping(target = "threeHourLevel", defaultValue = "0.0")
    SnowEntity toSnowEntity(Snow snow);

    CloudsEntity toCloudsEntity(Clouds clouds);

    LocationEntity toLocationEntity(Location location);

    CoordinateEntity toCoordinateEntity(Coordinate coordinate);
}
