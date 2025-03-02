package ru.org.myapp.repository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.org.myapp.entity.forecast.WeatherForecastEntity;

import java.util.List;

import static ru.org.myapp.util.Const.forecast;

@Repository
public interface WeatherForecastEntityRepository extends JpaRepository<WeatherForecastEntity, Long> {
    @EntityGraph(value = forecast, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT w FROM WeatherForecastEntity w " +
            "WHERE w.city = :city " +
            "AND w.forecastTime >= CAST(date(now()) AS timestamp)" +
            " ORDER BY w.forecastTime DESC")
    List<WeatherForecastEntity> findAllByCity(@Param("city") String city);
}
