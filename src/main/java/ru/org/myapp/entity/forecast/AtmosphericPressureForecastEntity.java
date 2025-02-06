package ru.org.myapp.entity.forecast;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class AtmosphericPressureForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "value")
    private Double value;
    @Column(name = "sea_level_value")
    private Double seaLevelValue;
    @Column(name = "ground_level_value")
    private Double groundLevelValue;
    @Column(name = "unit")
    private String unit;
}
