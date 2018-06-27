package weather.report.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import weather.report.entities.Station;

import java.util.List;

public interface StationRepository extends CrudRepository<Station, Long>{
    @Query("select s from Station s where s.apiEnable = true")
    List<Station> getEnableStations();
}