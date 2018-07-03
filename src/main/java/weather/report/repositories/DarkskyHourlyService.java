package weather.report.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.entities.DarkskyHourly;
import weather.report.entities.WeatherHourly;
import weather.report.sms.Utils;

import java.sql.Timestamp;
import java.util.*;

@Service
public class DarkskyHourlyService  {

    @Autowired
    DarkskyHourlyRepository darkskyHourlyRepository;

    public Map<Timestamp, WeatherHourly> getMapTimeHourlies(String stationCode, int date) {
        Map<Timestamp, WeatherHourly> weatherHourlyMap = new HashMap<Timestamp, WeatherHourly>();
        List<DarkskyHourly> list = darkskyHourlyRepository.getAllByStationCodeAndAndTime(stationCode, date);

        if(list == null || list.size() == 0){

        }else{
            list.remove(list.size() -1);
            list.forEach(e->{
                WeatherHourly weatherHourly = Utils.fixData(e);
                weatherHourlyMap.put(e.getTime(),weatherHourly);
            });
        }



        return weatherHourlyMap;
    }


    public List getLast(String stationCode, Date date){
        return darkskyHourlyRepository.getLast(stationCode, date);
    }


    public List<WeatherHourly> getHourlies(String stationCode, int date){
        List<WeatherHourly> list = new LinkedList<>();
        darkskyHourlyRepository.getAllByStationCodeAndAndTime(stationCode,date).forEach(x->{
            WeatherHourly data = Utils.fixData(x);
            list.add(data);
        });

        return list;
    }
}
