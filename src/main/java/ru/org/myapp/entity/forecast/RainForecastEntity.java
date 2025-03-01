package ru.org.myapp.entity.forecast;

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
@Table(name ="rain_forecast_entity")
public class RainForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "three_hour_level")
    private Double threeHourLevel;
    @Column(name = "one_hour_level")
    private Double oneHourLevel;
    @Column(name = "unit")
    private String unit;
}
