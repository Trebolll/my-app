package ru.org.myapp.entity.weather;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "weather_entity")
@NamedEntityGraph(name = "weather",
        attributeNodes = {
                @NamedAttributeNode("weatherStateEntity"),
                @NamedAttributeNode("temperatureEntity"),
                @NamedAttributeNode("atmosphericPressureEntity"),
                @NamedAttributeNode("humidityEntity"),
                @NamedAttributeNode("windEntity"),
                @NamedAttributeNode("rainEntity"),
                @NamedAttributeNode("snowEntity"),
                @NamedAttributeNode("clouds"),
                @NamedAttributeNode(value = "location", subgraph = "location-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(name = "location-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("coordinate")
                        })
        })
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "calculation_time")
    private LocalDateTime calculationTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_state_uuid", referencedColumnName = "uuid")
    private WeatherStateEntity weatherStateEntity;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "temperature_entity_uuid", referencedColumnName = "uuid")
    private TemperatureEntity temperatureEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atmospheric_pressure_entity_uuid", referencedColumnName = "uuid")
    private AtmosphericPressureEntity atmosphericPressureEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "humidity_entity_uuid", referencedColumnName = "uuid")
    private HumidityEntity humidityEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wind_entity_uuid", referencedColumnName = "uuid")
    private WindEntity windEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rain_entity_uuid", referencedColumnName = "uuid")
    private RainEntity rainEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "snow_entity_uuid", referencedColumnName = "uuid")
    private SnowEntity snowEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clouds_entity_uuid", referencedColumnName = "uuid")
    private CloudsEntity clouds;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_entity_uuid", referencedColumnName = "uuid")
    private LocationEntity location;
}
