package weather.report.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import weather.report.entities.Station;
import weather.report.repositories.*;
import weather.report.services.CalculateHourlyService;
import weather.report.services.ReportModel;
import weather.report.sms.Utils;
import weather.report.entities.WeatherHourly;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("report")
public class MainController {
    private static Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private StationService stationService;

    @GetMapping("getEnableStation")
    public ResponseEntity<List<Station>> get() {
        List<Station> stationList = stationService.getEnableStations();
        return new ResponseEntity<>(stationList, HttpStatus.OK);
    }

    @Autowired
    private Services services;

    @GetMapping("weatherHourly")
    public ResponseEntity<Map<Timestamp, WeatherHourly>> getList(String site,String stationCode, int day){
        Map<Timestamp, WeatherHourly> map = services.getMapTimeHourlies(Utils.getSite(site), stationCode,day);
        log.info("size = "+map.size());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("getHourly")
    public ResponseEntity<WeatherHourly> getList(String site, int id){
        WeatherHourly weatherHourly = services.getHourly(Utils.getSite(site),id);
        log.info("size = "+weatherHourly.toString());
        return new ResponseEntity<>(weatherHourly,HttpStatus.OK);
    }

    @Autowired
    CalculateHourlyService avgHourlyService;

    @GetMapping("getKeys")
    public ResponseEntity<Map<Timestamp, Integer>> getKey(String stationCode, int date){
        avgHourlyService.set(stationCode,date);
        Map<Timestamp, Integer> timestampSet = avgHourlyService.getKeys();
        log.info("size = "+timestampSet.size());
        return new ResponseEntity<>(timestampSet,HttpStatus.OK);
    }

    @GetMapping("countKey")
    public ResponseEntity<Integer> countKey(String stationCode, int date, Timestamp key){
        avgHourlyService.set(stationCode,date);
        int countKey = avgHourlyService.countKey(key);
        log.info("size = "+countKey);
        return new ResponseEntity<>(countKey,HttpStatus.OK);
    }

    @GetMapping("getSum")
    public ResponseEntity<Map<Timestamp, WeatherHourly>> getSum(String stationCode, int date){
        avgHourlyService.set(stationCode,date);
        Map<Timestamp, WeatherHourly> weatherHourlyMap = avgHourlyService.calculateSum();
        log.info("size = "+weatherHourlyMap.size());
        return new ResponseEntity<>(weatherHourlyMap,HttpStatus.OK);
    }

    @GetMapping("getAvg")
    public ResponseEntity<Map<Timestamp, WeatherHourly>> getAvg(String stationCode, int date){
        avgHourlyService.set(stationCode,date);
        Map<Timestamp, WeatherHourly> weatherHourlyMap = avgHourlyService.calculateAvg();
        log.info("size = "+weatherHourlyMap.size());
        return new ResponseEntity<>(weatherHourlyMap,HttpStatus.OK);
    }

    @GetMapping("countWindirection")
    public ResponseEntity<Map<String, Integer>> getWind(String stationCode, int date){
        avgHourlyService.set(stationCode,date);
        Map<String, Integer> weatherHourlyMap = avgHourlyService.countWindirection();

        return new ResponseEntity<>(weatherHourlyMap,HttpStatus.OK);
    }

    @GetMapping("getMaxWind")
    public ResponseEntity<Map.Entry<String, Integer>> getMaxWind(String stationCode, Integer date){
        avgHourlyService.set(stationCode,date);
        Map<String, Integer> weatherHourlyMap = avgHourlyService.countWindirection();

        Map.Entry<String, Integer> maxEntry= Utils.getMaxWind(weatherHourlyMap);
        log.info("max = "+ maxEntry.getKey()+" : "+maxEntry.getValue());
        log.info("windmax = "+weatherHourlyMap.toString());
        return new ResponseEntity<>(maxEntry,HttpStatus.OK);
    }

    @Autowired
    private ReportModel reportModel;

    @GetMapping("getReport")
    public ResponseEntity<String> getReport(Integer forecatDates){
        reportModel.getReport(forecatDates);
        return new ResponseEntity<>("Done",HttpStatus.OK);
    }

}
