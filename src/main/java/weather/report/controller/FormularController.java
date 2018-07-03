package weather.report.controller;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import weather.report.entities.WeatherHourly;
import weather.report.services.Services;
import weather.report.sms.SMSRule;
import weather.report.sms.SessionFormular;
import weather.report.sms.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("calculate")
public class FormularController {
    private static Logger log = LoggerFactory.getLogger(FormularController.class);

    @Autowired
    private SessionFormular formular;

    @Autowired
    private Services services;


    @RequestMapping(value = "data", method = RequestMethod.GET, params = {"site", "station", "after"})
    public ResponseEntity getMapTimeHourlies(@RequestParam String site, @RequestParam String station, @RequestParam String after) {
        Map<Timestamp, WeatherHourly> map = services.getMapTimeHourlies(site.trim(), station.trim(), Integer.valueOf(after.trim()));
//        log.error("size: " + map.size());
        return new ResponseEntity<>(Utils.sorted(map), HttpStatus.OK);
    }

    @RequestMapping(value = "data", method = RequestMethod.GET, params = {"station", "after"})
    public ResponseEntity getMapTimeHourliesLA(@RequestParam String station, @RequestParam String after) {
        return this.getMapTimeHourlies(Utils.LA, station.trim(), after);
    }

    @RequestMapping(value = "data", method = RequestMethod.GET, params = {"site", "station"})
    public ResponseEntity getMapTimeHourlies0(@RequestParam String site, @RequestParam String station) {
        return this.getMapTimeHourlies(site.trim(), station.trim(), "0");
    }

    @RequestMapping(value = "data", method = RequestMethod.GET, params = {"station"})
    public ResponseEntity getMapTimeHourliesLA0(@RequestParam String station) {
        return this.getMapTimeHourlies(Utils.LA, station.trim(), "0");
    }

    @RequestMapping(value = "rain", method = RequestMethod.GET, params = {"site", "station", "after", "type"})
    public ResponseEntity calculateRainAndPercentRain(@RequestParam String site, @RequestParam String station, @RequestParam Integer after, @RequestParam String type) {

        Map<Timestamp, WeatherHourly> map = services.getMapTimeHourlies(site.trim(), station.trim(), after);
        formular.calculate(Utils.convert(map));
//        log.error("size: " + map.size());
        if (type.equals(Utils.DATA_RAW)) {
            return new ResponseEntity(formular.getPercentRain(), HttpStatus.OK);
        } else {
            return new ResponseEntity(formular.getReport_RainAndPercentRain(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "rain", method = RequestMethod.GET, params = {"site", "station"})
    public ResponseEntity calculateRainAndPercentRain(@RequestParam String site, @RequestParam String station) {
        return this.getMapTimeHourlies(site, station, "0");
    }

    @RequestMapping(value = "rain", method = RequestMethod.GET, params = {"station"})
    public ResponseEntity<Map<Timestamp, WeatherHourly>> calculateRainAndPercentRain(String station) {
        return this.getMapTimeHourlies(Utils.LA, station, "0");
    }

    @RequestMapping(value = "sms", method = RequestMethod.GET, params = {"site", "station", "after"})
    public ResponseEntity getSMS(@RequestParam String site, @RequestParam String station, String after) {
        Map<Timestamp, WeatherHourly> map = services.getMapTimeHourlies(site, station, 0);
//            log.error("size: " + map.size());
        formular.calculate(Utils.convert(map));

        return new ResponseEntity<>(formular.getSMSReport(), HttpStatus.OK);
    }

    @RequestMapping(value = "sms", method = RequestMethod.GET, params = {"site", "station"})
    public ResponseEntity getSMS(@RequestParam String site, @RequestParam String station) {
        return this.getMapTimeHourlies(site, station, "0");
    }

    @RequestMapping(value = "sms", method = RequestMethod.GET, params = {"station", "after"})
    public ResponseEntity getSMS(@RequestParam String station, @RequestParam Integer after) {
        return this.getMapTimeHourlies(Utils.LA, station, String.valueOf(after));
    }

    @RequestMapping(value = "sms", method = RequestMethod.GET, params = {"station"})
    public ResponseEntity getSMS(@RequestParam String station) {
        return this.getMapTimeHourlies(Utils.LA, station, "0");
    }

    @GetMapping("wind")
    public ResponseEntity windSpeed(double u, double v) {
        Map result = new HashedMap();

        double degree = (180 + (180 / Math.PI) * Math.atan2(u, v));
        degree = degree > 360 ? degree - 360 : degree;

        String el = SMSRule.smsGenerateElement(degree, SMSRule.getGetWindDirectELRule());
        String vi = SMSRule.smsGenerateElement(degree, SMSRule.getGetWindDirectSMSRule());
        String sp = String.valueOf(Math.sqrt(u * u + v * v));

        result.put("Degree", degree);
        result.put("Speed", sp);
        result.put("Bear", el);
        result.put("VN", vi);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "last", method = RequestMethod.GET, params = {"site", "station", "date"})
    public ResponseEntity getLast(@RequestParam String site, @RequestParam String station, @RequestParam String date) {
        Map<String,List<WeatherHourly>> map = services.getData(site,station,date);

//        log.error("size: " + map.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "last", method = RequestMethod.GET, params = {"station", "date"})
    public ResponseEntity getLast(@RequestParam String station, @RequestParam String date) {
        return this.getLast(Utils.LA, station, date);
    }

}
