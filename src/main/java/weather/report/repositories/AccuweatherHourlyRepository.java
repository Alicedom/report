package weather.report.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import weather.report.entities.AccuweatherHourly;

import java.util.Date;
import java.util.List;

public interface AccuweatherHourlyRepository extends CrudRepository<AccuweatherHourly, Long> {
    // 0h -> 0h hom sau
    @Query(value = "SELECT * FROM accuweather_hourly a WHERE a.station_code = ?1 AND a.updated_time BETWEEN (CURDATE() + INTERVAL ?2 DAY) AND (CURDATE() + INTERVAL ?2 + 1 DAY) " +
            " AND a.time BETWEEN (CURDATE() + INTERVAL ?2 DAY) AND (CURDATE() + INTERVAL ?2 + 1 DAY) " +
            " AND a.id IN ( SELECT MAX(id) AS id FROM accuweather_hourly a WHERE " +
            " a.station_code = ?1 AND a.updated_time BETWEEN (CURDATE() + INTERVAL ?2 DAY) AND (CURDATE() + INTERVAL ?2 + 1 DAY) " +
            " AND a.time BETWEEN (CURDATE() + INTERVAL ?2 DAY) AND (CURDATE() + INTERVAL ?2 + 1 DAY) GROUP BY a.time )" +
            " ORDER BY a.time", nativeQuery = true)
    List<AccuweatherHourly> getAllByStationCodeAndAndTime(String station, int day);

    @Query(value = "SELECT * FROM accuweather_hourly a WHERE a.station_code = ?1 " +
            " AND a.updated_time = ( SELECT max(updated_time) FROM accuweather_hourly a " +
            " WHERE a.station_code = ?1 AND updated_time < ?2 )", nativeQuery = true)
    List<AccuweatherHourly> getLast(String stationCode, Date day);
}
