package ru.org.myapp.entity.weather;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "weather_entity")
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "calculation_time")
    private LocalDateTime calculationTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_state_id", referencedColumnName = "id")
    private WeatherStateEntity weatherStateEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "temperature_entity_id", referencedColumnName = "id")
    private TemperatureEntity temperatureEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atmospheric_pressure_entity_id", referencedColumnName = "id")
    private AtmosphericPressureEntity atmosphericPressureEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "humidity_entity_id", referencedColumnName = "id")
    private HumidityEntity humidityEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wind_entity_id", referencedColumnName = "id")
    private WindEntity windEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rain_entity_id", referencedColumnName = "id")
    private RainEntity rainEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "snow_entity_id", referencedColumnName = "id")
    private SnowEntity snowEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clouds_entity_id", referencedColumnName = "id")
    private CloudsEntity clouds;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_entity_id", referencedColumnName = "id")
    private LocationEntity location;
}
