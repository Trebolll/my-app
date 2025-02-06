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
import ru.org.myapp.entity.weather.AtmosphericPressureEntity;
import ru.org.myapp.entity.weather.CloudsEntity;
import ru.org.myapp.entity.weather.CoordinateEntity;
import ru.org.myapp.entity.weather.HumidityEntity;
import ru.org.myapp.entity.weather.LocationEntity;
import ru.org.myapp.entity.weather.RainEntity;
import ru.org.myapp.entity.weather.SnowEntity;
import ru.org.myapp.entity.weather.TemperatureEntity;
import ru.org.myapp.entity.weather.WeatherEntity;
import ru.org.myapp.entity.weather.WeatherStateEntity;
import ru.org.myapp.entity.weather.WindEntity;

public class WeatherMapper {

    public static WeatherEntity LibToEntity(Weather weather) {
        if (weather == null) {
            return null;
        }
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCalculationTime(weather.getCalculationTime());
        weatherEntity.setWeatherStateEntity(toWeatherStateEntity(weather.getWeatherState()));
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

    private static WeatherStateEntity toWeatherStateEntity(WeatherState weatherState) {
        if (weatherState == null) {
            return null;
        }
        WeatherStateEntity weatherStateEntity = new WeatherStateEntity();
        weatherStateEntity.setName(weatherState.getName());
        weatherStateEntity.setDescription(weatherState.getDescription());
        weatherStateEntity.setIconId(weatherState.getIconId());
        weatherStateEntity.setWeatherConditionEnum(weatherStateEntity.getWeatherConditionEnum());
        weatherStateEntity.setWeatherIconUrl(weatherState.getWeatherIconUrl());
        return weatherStateEntity;
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
            return new RainEntity(null, 0.0, 0.0, "mm");
        }
        RainEntity rainEntity = new RainEntity();
        rainEntity.setThreeHourLevel(rain.getThreeHourLevel());
        rainEntity.setOneHourLevel(rainEntity.getOneHourLevel());
        rainEntity.setUnit(rain.getUnit());
        return rainEntity;
    }

    private static SnowEntity toSnowEntity(Snow snow) {
        if (snow == null) {
            return new SnowEntity(null, 0.0, 0.0, "mm");
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

