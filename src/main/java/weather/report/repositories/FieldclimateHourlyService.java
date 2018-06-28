package weather.report.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.entities.FieldclimateHourly;
import weather.report.sms.Utils;
import weather.report.entities.WeatherHourly;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FieldclimateHourlyService {
    @Autowired
    FieldclimateHourlyRepository fieldclimateHourlyRepository;

    public Map<Timestamp, WeatherHourly> getMapTimeHourlies(String stationCode, int date) {
        Map<Timestamp, WeatherHourly> weatherHourlyMap = new HashMap<Timestamp, WeatherHourly>();

        fieldclimateHourlyRepository.getAllByStationCodeAndAndTime(stationCode, date).forEach(e->{
            WeatherHourly weatherHourly = Utils.fixData(e);
            weatherHourlyMap.put(e.getTime(),e);
        });

        return weatherHourlyMap;
    }


    public List<FieldclimateHourly> getList(String stationCode, int date){
        return fieldclimateHourlyRepository.getAllByStationCodeAndAndTime(stationCode,date);
    }
}
