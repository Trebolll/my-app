package ru.org.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "weather_state_entity")
public class WeatherStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "iconId")
    private String iconId;
    @Column(name = "weather_condition_enum")
    private String weatherConditionEnum;
    @Column(name = "weather_icon_url")
    private String weatherIconUrl;
}
