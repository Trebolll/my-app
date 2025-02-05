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
import ru.org.myapp.dto.WeatherDto;
import ru.org.myapp.entity.AtmosphericPressureEntity;
import ru.org.myapp.entity.CloudsEntity;
import ru.org.myapp.entity.CoordinateEntity;
import ru.org.myapp.entity.HumidityEntity;
import ru.org.myapp.entity.LocationEntity;
import ru.org.myapp.entity.RainEntity;
import ru.org.myapp.entity.SnowEntity;
import ru.org.myapp.entity.TemperatureEntity;
import ru.org.myapp.entity.WeatherEntity;
import ru.org.myapp.entity.WindEntity;

public class WeatherMapper {
    public static WeatherDto toDto(Weather weather) {
        if (weather == null) {
            return null;
        }
        return new WeatherDto(
                weather.getCalculationTime(),
                toWeatherStateDto(weather.getWeatherState()),
                toTemperatureDto(weather.getTemperature()),
                toAtmosphericPressureDto(weather.getAtmosphericPressure()),
                toHumidityDto(weather.getHumidity()),
                toWindDto(weather.getWind()),
                toRainDto(weather.getRain()),
                toSnowDto(weather.getSnow()),
                toCloudsDto(weather.getClouds()),
                toLocationDto(weather.getLocation())
        );
    }

    private static WeatherDto.WeatherStateDto toWeatherStateDto(WeatherState weatherState) {
        if (weatherState == null) {
            return null;
        }
        return new WeatherDto.WeatherStateDto(
                weatherState.getId(),
                weatherState.getName(),
                weatherState.getDescription(),
                weatherState.getIconId(),
                weatherState.getWeatherConditionEnum().name(),
                weatherState.getWeatherIconUrl()
        );
    }

    private static WeatherDto.TemperatureDto toTemperatureDto(Temperature temperature) {
        if (temperature == null) {
            return null;
        }
        return new WeatherDto.TemperatureDto(
                temperature.getValue(),
                temperature.getMaxTemperature(),
                temperature.getMinTemperature(),
                temperature.getFeelsLike(),
                temperature.getUnit()
        );
    }

    private static WeatherDto.AtmosphericPressureDto toAtmosphericPressureDto(AtmosphericPressure atmosphericPressure) {
        if (atmosphericPressure == null) {
            return null;
        }
        return new WeatherDto.AtmosphericPressureDto(
                atmosphericPressure.getValue(),
                atmosphericPressure.getSeaLevelValue(),
                atmosphericPressure.getGroundLevelValue(),
                atmosphericPressure.getUnit()
        );
    }

    private static WeatherDto.HumidityDto toHumidityDto(Humidity humidity) {
        if (humidity == null) {
            return null;
        }
        return new WeatherDto.HumidityDto(
                humidity.getValue(),
                humidity.getUnit()
        );
    }

    private static WeatherDto.WindDto toWindDto(com.github.prominence.openweathermap.api.model.weather.Wind wind) {
        if (wind == null) {
            return null;
        }
        return new WeatherDto.WindDto(
                wind.getSpeed(),
                wind.getDegrees(),
                wind.getUnit()
        );
    }

    private static WeatherDto.RainDto toRainDto(Rain rain) {
        if (rain == null) {
            return WeatherDto.RainDto.builder()
                    .oneHourLevel(0.0)
                    .threeHourLevel(0.0)
                    .build();
        }
        return new WeatherDto.RainDto(
                rain.getThreeHourLevel(),
                rain.getOneHourLevel()
        );
    }

    private static WeatherDto.SnowDto toSnowDto(Snow snow) {
        if (snow == null) {
            return WeatherDto.SnowDto.builder()
                    .oneHourLevel(0.0)
                    .threeHourLevel(0.0)
                    .build();
        }
        return new WeatherDto.SnowDto(
                snow.getThreeHourLevel(),
                snow.getOneHourLevel()
        );
    }

    private static WeatherDto.CloudsDto toCloudsDto(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        return new WeatherDto.CloudsDto(
                clouds.getValue(),
                clouds.getUnit()
        );
    }

    private static WeatherDto.LocationDto toLocationDto(Location location) {
        if (location == null) {
            return null;
        }
        return new WeatherDto.LocationDto(
                location.getId(),
                location.getName(),
                location.getCountryCode(),
                location.getSunriseTime(),
                location.getSunsetTime(),
                location.getZoneOffset(),
                toCoordinateDto(location.getCoordinate())
        );
    }

    private static WeatherDto.CoordinateDto toCoordinateDto(Coordinate coordinate) {
        if (coordinate == null) {
            return null;
        }
        return new WeatherDto.CoordinateDto(
                coordinate.getLatitude(),
                coordinate.getLongitude()
        );
    }

    public static WeatherEntity toEntity(Weather weather) {
        if (weather == null) {
            return null;
        }
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCalculationTime(weather.getCalculationTime());
        weatherEntity.setTemperatureEntity(toTemperatureEntity(weather.getTemperature()));
        weatherEntity.setAtmosphericPressureEntity(toAtmosphericPressureEntity(weather.getAtmosphericPressure()));
        weatherEntity.setHumidityEntity(toHumidityEntity(weather.getHumidity()));
        weatherEntity.setWindEntity(toWindEntity(weather.getWind()));
        weatherEntity.setRainEntity(toRainEntity(weather.getRain()));
        weatherEntity.setSnowEntity(toSnowEntity(weather.getSnow()));
        weatherEntity.setClouds(toCloudsEntity(weather.getClouds()));
        weatherEntity.setLocation(toLocationEntity(weather.getLocation()));
        return weatherEntity;
    }

    private static TemperatureEntity toTemperatureEntity(Temperature temperature) {
        if (temperature == null) {
            return null;
        }
        TemperatureEntity temperatureEntity = new TemperatureEntity();
        temperatureEntity.setValue(temperature.getValue());
        temperatureEntity.setMaxTemperature(temperature.getMaxTemperature());
        temperatureEntity.setMinTemperature(temperature.getMinTemperature());
        temperatureEntity.setFeelsLike(temperature.getFeelsLike());
        temperatureEntity.setUnit(temperature.getUnit());
        return temperatureEntity;
    }

    private static AtmosphericPressureEntity toAtmosphericPressureEntity(AtmosphericPressure atmosphericPressure) {
        if (atmosphericPressure == null) {
            return null;
        }
        AtmosphericPressureEntity atmosphericPressureEntity = new AtmosphericPressureEntity();
        atmosphericPressureEntity.setValue(atmosphericPressure.getValue());
        atmosphericPressureEntity.setSeaLevelValue(atmosphericPressure.getSeaLevelValue());
        atmosphericPressureEntity.setGroundLevelValue(atmosphericPressure.getGroundLevelValue());
        atmosphericPressureEntity.setUnit(atmosphericPressure.getUnit());
        return atmosphericPressureEntity;
    }

    private static HumidityEntity toHumidityEntity(Humidity humidity) {
        if (humidity == null) {
            return null;
        }
        HumidityEntity humidityEntity = new HumidityEntity();
        humidityEntity.setValue(humidity.getValue());
        humidityEntity.setUnit(humidity.getUnit());
        return humidityEntity;
    }

    private static WindEntity toWindEntity(Wind wind) {
        if (wind == null) {
            return null;
        }
        WindEntity windEntity = new WindEntity();
        windEntity.setSpeed(wind.getSpeed());
        windEntity.setDegrees(wind.getDegrees());
        windEntity.setUnit(wind.getUnit());
        return windEntity;
    }

    private static RainEntity toRainEntity(Rain rain) {
        if (rain == null) {
            return new RainEntity(null, 0.0, 0.0);
        }
        RainEntity rainEntity = new RainEntity();
        rainEntity.setThreeHourLevel(rain.getThreeHourLevel());
        rainEntity.setOneHourLevel(rainEntity.getOneHourLevel());
        return rainEntity;
    }

    private static SnowEntity toSnowEntity(Snow snow) {
        if (snow == null) {
            return new SnowEntity(null, 0.0, 0.0);
        }
        SnowEntity snowEntity = new SnowEntity();
        snowEntity.setThreeHourLevel(snow.getThreeHourLevel());
        snowEntity.setOneHourLevel(snowEntity.getOneHourLevel());
        return snowEntity;
    }

    private static CloudsEntity toCloudsEntity(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        CloudsEntity cloudsEntity = new CloudsEntity();
        cloudsEntity.setValue(clouds.getValue());
        cloudsEntity.setUnit(clouds.getUnit());
        return cloudsEntity;
    }

    private static LocationEntity toLocationEntity(Location location) {
        if (location == null) {
            return null;
        }
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(location.getName());
        locationEntity.setCountryCode(location.getCountryCode());
        locationEntity.setSunriseTime(location.getSunriseTime());
        locationEntity.setSunsetTime(location.getSunsetTime());
        locationEntity.setZoneOffset(location.getZoneOffset());
        locationEntity.setCoordinate(toCoordinateEntity(location.getCoordinate()));
        return locationEntity;
    }

    private static CoordinateEntity toCoordinateEntity(Coordinate coordinate) {
        if (coordinate == null) {
            return null;
        }
        CoordinateEntity coordinateEntity = new CoordinateEntity();
        coordinateEntity.setLatitude(coordinate.getLatitude());
        coordinateEntity.setLongitude(coordinate.getLongitude());
        return coordinateEntity;
    }
}

