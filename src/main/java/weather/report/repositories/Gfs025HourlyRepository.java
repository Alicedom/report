package weather.report.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import weather.report.entities.Gfs025Hourly;

import java.util.List;

public interface Gfs025HourlyRepository extends CrudRepository<Gfs025Hourly, Long> {

    @Query(value = "SELECT * FROM gfs025_hourly a WHERE a.id IN ( SELECT MAX(id) AS id FROM gfs025_hourly a WHERE a.station_code = ?1 AND DATE(a.time) = (CURDATE() + INTERVAL ?2 DAY ) GROUP BY a.station_code, a.time )", nativeQuery = true)
    List<Gfs025Hourly> getAllByStationCodeAndAndTime(String station, int day);


}
