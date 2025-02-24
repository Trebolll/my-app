package ru.org.myapp.entity.weather;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "location_entity")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "sunrise_time")
    private LocalDateTime sunriseTime;
    @Column(name = "sunset_time")
    private LocalDateTime sunsetTime;
    @Column(name = "zone_offset")
    private ZoneOffset zoneOffset;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_entity_uuid", referencedColumnName = "uuid")
    private CoordinateEntity coordinate;
}
