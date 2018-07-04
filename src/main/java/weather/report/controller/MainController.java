package weather.report.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import weather.report.entities.Station;
import weather.report.entities.WeatherHourly;
import weather.report.repositories.StationService;
import weather.report.services.CalculateHourlyService;
import weather.report.services.ReportModel;
import weather.report.sms.Utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report")
public class MainController {
    private static Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    CalculateHourlyService avgHourlyService;
    @Autowired
    private StationService stationService;
    @Autowired
    private ReportModel reportModel;

    @GetMapping("station")
    public ResponseEntity<List<Station>> get() {
        List<Station> stationList = stationService.getEnableStations();
        return new ResponseEntity<>(stationList, HttpStatus.OK);
    }

    @GetMapping("key")
    public ResponseEntity<Map<Timestamp, Integer>> getKey(String station, int date) {
        avgHourlyService.set(station, date);
        Map<Timestamp, Integer> timestampSet = avgHourlyService.getKeys();
//        log.info("size = "+timestampSet.size());
        return new ResponseEntity<>(Utils.sorted(timestampSet), HttpStatus.OK);
    }

    @GetMapping("sum")
    public ResponseEntity<Map<Timestamp, WeatherHourly>> getSum(String station, int date) {
        avgHourlyService.set(station, date);
        Map<Timestamp, WeatherHourly> weatherHourlyMap = avgHourlyService.calculateSum();
//        log.info("size = "+weatherHourlyMap.size());
        return new ResponseEntity<>(Utils.sorted(weatherHourlyMap), HttpStatus.OK);
    }

    @GetMapping("avg")
    public ResponseEntity<Map<Timestamp, WeatherHourly>> getAvg(String station, int date) {
        avgHourlyService.set(station, date);
        Map<Timestamp, WeatherHourly> weatherHourlyMap = avgHourlyService.calculateAvg();
//        log.info("size = "+weatherHourlyMap.size());
        return new ResponseEntity<>(Utils.sorted(weatherHourlyMap), HttpStatus.OK);
    }

    @GetMapping("windirection")
    public ResponseEntity<Map<String, Integer>> getWind(String station, int date) {
        avgHourlyService.set(station, date);
        Map<String, Integer> weatherHourlyMap = avgHourlyService.countWindirection();

        return new ResponseEntity<>(Utils.sorted(weatherHourlyMap), HttpStatus.OK);
    }

    @GetMapping("windMax")
    public ResponseEntity<Map.Entry<String, Integer>> getMaxWind(String station, Integer date) {
        avgHourlyService.set(station, date);
        Map<String, Integer> weatherHourlyMap = avgHourlyService.countWindirection();

        Map.Entry<String, Integer> maxEntry = Utils.getMaxWind(weatherHourlyMap);
//        log.info("max = "+ maxEntry.getKey()+" : "+maxEntry.getValue());
//        log.info("windmax = "+weatherHourlyMap.toString());
        return new ResponseEntity<>(maxEntry, HttpStatus.OK);
    }

    @GetMapping("sms")
    public ResponseEntity<List<String>> getReport() {
        List<String> list = reportModel.getLinkedListTotalSMS();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "download", method = RequestMethod.GET, params = {"forecat", "site"})
    public void download1(HttpServletResponse response, @RequestParam String forecat, @RequestParam String site) {
        try {
            int dates = 5;
            try {
                dates = Integer.valueOf(forecat.trim());
            } catch (NumberFormatException e) {

            }
            String reportlink = reportModel.getReport(dates, site);
            File file = new File(reportlink);
            byte[] data = FileUtils.readFileToByteArray(file);
            // Thiết lập thông tin trả về
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            response.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "download", method = RequestMethod.GET, params = {"forecat"})
    public void download1(HttpServletResponse response, @RequestParam String forecat) {
        download1(response, forecat, Utils.WE);
    }

    @RequestMapping(value = "download", method = RequestMethod.GET, params = {"site"})
    public void download2(HttpServletResponse response, @RequestParam String site) {
        download1(response, "5", site);
    }

    @RequestMapping(value = "download", method = RequestMethod.GET, params = {})
    public void download2(HttpServletResponse response) {
        download1(response, "5", Utils.WE);
    }
}
