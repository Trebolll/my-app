package ru.org.myapp.repository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;

import java.util.List;

@Repository
public interface WeatherForecastEntityRepository extends JpaRepository<WeatherForecastEntity, Long> {
    @EntityGraph(value = "forecast", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT w FROM WeatherForecastEntity w WHERE w.city = :city ORDER BY w.forecastTime DESC")
    List<WeatherForecastEntity> findAllByCity(String city);
}
