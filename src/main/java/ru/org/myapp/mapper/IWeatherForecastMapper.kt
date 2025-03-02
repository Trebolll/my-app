package ru.org.myapp.mapper

import com.github.prominence.openweathermap.api.model.*
import com.github.prominence.openweathermap.api.model.forecast.Rain
import com.github.prominence.openweathermap.api.model.forecast.Snow
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast
import com.github.prominence.openweathermap.api.model.forecast.Wind
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.Named
import ru.org.myapp.dto.WeatherForecastDto
import ru.org.myapp.entity.forecast.*
import java.util.stream.Collectors

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface IWeatherForecastMapper {
    fun toWeatherStateDto(entity: WeatherStateForecastEntity?): WeatherForecastDto.WeatherStateDto?
    fun toTemperatureDto(entity: TemperatureForecastEntity?): WeatherForecastDto.TemperatureDto?
    fun toAtmosphericPressureDto(entity: AtmosphericPressureForecastEntity?): WeatherForecastDto.AtmosphericPressureDto?
    fun toHumidityDto(entity: HumidityForecastEntity?): WeatherForecastDto.HumidityDto?
    fun toWindDto(entity: WindForecastEntity?): WeatherForecastDto.WindDto?
    fun toRainDto(entity: RainForecastEntity?): WeatherForecastDto.RainDto?
    fun toSnowDto(entity: SnowForecastEntity?): WeatherForecastDto.SnowDto?
    fun toCloudsDto(entity: CloudsForecastEntity?): WeatherForecastDto.CloudsDto?
    fun entityToDtoList(entities: List<WeatherForecastEntity?>?): List<WeatherForecastDto?>?

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
    fun entityToDto(entity: WeatherForecastEntity?): WeatherForecastDto?


    @Mapping(target = "forecastTimeISO", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "dayTime", source = "forecast.dayTime")
    @Mapping(
        target = "weatherStateForecastEntity",
        source = "forecast.weatherState",
        qualifiedByName = ["mapWeatherStateForecast"]
    )
    @Mapping(target = "temperatureForecastEntity", source = "forecast.temperature")
    @Mapping(target = "atmosphericPressureForecastEntity", source = "forecast.atmosphericPressure")
    @Mapping(target = "humidityForecastEntity", source = "forecast.humidity", qualifiedByName = ["mapHumidity"])
    @Mapping(target = "windForecastEntity", source = "forecast.wind")
    @Mapping(target = "rainForecastEntity", source = "forecast.rain")
    @Mapping(target = "snowForecastEntity", source = "forecast.snow")
    @Mapping(target = "cloudsForecastEntity", source = "forecast.clouds")
    @Mapping(target = "city", source = "city")
    fun libToEntity(forecast: WeatherForecast?, city: String?): WeatherForecastEntity
    fun libToEntityList(forecasts: List<WeatherForecast?>?, city: String?): List<WeatherForecastEntity> {
        if (forecasts == null) {
            return listOf()
        }
        return forecasts.stream()
            .map { forecast: WeatherForecast? -> libToEntity(forecast, city) }
            .collect(Collectors.toList())
    }

    @Named("mapWeatherStateForecast")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "iconId", source = "iconId")
    @Mapping(target = "weatherConditionEnum", source = "weatherConditionEnum")
    @Mapping(target = "weatherIconUrl", source = "weatherIconUrl")
    fun mapWeatherStateForecast(weatherState: WeatherState?): WeatherStateForecastEntity?
    fun toTemperatureForecastEntity(temperature: Temperature?): TemperatureForecastEntity?
    fun toAtmosphericPressureForecastEntity(atmosphericPressure: AtmosphericPressure?): AtmosphericPressureForecastEntity?

    @Named("mapHumidity")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "unit", source = "unit")
    fun mapHumidity(humidity: Humidity?): HumidityForecastEntity?
    fun toWindForecastEntity(wind: Wind?): WindForecastEntity?
    fun toRainForecastEntity(rain: Rain?): RainForecastEntity?
    fun toSnowForecastEntity(snow: Snow?): SnowForecastEntity?
    fun toCloudsForecastEntity(clouds: Clouds?): CloudsForecastEntity?
}


