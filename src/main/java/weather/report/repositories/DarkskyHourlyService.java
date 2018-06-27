package weather.report.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.sms.Utils;
import weather.report.entities.WeatherHourly;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class DarkskyHourlyService  {

    @Autowired
    DarkskyHourlyRepository darkskyHourlyRepository;

    public Map<Timestamp, WeatherHourly> getMapTimeHourlies(String stationCode, int date) {
        Map<Timestamp, WeatherHourly> weatherHourlyMap = new HashMap<Timestamp, WeatherHourly>();

        darkskyHourlyRepository.getAllByStationCodeAndAndTime(stationCode, date).forEach(e->{
            WeatherHourly weatherHourly = Utils.convertWind(e);

            weatherHourlyMap.put(e.getTime(),e);
        });

        return weatherHourlyMap;
    }
}
