package weather.report.services;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.report.entities.Station;
import weather.report.entities.WeatherHourly;
import weather.report.repositories.StationService;
import weather.report.sms.SMSRule;
import weather.report.sms.SessionFormular;
import weather.report.sms.Utils;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ReportModel {
    private final static Logger logger = LoggerFactory.getLogger(ReportModel.class);

    private final String TEMP_FILENAME = "/home/java/data/report/templates/template.xlsx";
    private final String OUT_FILENAME = "/home/java/data/report/Ban tin thoi tiet tu dong ";

    private final int START_ROW = 3;
    private final int DEFAULT_NUMBER_FORECAST = 5;


    @Autowired
    StationService stationService;

    @Autowired
    Services services;

    @Autowired
    SessionFormular formular;

    private List<String> linkedListTotalSMS;

    public String getReport(Integer forecatDates, String site) {
        String fileout = OUT_FILENAME + Utils.getCurTime()+site+".xlsx";

        linkedListTotalSMS = new LinkedList<>();
        FileInputStream templateFile = null;
        XSSFWorkbook workbook = null;

        try {
            File file = new File(TEMP_FILENAME);
            logger.info("file path:"+file.getPath());
            logger.info("file absolute path::"+file.getAbsolutePath());
            templateFile = new FileInputStream(file);
            workbook = new XSSFWorkbook(templateFile);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        List<Station> listStation = stationService.getEnableStations();

        for (int i = 0; i < listStation.size(); i++) {
            Station station = listStation.get(i);

            XSSFSheet sheet = workbook.cloneSheet(0, station.getStationNameVi());

            if(forecatDates <= 0 || forecatDates == null){
                forecatDates = DEFAULT_NUMBER_FORECAST;
            }
            for (int forecastDay = 0; forecastDay < forecatDates; forecastDay++) {
                int row = START_ROW + forecastDay;

                logger.info(site);
                logger.info(station.getStationCode());
                logger.info(forecastDay+" : "+site);
                Map<Timestamp, WeatherHourly> map = services.getMapTimeHourlies(site, station.getStationCode(), forecastDay);
                List<WeatherHourly> listStationHourlyData = Utils.convert(map);

                Cell cell = null;
                //Update the value of cell

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, forecastDay);
                String date = calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR);
                cell = sheet.getRow(row).getCell(0);
                cell.setCellValue(date);

                String sms = null;
                if (listStationHourlyData == null || listStationHourlyData.isEmpty()) {
                    sms = "Trạm " + station.getStationNameVi() + " không có dữ liệu dự báo\n";
                } else {

                    formular.calculate(listStationHourlyData);

                    sms = "Trạm " + station.getStationNameVi() + getSMSReport(date, formular);

                    if (listStationHourlyData.size() < 24) {
                        sms = "Trạm " + station.getStationNameVi() + " tính trên số giờ dữ liệu " + listStationHourlyData.size() + getSMSReport(date, formular);
                    }

                    Double sumUV = formular.getTotalUV();
                    cell = sheet.getRow(row).getCell(1);
                    cell.setCellValue(sumUV);

                    String maxTem = formular.getMaxTemperature();
                    cell = sheet.getRow(row).getCell(16);
                    cell.setCellValue(maxTem);

                    String minTem = formular.getMinTemperature();
                    cell = sheet.getRow(row).getCell(17);
                    cell.setCellValue(minTem);

                    Double humidity = formular.getAverageHumidity();
                    cell = sheet.getRow(row).getCell(18);
                    cell.setCellValue(humidity);

                    String maxWinddirect = formular.getMaxWindDirect();
                    cell = sheet.getRow(row).getCell(19);
                    cell.setCellValue(maxWinddirect);

                    Double avgWindSpeed = formular.getAverageWindSpeed();
                    cell = sheet.getRow(row).getCell(20);
                    cell.setCellValue(avgWindSpeed);

                    Hashtable<String, Double> rain = formular.getRain();
                    Hashtable<String, Double> percentRain = formular.getPercentRain();

                    if (!rain.isEmpty()) {
                        List<String> session = SMSRule.getListSMS(SMSRule.getSession());
                        for (int j = 0; j < session.size(); j++) {
                            if (rain.containsKey(session.get(j))) {
                                Double mount = Math.round(rain.get(session.get(j)) * 10.0) / 10.0;
                                cell = sheet.getRow(row).getCell(2 + j);
                                cell.setCellValue(mount);
                                cell = sheet.getRow(row).getCell(3 + j);
                                cell.setCellValue(percentRain.get(session.get(j)));
                            }
                        }
                    }
                }

                cell = sheet.getRow(row).getCell(21);
                cell.setCellValue(sms);

                cell = sheet.getRow(row).getCell(22);
                cell.setCellValue(Utils.getNonSign(sms));
                //log
//                logger.info(sms);
                linkedListTotalSMS.add(sms);
            }
        }



        FileOutputStream outFile = null;
        try {

            Utils.checkAndCreateNewFile(fileout);
            outFile = new FileOutputStream(fileout);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        try {

            templateFile.close();
            workbook.removeSheetAt(0);
            workbook.write(outFile);
            outFile.flush();
            outFile.close();

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return fileout;
//        return linkedListTotalSMS;
    }

    private String getSMSReport(String date, SessionFormular formular) {

        StringBuilder report = new StringBuilder();

        report.append(".\nDự báo ngày: ").append(date);
        report.append(".\n" + formular.getReport_MaxTemperature());
        report.append(".\n" + formular.getReport_MinTemperature());
        report.append(".\n" + formular.getReport_AverageHumidity());
        report.append(".\n" + formular.getReport_MaxWindDirect());
        report.append(" " + formular.getReport_AverageWindSpeed());
        report.append(".\n" + formular.getReport_NumberMaxSun());
        report.append(". " + formular.getReport_RainAndPercentRain());
        report.append(".\n");

        return report.toString();

    }

    public List<String> getLinkedListTotalSMS() {
        return linkedListTotalSMS;
    }
}
