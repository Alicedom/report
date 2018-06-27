package weather.report.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.sms.Utils;
import weather.report.entities.WeatherHourly;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class Services {

    @Autowired
    Repositories repositories;
    @Autowired
    AccuweatherHourlyService ac;
    @Autowired
    DarkskyHourlyService da;
    @Autowired
    FieldclimateHourlyService fi;
    @Autowired
    Gfs025HourlyService gf;

    public Map<Timestamp, WeatherHourly> getMapTimeHourlies(String site, String stationCode, int date) {
        Map<Timestamp, WeatherHourly> map = new HashMap();

        if(Utils.getSite(site).equals(Utils.AC)){
            map = ac.getMapTimeHourlies(stationCode, date);
        }else if(Utils.getSite(site).equals(Utils.DA)){
            map = da.getMapTimeHourlies(stationCode, date);
        }else if(Utils.getSite(site).equals(Utils.FI)){
            map = fi.getMapTimeHourlies(stationCode, date);
        }else if(Utils.getSite(site).equals(Utils.GF)){
            map = gf.getMapTimeHourlies(stationCode, date);
        }

        return map;
    }

    public WeatherHourly getHourly(String site, long id){

        return repositories.getDateHourly(id);
    }
}
