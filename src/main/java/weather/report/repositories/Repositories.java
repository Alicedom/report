package weather.report.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import weather.report.entities.WeatherHourly;

import java.util.List;

public interface Repositories<E> extends CrudRepository<WeatherHourly, Long> {
    @Query(value = "SELECT * FROM #{#entityName} a WHERE a.id IN ( SELECT MAX(id) AS id FROM #{#entityName} a WHERE a.station_code = ?2 AND DATE(a.time) = (CURDATE() + INTERVAL ?3 DAY ) GROUP BY a.station_code, a.time )", nativeQuery = true)
    List<WeatherHourly> getAllByStationCodeAndTime(String stationCode, int day);

    @Query(value = "SELECT * FROM #{#entityName} a WHERE a.id = ?2", nativeQuery = true)
    WeatherHourly getDateHourly(long id);
}
