package ru.org.myapp.entity.weather;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "temperature_entity")
public  class TemperatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "value")
    private Double value;
    @Column(name = "max_temperature")
    private Double maxTemperature;
    @Column(name = "min_temperature")
    private Double minTemperature;
    @Column(name = "feels_like")
    private Double feelsLike;
    @Column(name = "unit")
    private String unit;
}
