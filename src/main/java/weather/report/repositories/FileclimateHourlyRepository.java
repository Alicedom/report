package weather.report.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import weather.report.entities.FieldclimateHourly;

import java.util.Date;
import java.util.List;

public interface FileclimateHourlyRepository extends CrudRepository<FieldclimateHourly,Long> {
    // 0h -> 0h hom sau

    @Query(value = "SELECT * FROM fieldclimate_hourly a " +
            " WHERE a.station_code = ?1 " +
            " AND a.time > CURDATE() + INTERVAL ?2 day AND a.time < CURDATE() + INTERVAL ?2 + 1 DAY " +
            " AND a.updated_time = ( SELECT MAX(a.updated_time) FROM fieldclimate_hourly a " +
            " WHERE a.station_code = ?1 AND a.updated_time < CURDATE() + INTERVAL ?2 DAY ) " +
            " ORDER BY a.time", nativeQuery = true)
    List<FieldclimateHourly> getAllByStationCodeAndAndTime(String stationCode, int day);

    @Query(value = "SELECT * FROM fieldclimate_hourly a WHERE a.station_code = ?1 " +
            " AND a.updated_time = ( SELECT max(updated_time) FROM fieldclimate_hourly a " +
            " WHERE a.station_code = ?1 AND updated_time < ?2 )", nativeQuery = true)
    List<FieldclimateHourly> getLast(String stationCode, Date day);
}
