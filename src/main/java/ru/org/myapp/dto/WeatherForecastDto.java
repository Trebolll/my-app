package ru.org.myapp.dto;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record WeatherForecastDto(
        LocalDateTime forecastTime,
        WeatherStateDto weatherState,
        TemperatureDto temperature,
        AtmosphericPressureDto atmosphericPressure,
        HumidityDto humidity,
        WindDto wind,
        RainDto rain,
        SnowDto snow,
        CloudsDto clouds,
        String forecastTimeISO,
        String dayTime
) {
    @Builder
    public record WeatherStateDto(
            int id,
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
            String unit
    ) {}
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

}