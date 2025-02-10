package ru.org.myapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;
import ru.org.myapp.entity.weather.WeatherEntity;

import java.util.List;

@Repository
public interface WeatherForecastEntityRepository extends JpaRepository<WeatherForecastEntity, Long> {
    @Query("SELECT w FROM WeatherForecastEntity w WHERE w.city = :city")
    List<WeatherForecastEntity> findAllByCity(String city);
}
