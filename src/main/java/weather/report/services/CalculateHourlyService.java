package weather.report.services;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.repositories.AccuweatherHourlyService;
import weather.report.repositories.DarkskyHourlyService;
import weather.report.repositories.FieldclimateHourlyService;
import weather.report.repositories.Gfs025HourlyService;
import weather.report.sms.SMSRule;
import weather.report.sms.Utils;
import weather.report.entities.WeatherHourly;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.BiFunction;

@Service
public class CalculateHourlyService {
    public static BiFunction<WeatherHourly, WeatherHourly, WeatherHourly> sumWeather = (s1, s2) -> {
        WeatherHourly s3 = new WeatherHourly();
        s3.setStationCode(s2.getStationCode());
        s3.setTime(s2.getTime());
        s3.setWindDirection(s2.getWindDirection() + s1.getWindDirection());
        s3.setWindSpeed(s2.getWindSpeed() + s1.getWindSpeed());
        s3.setTotalLiquid(s2.getTotalLiquid() + s1.getTotalLiquid());
        s3.setProbability(s2.getProbability() + s1.getProbability());
        s3.setWindEdge(s2.getWindEdge() + s1.getWindEdge()); //???
        s3.setHumidity(s2.getHumidity() + s1.getHumidity());
        s3.setTemperature(s1.getTemperature() + s2.getTemperature());
        s3.setUvIndex((byte) (s2.getUvIndex() + s1.getUvIndex()));

        return s3;
    };
    public static BiFunction<WeatherHourly, Integer, WeatherHourly> avgWeather = (s2, count) -> {
        WeatherHourly s3 = new WeatherHourly();
        if(count == 0){
            s3 = s2;
        }else{
            s3.setStationCode(s2.getStationCode());
            s3.setTime(s2.getTime());
            s3.setWindSpeed(s2.getWindSpeed() / count);
            s3.setWindDirection(SMSRule.smsGenerateElement(s3.getWindSpeed(), SMSRule.getGetWindDirectELRule()));
            s3.setTotalLiquid(s2.getTotalLiquid() / count);
            s3.setProbability(s2.getProbability() / count);
            s3.setWindEdge(s2.getWindEdge() / count); //???
            s3.setHumidity(s2.getHumidity() / count);
            s3.setTemperature(s2.getTemperature() / count);
            s3.setUvIndex((byte) (s2.getUvIndex() / count));
        }

        return s3;
    };

//    private static Logger log = LoggerFactory.getLogger(CalculateHourlyService.class);

    @Autowired
    AccuweatherHourlyService accuweatherHourlyService;
    @Autowired
    DarkskyHourlyService darkskyHourlyService;
    @Autowired
    FieldclimateHourlyService fieldclimateHourlyService;
    @Autowired
    Gfs025HourlyService gfs025HourlyService;
    private Map<Timestamp, WeatherHourly> map1;
    private Map<Timestamp, WeatherHourly> map2;
    private Map<Timestamp, WeatherHourly> map3;
    private Map<Timestamp, WeatherHourly> map4;
    private Map<Timestamp, WeatherHourly> mapAvg;
    private Map<Timestamp, WeatherHourly> mapSum;
    private Map<Timestamp, Integer> keys;
    private String stationCode;
    private int date;

    public void set(String stationCode, int date) {

        if (this.stationCode == stationCode && this.date == date) {
            //old data

        } else {
            this.stationCode = stationCode;
            this.date = date;

            map1 = accuweatherHourlyService.getMapTimeHourlies(stationCode, date);
            map2 = darkskyHourlyService.getMapTimeHourlies(stationCode, date);
            map3 = fieldclimateHourlyService.getMapTimeHourlies(stationCode, date);
            map4 = gfs025HourlyService.getMapTimeHourlies(stationCode, date);
        }

    }

    /*
    Doan nay rat thu vi: gia tri su dung trong lamda la final
    neu khai bao Map<Timestamp,WeatherHourly> mapTG = new HashMap();
    map1.forEach((k, v) -> mapTG.merge(k,v,(val1, val2)-> sumWeather.apply(val1, val2)));
    khong cho phep, yeu cau mapTG la final??? final thi gia tri meger se add the nao???
    => test lai thi binh thuong
     */

    public Map<Timestamp, Integer> getKeys() {
        keys = new HashedMap<>();

        Set<Timestamp> sortedSet = new HashSet<Timestamp>();
        sortedSet.addAll(map1.keySet());
        sortedSet.addAll(map2.keySet());
        sortedSet.addAll(map3.keySet());
        sortedSet.addAll(map4.keySet());

        for (Timestamp k : sortedSet) {
            keys.put(k,countKey(k));
        }

        return keys;
    }

    /*
    Doan nay cung rat hay: error khi
    null.containsKey(key)= true
    => test thi false
     */
    public int countKey(Timestamp key) {
        int count = 0;

        if (map1.containsKey(key) && map1 != null) {
            count++;
        }
        if (map2.containsKey(key) && map2 != null) {
            count++;
        }
        if (map3.containsKey(key) && map3 != null) {
            count++;
        }
        if (map4.containsKey(key) && map4 != null) {
            count++;
        }

        return count;
    }

    public Map<Timestamp, WeatherHourly> calculateSum() {
        mapSum = new HashMap<>();

        map1.forEach((k, v) -> mapSum.merge(k, v,
                (v1, v2) -> sumWeather.apply(v1, v2)));
        map2.forEach((k, v) -> mapSum.merge(k, v,
                (v1, v2) -> sumWeather.apply(v1, v2)));
        map3.forEach((k, v) -> mapSum.merge(k, v,
                (v1, v2) -> sumWeather.apply(v1, v2)));
        map4.forEach((k, v) -> mapSum.merge(k, v,
                (v1, v2) -> sumWeather.apply(v1, v2)));
        return mapSum;
    }

    public Map<Timestamp, WeatherHourly> calculateAvg() {
        mapAvg = new HashedMap<>();

        if (mapSum == null || mapSum.size() == 0) {
            mapSum = calculateSum();
        }

        mapSum.forEach((k, v) -> {
            mapAvg.put(k, avgWeather.apply(v, countKey(k)));
        });

        return mapAvg;
    }

    public Map<String, Integer> countWindirection() {
        Map<String, Integer> countWind = new HashedMap<>();

        String allWindDirect = getAllWind();

        List<String> winddirectListRule = SMSRule.getListSMS(SMSRule.getGetWindDirectELRule());
        for (String e : winddirectListRule) {
            int count = Utils.count(allWindDirect, e);
            countWind.put(e, count);
        }

        return countWind;
    }

    private String getAllWind() {
        StringBuilder stringBuilder = new StringBuilder();

        if (mapSum == null || mapSum.size() == 0) {
            mapSum = calculateSum();
        }

        mapSum.forEach((k, v) -> stringBuilder.append(v.getWindDirection()));

        return stringBuilder.toString();
    }
}
