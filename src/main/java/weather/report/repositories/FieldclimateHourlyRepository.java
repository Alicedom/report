package weather.report.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import weather.report.entities.FieldclimateHourly;

import java.util.List;

public interface FieldclimateHourlyRepository extends CrudRepository<FieldclimateHourly, Long> {

    @Query(value = "SELECT * FROM fieldclimate_hourly a WHERE a.id IN ( SELECT MAX(id) AS id FROM fieldclimate_hourly a WHERE a.station_code = ?1 AND DATE(a.time) = (CURDATE() + INTERVAL ?2 DAY ) GROUP BY a.station_code, a.time )", nativeQuery = true)
    List<FieldclimateHourly> getAllByStationCodeAndAndTime(String station, int day);
}
