package ru.org.myapp.mapper

import com.github.prominence.openweathermap.api.model.Coordinate
import com.github.prominence.openweathermap.api.model.WeatherState
import com.github.prominence.openweathermap.api.model.weather.Location
import com.github.prominence.openweathermap.api.model.weather.Weather
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.Named
import ru.org.myapp.dto.WeatherDto
import ru.org.myapp.entity.weather.CoordinateEntity
import ru.org.myapp.entity.weather.LocationEntity
import ru.org.myapp.entity.weather.WeatherEntity
import ru.org.myapp.entity.weather.WeatherStateEntity

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface IWeatherMapper {
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
    fun entityToDto(weatherEntity: WeatherEntity?): WeatherDto?

    @Mapping(target = "calculationTime", source = "calculationTime")
    @Mapping(target = "temperatureEntity", source = "temperature")
    @Mapping(target = "atmosphericPressureEntity", source = "atmosphericPressure")
    @Mapping(target = "humidityEntity", source = "humidity")
    @Mapping(target = "windEntity", source = "wind")
    @Mapping(target = "rainEntity", source = "rain")
    @Mapping(target = "snowEntity", source = "snow")
    @Mapping(target = "clouds", source = "clouds")
    @Mapping(target = "location", source = "location", qualifiedByName = ["mapLocation"])
    @Mapping(target = "weatherStateEntity", source = "weatherState", qualifiedByName = ["mapWeatherState"])
    fun libToEntity(weather: Weather?): WeatherEntity?

    @Named("mapWeatherState")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "iconId", source = "iconId")
    @Mapping(target = "weatherConditionEnum", source = "weatherConditionEnum")
    @Mapping(target = "weatherIconUrl", source = "weatherIconUrl")
    fun mapWeatherState(weatherState: WeatherState?): WeatherStateEntity?

    @Named("mapLocation")
    @Mapping(target = "coordinate", source = "coordinate", qualifiedByName = ["mapCoordinate"])
    fun mapLocation(location: Location?): LocationEntity?

    @Named("mapCoordinate")
    fun mapCoordinate(coordinate: Coordinate?): CoordinateEntity?
}
