package ru.org.myapp.entity.forecast;
import jakarta.persistence.*;
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
@Table(name ="snow_forecast_entity")
public class SnowForecastEntity {
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
