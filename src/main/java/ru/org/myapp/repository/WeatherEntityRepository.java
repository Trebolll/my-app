package ru.org.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.org.myapp.entity.weather.WeatherEntity;

@Repository
public interface WeatherEntityRepository extends JpaRepository<WeatherEntity, Long> {
}
