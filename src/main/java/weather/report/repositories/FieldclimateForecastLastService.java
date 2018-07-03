package weather.report.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.entities.FieldclimateForecastLast;
import weather.report.entities.FieldclimateHourly;
import weather.report.entities.WeatherHourly;
import weather.report.sms.SMSRule;
import weather.report.sms.Utils;

import java.sql.Timestamp;
import java.util.*;

@Service
public class FieldclimateForecastLastService {
    @Autowired
    FieldclimateForecastLastRepository fieldclimateHourlyRepository;

    public Map<Timestamp, WeatherHourly> getMapTimeHourlies(String stationCode, int date) {
        Map<Timestamp, WeatherHourly> weatherHourlyMap = new HashMap<Timestamp, WeatherHourly>();

        List<FieldclimateForecastLast> list = fieldclimateHourlyRepository.getAllByStationCodeAndAndTime(stationCode, date);

        if(list == null || list.size() == 0){

        }else{
            list.remove(list.size() -1);
            list.forEach(e->{
                WeatherHourly weatherHourly = Utils.fixData(convert(e));
                weatherHourlyMap.put(e.getTime(),weatherHourly);
            });
        }

        return weatherHourlyMap;
    }

    private FieldclimateHourly convert(FieldclimateForecastLast e) {
        FieldclimateHourly w = new FieldclimateHourly();
        w.setId(Long.valueOf(e.getId()));
        w.setTime(e.getTime());
        w.setUpdatedTime(e.getUpdatedTime());
        w.setUvIndex(null);
        w.setDewPoint(Double.valueOf(e.getDewpointtemperature()));
        w.setHumidity(Double.valueOf(e.getRelativehumidity()));
        w.setTemperature(Double.valueOf(e.getTemperature()));
        w.setRealfileTemperature(Double.valueOf(e.getFelttemperature()));
        w.setIsdaylight(e.getIsdaylight() == 1 ? true : false);
        w.setTotalLiquid(Double.valueOf(e.getPrecipitation()));
        w.setProbability(Double.valueOf(e.getPrecipitationProbability()));
        w.setRainspot(e.getRainspot());
        w.setPressure(Double.valueOf(e.getSealevelpressure()));
        w.setWindEdge(Double.valueOf(e.getWinddirection()));
        w.setWindDirection(SMSRule.smsGenerateElement(w.getWindEdge(), SMSRule.getGetWindDirectELRule()));
        w.setWindSpeed(Double.valueOf(e.getWindspeed()));


        return w;
    }

    public List getLast(String stationCode, Date date){
        return fieldclimateHourlyRepository.getLast(stationCode, date);
    }

    public List<WeatherHourly> getHourlies(String stationCode, int date){
        List<WeatherHourly> list = new LinkedList<>();
        fieldclimateHourlyRepository.getAllByStationCodeAndAndTime(stationCode,date).forEach(x->{
            WeatherHourly data = Utils.fixData(convert(x));
            list.add(data);
        });

        return list;
    }
}
