package ru.org.myapp.mapper;

import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.forecast.Rain;
import com.github.prominence.openweathermap.api.model.forecast.Snow;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.forecast.Wind;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherForecastMapper {
    // Преобразование из WeatherForecast в WeatherForecastEntity
    public static WeatherForecastEntity toEntity(WeatherForecast forecast) {
        if (forecast == null) {
            return null;
        }
        WeatherForecastEntity entity = new WeatherForecastEntity();
        entity.setForecastTime(forecast.getForecastTime());
        entity.setWeatherStateForecastEntity(toWeatherStateForecastEntity(forecast.getWeatherState()));
        entity.setTemperatureForecastEntity(toTemperatureForecastEntity(forecast.getTemperature()));
        entity.setAtmosphericPressureForecastEntity(toAtmosphericPressureForecastEntity(forecast.getAtmosphericPressure()));
        entity.setHumidityForecastEntity(toHumidityForecastEntity(forecast.getHumidity()));
        entity.setWindForecastEntity(toWindForecastEntity(forecast.getWind()));
        entity.setRainForecastEntity(toRainForecastEntity(forecast.getRain()));
        entity.setSnowForecastEntity(toSnowForecastEntity(forecast.getSnow()));
        entity.setCloudsForecastEntity(toCloudsForecastEntity(forecast.getClouds()));
        entity.setForecastTimeISO(LocalDateTime.now());
        entity.setDayTime(forecast.getDayTime().name());
        return entity;
    }

    // Преобразование из List<WeatherForecast> в List<WeatherForecastEntity>
    public static List<WeatherForecastEntity> LibToEntityList(List<WeatherForecast> forecasts) {
        if (forecasts == null) {
            return null;
        }
        return forecasts.stream()
                .map(WeatherForecastMapper::toEntity)
                .collect(Collectors.toList());
    }

    // Вспомогательные методы для преобразования вложенных объектов

    private static WeatherStateForecastEntity toWeatherStateForecastEntity(WeatherState weatherState) {
        if (weatherState == null) {
            return null;
        }
        WeatherStateForecastEntity entity = new WeatherStateForecastEntity();
        entity.setName(weatherState.getName());
        entity.setDescription(weatherState.getDescription());
        entity.setIconId(weatherState.getIconId());
        entity.setWeatherConditionEnum(weatherState.getWeatherConditionEnum().name());
        entity.setWeatherIconUrl(weatherState.getWeatherIconUrl());
        return entity;
    }

    private static TemperatureForecastEntity toTemperatureForecastEntity(Temperature temperature) {
        if (temperature == null) {
            return null;
        }
        TemperatureForecastEntity entity = new TemperatureForecastEntity();
        entity.setValue(temperature.getValue());
        entity.setMaxTemperature(temperature.getMaxTemperature());
        entity.setMinTemperature(temperature.getMinTemperature());
        entity.setFeelsLike(temperature.getFeelsLike());
        entity.setUnit(temperature.getUnit());
        return entity;
    }

    private static AtmosphericPressureForecastEntity toAtmosphericPressureForecastEntity(AtmosphericPressure atmosphericPressure) {
        if (atmosphericPressure == null) {
            return null;
        }
        AtmosphericPressureForecastEntity entity = new AtmosphericPressureForecastEntity();
        entity.setValue(atmosphericPressure.getValue());
        entity.setSeaLevelValue(atmosphericPressure.getSeaLevelValue());
        entity.setGroundLevelValue(atmosphericPressure.getGroundLevelValue());
        entity.setUnit(atmosphericPressure.getUnit());
        return entity;
    }

    private static HumidityForecastEntity toHumidityForecastEntity(Humidity humidity) {
        if (humidity == null) {
            return null;
        }
        HumidityForecastEntity entity = new HumidityForecastEntity();
        entity.setValue(humidity.getValue());
        entity.setUnit(humidity.getUnit());
        return entity;
    }

    private static WindForecastEntity toWindForecastEntity(Wind wind) {
        if (wind == null) {
            return null;
        }
        WindForecastEntity entity = new WindForecastEntity();
        entity.setSpeed(wind.getSpeed());
        entity.setDegrees(wind.getDegrees());
        entity.setUnit(wind.getUnit());
        return entity;
    }

    private static RainForecastEntity toRainForecastEntity(Rain rain) {
        if (rain == null) {
            return null;
        }
        RainForecastEntity entity = new RainForecastEntity();
        entity.setThreeHourLevel(rain.getThreeHourLevel());
        entity.setOneHourLevel(rain.getThreeHourLevel());
        entity.setUnit(rain.getUnit());
        return entity;
    }

    private static SnowForecastEntity toSnowForecastEntity(Snow snow) {
        if (snow == null) {
            return null;
        }
        SnowForecastEntity entity = new SnowForecastEntity();
        entity.setThreeHourLevel(snow.getThreeHourLevel());
        entity.setOneHourLevel(snow.getThreeHourLevel());
        entity.setUnit(snow.getUnit());
        return entity;
    }

    private static CloudsForecastEntity toCloudsForecastEntity(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        CloudsForecastEntity entity = new CloudsForecastEntity();
        entity.setValue(clouds.getValue());
        entity.setUnit(clouds.getUnit());
        return entity;
    }
    public static WeatherForecastDto toDto(WeatherForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.builder()
                .forecastTime(entity.getForecastTime())
                .weatherState(toWeatherStateDto(entity.getWeatherStateForecastEntity()))
                .temperature(toTemperatureDto(entity.getTemperatureForecastEntity()))
                .atmosphericPressure(toAtmosphericPressureDto(entity.getAtmosphericPressureForecastEntity()))
                .humidity(toHumidityDto(entity.getHumidityForecastEntity()))
                .wind(toWindDto(entity.getWindForecastEntity()))
                .rain(toRainDto(entity.getRainForecastEntity()))
                .snow(toSnowDto(entity.getSnowForecastEntity()))
                .clouds(toCloudsDto(entity.getCloudsForecastEntity()))
                .forecastTimeISO(entity.getForecastTimeISO())
                .dayTime(entity.getDayTime())
                .build();
    }

    // Преобразование из List<WeatherForecastEntity> в List<WeatherForecastDto>
    public static List<WeatherForecastDto> EntitytoDtoList(List<WeatherForecastEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(WeatherForecastMapper::toDto)
                .collect(Collectors.toList());
    }

    // Вспомогательные методы для преобразования вложенных объектов

    private static WeatherForecastDto.WeatherStateDto toWeatherStateDto(WeatherStateForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.WeatherStateDto.builder()
                .id(entity.getId().intValue())
                .name(entity.getName())
                .description(entity.getDescription())
                .iconId(entity.getIconId())
                .weatherConditionEnum(entity.getWeatherConditionEnum())
                .weatherIconUrl(entity.getWeatherIconUrl())
                .build();
    }

    private static WeatherForecastDto.TemperatureDto toTemperatureDto(TemperatureForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.TemperatureDto.builder()
                .value(entity.getValue())
                .maxTemperature(entity.getMaxTemperature())
                .minTemperature(entity.getMinTemperature())
                .feelsLike(entity.getFeelsLike())
                .unit(entity.getUnit())
                .build();
    }

    private static WeatherForecastDto.AtmosphericPressureDto toAtmosphericPressureDto(AtmosphericPressureForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.AtmosphericPressureDto.builder()
                .value(entity.getValue())
                .seaLevelValue(entity.getSeaLevelValue())
                .groundLevelValue(entity.getGroundLevelValue())
                .unit(entity.getUnit())
                .build();
    }

    private static WeatherForecastDto.HumidityDto toHumidityDto(HumidityForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.HumidityDto.builder()
                .value(entity.getValue())
                .unit(entity.getUnit())
                .build();
    }

    private static WeatherForecastDto.WindDto toWindDto(WindForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.WindDto.builder()
                .speed(entity.getSpeed())
                .degrees(entity.getDegrees())
                .unit(entity.getUnit())
                .build();
    }

    private static WeatherForecastDto.RainDto toRainDto(RainForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.RainDto.builder()
                .threeHourLevel(entity.getThreeHourLevel())
                .oneHourLevel(entity.getOneHourLevel())
                .unit(entity.getUnit())
                .build();
    }

    private static WeatherForecastDto.SnowDto toSnowDto(SnowForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.SnowDto.builder()
                .threeHourLevel(entity.getThreeHourLevel())
                .oneHourLevel(entity.getOneHourLevel())
                .unit(entity.getUnit())
                .build();
    }

    private static WeatherForecastDto.CloudsDto toCloudsDto(CloudsForecastEntity entity) {
        if (entity == null) {
            return null;
        }
        return WeatherForecastDto.CloudsDto.builder()
                .value(entity.getValue())
                .unit(entity.getUnit())
                .build();
    }

}
