package ru.org.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
@Repository
public interface WeatherForecastEntityRepository extends JpaRepository<WeatherForecastEntity, Long> {
}
