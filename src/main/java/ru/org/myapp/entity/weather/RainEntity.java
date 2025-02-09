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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rain_entity")
public class RainEntity {
    private static final String DEFAULT_UNIT = "mm";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "one_Hour_Level")
    private Double oneHourLevel;
    @Column(name = "three_Hour_Level")
    private Double threeHourLevel;
    @Column(name = "unit")
    private String unit;
}
