package ru.org.myapp.mapper;


import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.Rain;
import com.github.prominence.openweathermap.api.model.forecast.Snow;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.forecast.Wind;
import ru.org.myapp.dto.WeatherForecastDto;
import ru.org.myapp.entity.forecast.*;

import java.util.List;
import java.util.stream.Collectors;


public class WeatherForecastMapper {

    public static List<WeatherForecastDto> toDtoList(List<WeatherForecast> weatherForecasts) {
        if (weatherForecasts == null || weatherForecasts.isEmpty()) {
            return List.of();
        }
        return weatherForecasts.stream()
                .map(WeatherForecastMapper::toDto)
                .collect(Collectors.toList());
    }

    public static WeatherForecastDto toDto(WeatherForecast weatherForecast) {
        if (weatherForecast == null) {
            return null;
        }
        return WeatherForecastDto.builder()
                .forecastTime(weatherForecast.getForecastTime())
                .weatherState(toWeatherStateDto(weatherForecast.getWeatherState()))
                .temperature(toTemperatureDto(weatherForecast.getTemperature()))
                .atmosphericPressure(toAtmosphericPressureDto(weatherForecast.getAtmosphericPressure()))
                .humidity(toHumidityDto(weatherForecast.getHumidity()))
                .wind(toWindDto(weatherForecast.getWind()))
                .rain(toRainDto(weatherForecast.getRain()))
                .snow(toSnowDto(weatherForecast.getSnow()))
                .clouds(toCloudsDto(weatherForecast.getClouds()))
                .forecastTimeISO(weatherForecast.getForecastTimeISO())
                .dayTime(weatherForecast.getDayTime() != null ? weatherForecast.getDayTime().name() : null)
                .build();
    }

    private static WeatherForecastDto.WeatherStateDto toWeatherStateDto(WeatherState weatherState) {
        if (weatherState == null) {
            return null;
        }
        return WeatherForecastDto.WeatherStateDto.builder()
                .id(weatherState.getId())
                .name(weatherState.getName())
                .description(weatherState.getDescription())
                .iconId(weatherState.getIconId())
                .weatherConditionEnum(weatherState.getWeatherConditionEnum().name())
                .weatherIconUrl(weatherState.getWeatherIconUrl())
                .build();
    }

    private static WeatherForecastDto.TemperatureDto toTemperatureDto(Temperature temperature) {
        if (temperature == null) {
            return null;
        }
        return WeatherForecastDto.TemperatureDto.builder()
                .value(temperature.getValue())
                .maxTemperature(temperature.getMaxTemperature())
                .minTemperature(temperature.getMinTemperature())
                .feelsLike(temperature.getFeelsLike())
                .unit(temperature.getUnit())
                .build();
    }

    private static WeatherForecastDto.AtmosphericPressureDto toAtmosphericPressureDto(AtmosphericPressure atmosphericPressure) {
        if (atmosphericPressure == null) {
            return null;
        }
        return WeatherForecastDto.AtmosphericPressureDto.builder()
                .value(atmosphericPressure.getValue())
                .seaLevelValue(atmosphericPressure.getSeaLevelValue())
                .groundLevelValue(atmosphericPressure.getGroundLevelValue())
                .unit(atmosphericPressure.getUnit())
                .build();
    }

    private static WeatherForecastDto.HumidityDto toHumidityDto(Humidity humidity) {
        if (humidity == null) {
            return null;
        }
        return WeatherForecastDto.HumidityDto.builder()
                .value(humidity.getValue())
                .unit(humidity.getUnit())
                .build();
    }

    private static WeatherForecastDto.WindDto toWindDto(Wind wind) {
        if (wind == null) {
            return null;
        }
        return WeatherForecastDto.WindDto.builder()
                .speed(wind.getSpeed())
                .degrees(wind.getDegrees())
                .unit(wind.getUnit())
                .build();
    }

    private static WeatherForecastDto.RainDto toRainDto(Rain rain) {
        if (rain == null) {
            return WeatherForecastDto.RainDto.builder()
                    .oneHourLevel(0.0)
                    .threeHourLevel(0.0)
                    .unit("mm")
                    .build();
        }
        return WeatherForecastDto.RainDto.builder()
                .threeHourLevel(rain.getThreeHourLevel())
                .oneHourLevel(rain.getThreeHourLevel() / 3)
                .unit(rain.getUnit())
                .build();
    }

    private static WeatherForecastDto.SnowDto toSnowDto(Snow snow) {
        if (snow == null) {
            return WeatherForecastDto.SnowDto.builder()
                    .oneHourLevel(0.0)
                    .threeHourLevel(0.0)
                    .unit("mm")
                    .build();
        }
        return WeatherForecastDto.SnowDto.builder()
                .threeHourLevel(snow.getThreeHourLevel())
                .oneHourLevel(snow.getThreeHourLevel() / 3)
                .unit(snow.getUnit())
                .build();
    }

    private static WeatherForecastDto.CloudsDto toCloudsDto(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        return WeatherForecastDto.CloudsDto.builder()
                .value(clouds.getValue())
                .unit(clouds.getUnit())
                .build();
    }
    public static String dayTimeToString(String dayTime) {
        return dayTime != null ? dayTime : null;
    }

    public static Double divideByThree(Double value) {
        return value != null ? value / 3 : 0.0;
    }

    public static List<WeatherForecastEntity> toEntityList(List<WeatherForecast> weatherForecasts) {
        if (weatherForecasts == null || weatherForecasts.isEmpty()) {
            return List.of();
        }
        return weatherForecasts.stream()
                .map(WeatherForecastMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static WeatherForecastEntity toEntity(WeatherForecast weatherForecast) {
        if (weatherForecast == null) {
            return null;
        }
        return WeatherForecastEntity.builder()
                .forecastTime(weatherForecast.getForecastTime())
                .weatherStateForecastEntity(toWeatherStateForecastEntity(weatherForecast.getWeatherState()))
                .temperatureForecastEntity(toTemperatureForecastEntity(weatherForecast.getTemperature()))
                .atmosphericPressureForecastEntity(toAtmosphericPressureForecastEntity(weatherForecast.getAtmosphericPressure()))
                .humidityForecastEntity(toHumidityForecastEntity(weatherForecast.getHumidity()))
                .windForecastEntity(toWindForecastEntity(weatherForecast.getWind()))
                .rainForecastEntity(toRainForecastEntity(weatherForecast.getRain()))
                .snowForecastEntity(toSnowForecastEntity(weatherForecast.getSnow()))
                .cloudsForecastEntity(toCloudsForecastEntity(weatherForecast.getClouds()))
                .forecastTimeISO(weatherForecast.getForecastTimeISO())
                .dayTime(weatherForecast.getDayTime().getValue())
                .build();
    }

    public static WeatherStateForecastEntity toWeatherStateForecastEntity(WeatherState weatherState) {
        if (weatherState == null) {
            return null;
        }
        return WeatherStateForecastEntity.builder()
                .name(weatherState.getName())
                .description(weatherState.getDescription())
                .iconId(weatherState.getIconId())
                .weatherConditionEnum(weatherState.getWeatherConditionEnum().name())
                .weatherIconUrl(weatherState.getWeatherIconUrl())
                .build();
    }

    public static TemperatureForecastEntity toTemperatureForecastEntity(Temperature temperature) {
        if (temperature == null) {
            return null;
        }
        return TemperatureForecastEntity.builder()
                .value(temperature.getValue())
                .maxTemperature(temperature.getMaxTemperature())
                .minTemperature(temperature.getMinTemperature())
                .feelsLike(temperature.getFeelsLike())
                .unit(temperature.getUnit())
                .build();
    }

    public static AtmosphericPressureForecastEntity toAtmosphericPressureForecastEntity(AtmosphericPressure atmosphericPressure) {
        if (atmosphericPressure == null) {
            return null;
        }
        return AtmosphericPressureForecastEntity.builder()
                .value(atmosphericPressure.getValue())
                .seaLevelValue(atmosphericPressure.getSeaLevelValue())
                .groundLevelValue(atmosphericPressure.getGroundLevelValue())
                .unit(atmosphericPressure.getUnit())
                .build();
    }

    public static HumidityForecastEntity toHumidityForecastEntity(Humidity humidity) {
        if (humidity == null) {
            return null;
        }
        return HumidityForecastEntity.builder()
                .value(humidity.getValue())
                .unit(humidity.getUnit())
                .build();
    }

    public static WindForecastEntity toWindForecastEntity(Wind wind) {
        if (wind == null) {
            return null;
        }
        return WindForecastEntity.builder()
                .speed(wind.getSpeed())
                .degrees(wind.getDegrees())
                .unit(wind.getUnit())
                .build();
    }

    public static RainForecastEntity toRainForecastEntity(Rain rain) {
        if (rain == null) {
            return null;
        }
        return RainForecastEntity.builder()
                .threeHourLevel(rain.getThreeHourLevel())
                .oneHourLevel(divideByThree(rain.getThreeHourLevel()))
                .unit(rain.getUnit())
                .build();
    }

    public static SnowForecastEntity toSnowForecastEntity(Snow snow) {
        if (snow == null) {
            return null;
        }
        return SnowForecastEntity.builder()
                .threeHourLevel(snow.getThreeHourLevel())
                .oneHourLevel(divideByThree(snow.getThreeHourLevel()))
                .unit(snow.getUnit())
                .build();
    }

    public static CloudsForecastEntity toCloudsForecastEntity(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        return CloudsForecastEntity.builder()
                .value(clouds.getValue())
                .unit(clouds.getUnit())
                .build();
    }
}