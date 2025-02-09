package ru.org.myapp.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Builder
public record WeatherDto (
        LocalDateTime calculationTimeDto,
        WeatherStateDto weatherStateDto,
        TemperatureDto temperatureDto,
        AtmosphericPressureDto atmosphericPressureDto,
        HumidityDto humidityDto,
        WindDto windDto,
        RainDto rainDto,
        SnowDto snowDto,
        CloudsDto cloudsDto,
        LocationDto locationDto
) {
    @Builder
    public record WeatherStateDto(
            String name,
            String description,
            String iconId,
            String weatherConditionEnum,
            String weatherIconUrl
    ) {}
    @Builder
    public record TemperatureDto(
            Double value,
            Double maxTemperature,
            Double minTemperature,
            Double feelsLike,
            String unit
    ) {}
    @Builder
    public record AtmosphericPressureDto(
            Double value,
            Double seaLevelValue,
            Double groundLevelValue,
            String unit
    ) {}
    @Builder
    public record HumidityDto(
            int value,
            String unit
    ) {}
    @Builder
    public record WindDto(
            Double speed,
            Double degrees,
            String unit
    ) {}
    @Builder
    public record RainDto(
            Double threeHourLevel,
            Double oneHourLevel,
            String unit) {}
    @Builder
    public record SnowDto(
            Double threeHourLevel,
            Double oneHourLevel,
            String unit
    ) {}
    @Builder
    public record CloudsDto(
            Byte value,
            String unit
    ) {}
    @Builder
    public record LocationDto(
            String name,
            String countryCode,
            LocalDateTime sunriseTime,
            LocalDateTime sunsetTime,
            ZoneOffset zoneOffset,
            CoordinateDto coordinate
    ) {}
    @Builder
    public record CoordinateDto(
            double latitude,
            double longitude
    ) {}
}

