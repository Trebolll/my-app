package ru.org.myapp.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.org.myapp.entity.weather.WeatherEntity;

import static ru.org.myapp.util.Constant.weather;

@Repository
public interface WeatherEntityRepository extends JpaRepository<WeatherEntity, Long> {
    @EntityGraph(value = weather, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT w FROM WeatherEntity w" +
            " WHERE w.location.name = :city " +
            "AND w.calculationTime >= CAST(date(now()) AS timestamp)" +
            " ORDER BY w.calculationTime DESC")
    WeatherEntity findByCity(@Param("city") String city);
}
