package weather.report.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import weather.report.entities.AccuweatherHourly;

import java.util.List;

public interface AccuweatherHourlyRepository extends CrudRepository<AccuweatherHourly, Long> {
    @Query(value = "SELECT * FROM accuweather_hourly a WHERE a.id IN ( SELECT MAX(id) AS id FROM accuweather_hourly a WHERE a.station_code = ?1 AND DATE(a.time) = (CURDATE() + INTERVAL ?2 DAY ) GROUP BY a.station_code, a.time )", nativeQuery = true)
    List<AccuweatherHourly> getAllByStationCodeAndAndTime(String station, int day);

}
