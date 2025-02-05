package ru.org.myapp.entity;

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
@Table(name = "weather")
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "calculationTime")
    private LocalDateTime calculationTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "temperatureEntity_id", referencedColumnName = "id")
    private TemperatureEntity temperatureEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atmosphericPressureEntity_id", referencedColumnName = "id")
    private AtmosphericPressureEntity atmosphericPressureEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "humidityEntity_id", referencedColumnName = "id")
    private HumidityEntity humidityEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "windEntity_id", referencedColumnName = "id")
    private WindEntity windEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rainEntity_id", referencedColumnName = "id")
    private RainEntity rainEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "snowEntity_id", referencedColumnName = "id")
    private SnowEntity snowEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clouds_id", referencedColumnName = "id")
    private CloudsEntity clouds;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;
}
