package weather.report.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.controller.MainController;
import weather.report.repositories.*;
import weather.report.sms.Utils;
import weather.report.entities.WeatherHourly;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Services {
    private static Logger logger = LoggerFactory.getLogger(Services.class);

    @Autowired
    AccuweatherHourlyService ac;
    @Autowired
    DarkskyHourlyService da;
    @Autowired
    Gfs025HourlyService gf;
    @Autowired
    FileclimateHourlyService fi;
    @Autowired
    FieldclimateForecastLastService la;
    @Autowired
    CalculateHourlyService to;
    public Map<Timestamp, WeatherHourly> getMapTimeHourlies(String site, String stationCode, int date) {
        Map<Timestamp, WeatherHourly> map = new HashMap();

        if (site == null || site.equals("") ||
                Utils.getSite(site).equals(Utils.TO)){
            to.set(stationCode, date);
            map = to.calculateAvg();
        }else if (Utils.getSite(site).equals(Utils.LA)) {
            map = la.getMapTimeHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.GF)) {
            map = gf.getMapTimeHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.DA)) {
            map = da.getMapTimeHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.AC)) {
            map = ac.getMapTimeHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.FI)) {
            map = fi.getMapTimeHourlies(stationCode, date);
        }

        return map;
    }

    public List<WeatherHourly> getTimeHourlies(String site, String stationCode, int date) {
        List<WeatherHourly> map = new LinkedList<>();

        if (site == null || Utils.getSite(site).equals(Utils.TO)) {
            to.set(stationCode, date);
            map = Utils.convert(to.calculateAvg());
        } else if (Utils.getSite(site).equals(Utils.LA)) {
            map = la.getHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.GF)) {
            map = gf.getHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.DA)) {
            map = da.getHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.AC)) {
            map = ac.getHourlies(stationCode, date);
        } else if (Utils.getSite(site).equals(Utils.FI)) {
            map = fi.getHourlies(stationCode, date);
        }

        return map;
    }

    public Map<String, List<WeatherHourly>> getData(String site, String stationCode, String ldate) {
        Map map = new WeakHashMap();

        Date date= convertDateTofullDate(ldate);
        map.put("date", date);

        if (Utils.getSite(site).equals(Utils.LA)) {
            map.put(Utils.LA, la.getLast(stationCode, date));
        } else if (Utils.getSite(site).equals(Utils.GF)) {
            map.put(Utils.GF, gf.getLast(stationCode, date));
        } else if (Utils.getSite(site).equals(Utils.DA)) {
            map.put(Utils.DA, da.getLast(stationCode, date));
        } else if (Utils.getSite(site).equals(Utils.AC)) {
            map.put(Utils.AC, ac.getLast(stationCode, date));
        } else if (Utils.getSite(site).equals(Utils.FI)) {
            map.put(Utils.FI, fi.getLast(stationCode, date));
        } else {
            map.put("site","Not found site");
        }

        return map;
    }

    public Date convertDateTofullDate(String ldate) {
        Date date = null;

        if (ldate.compareToIgnoreCase("20181") > 0)
            while (ldate.length() < 14) ldate += "0";

        try {
            date = new SimpleDateFormat("yyyyMMddhhmmss").parse(ldate);
        } catch (ParseException e) {
            date = new Date();
        }

        return date;

    }
}
