package weather.report.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.entities.Station;
import weather.report.repositories.StationRepository;

import java.util.List;

@Service
public class StationService {

    @Autowired
    StationRepository stationRepository;

    public List<Station> getEnableStations(){
        return stationRepository.getEnableStations();
    }
}
