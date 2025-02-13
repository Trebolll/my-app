package ru.org.myapp.entity.forecast;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "weather_forecast_entity")
@NamedEntityGraph(name = "forecast",
        attributeNodes = {
                @NamedAttributeNode("weatherStateForecastEntity"),
                @NamedAttributeNode("temperatureForecastEntity"),
                @NamedAttributeNode("atmosphericPressureForecastEntity"),
                @NamedAttributeNode("humidityForecastEntity"),
                @NamedAttributeNode("windForecastEntity"),
                @NamedAttributeNode("rainForecastEntity"),
                @NamedAttributeNode("snowForecastEntity"),
                @NamedAttributeNode("cloudsForecastEntity"),
})
public class WeatherForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "forecast_time")
    private  LocalDateTime forecastTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_state_forecast_entity_id", referencedColumnName = "id")
    private WeatherStateForecastEntity weatherStateForecastEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "temperature_forecast_entity_id", referencedColumnName = "id")
    private TemperatureForecastEntity temperatureForecastEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atmospheric_pressure_forecast_entity_id", referencedColumnName = "id")
    private AtmosphericPressureForecastEntity atmosphericPressureForecastEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "humidity_forecast_entity_id", referencedColumnName = "id")
    private HumidityForecastEntity humidityForecastEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wind_forecast_entity_id", referencedColumnName = "id")
    private WindForecastEntity windForecastEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rain_forecast_entity_id", referencedColumnName = "id")
    private RainForecastEntity rainForecastEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "snow_forecast_entity_id", referencedColumnName = "id")
    private SnowForecastEntity snowForecastEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clouds_forecast_entity_id", referencedColumnName = "id")
    private CloudsForecastEntity cloudsForecastEntity;
    @Column(name = "forecast_time_iso")
    private  LocalDateTime forecastTimeISO;
    @Column(name = "day_time")
    private String dayTime;
    @Column(name = "city")
    private String city;
}
