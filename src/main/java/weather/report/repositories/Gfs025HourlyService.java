package weather.report.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.sms.Utils;
import weather.report.entities.WeatherHourly;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class Gfs025HourlyService  {

    @Autowired
    Gfs025HourlyRepository gfs025HourlyRepository;

    public Map<Timestamp, WeatherHourly> getMapTimeHourlies(String stationCode, int date) {
        Map<Timestamp, WeatherHourly> weatherHourlyMap = new HashMap<Timestamp, WeatherHourly>();

        gfs025HourlyRepository.getAllByStationCodeAndAndTime(stationCode, date).forEach(e->{
            WeatherHourly weatherHourly = Utils.fixData(e);
            weatherHourlyMap.put(e.getTime(),e);
        });

        return weatherHourlyMap;
    }

}
