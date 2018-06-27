//package weather.report.sms;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import weather.report.entities.Station;
//import weather.report.entities.WeatherHourly;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//public class Dao {
//    private final static Logger logger = LoggerFactory.getLogger(Dao.class);
//    // connect DB
//    private Connection conn = null;
//
//    public Dao() {
//        String hostName = "172.16.0.252";
//        String dbName = "fieldclimate";
//        String userName = "root";
//        String password = "123456aA";
//        String dbURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
//                + "?useSSL=false&useUnicode=true&characterEncoding=utf8";
//
//        if (conn == null) {
//            try {
//                conn = DriverManager.getConnection(dbURL, userName, password);
//            } catch (SQLException ex) {
//                logger.error(ex.getMessage());
//            }
//        }
//    }
//
//    public ArrayList<Station> getStationEnalbeApi() {
//        ArrayList<Station> listStation = new ArrayList<Station>();
//
//        String sql = "SELECT * FROM station s WHERE s.api_enable = TRUE";
//
//        Statement statement;
//
//        try {
//            statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//
//            while (rs.next()) {
//
//                String station_code = rs.getString("station_code");
//                String station_name = rs.getString("station_name");
//                String station_name_vi = rs.getString("station_name_vi");
//                double lat = rs.getDouble("lat");
//                double lon = rs.getDouble("lon");
//                String accuweather_key = rs.getString("accuweather_key");
//
//                Station station = new Station(station_code, station_name, station_name_vi,lat,lon,accuweather_key, true);
//                listStation.add(station);
//            }
//
//            statement.close();
//
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        }
//
//        return listStation;
//    }
//    //getDataHourly
//    public List<WeatherHourly> getDataHourly(String station_code, String website, int after_day) {
//        List<WeatherHourly> list = new LinkedList<WeatherHourly>();
//
//        String tableHourly = website + "_hourly";
//
//        String SQL = "SELECT a.time,a.station_code,a.total_liquid,a.probability,a.temperature,a.uv_index,a.humidity,a.wind_speed,a.wind_edge" +
//                " FROM " + tableHourly + " a" +
//                " WHERE a.id IN (SELECT MAX(id) AS id FROM " + tableHourly + " a " +
//                " WHERE DATE(a.time) = (CURDATE() + INTERVAL " + after_day + " DAY) AND a.station_code = '" + station_code + "'" +
//                " GROUP BY a.station_code,a.time) ";
//
//
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(SQL);
//
//            while (rs.next()) {
//                WeatherHourly dataWeather = new WeatherHourly();
//                dataWeather.setTime(rs.getTimestamp("time"));
//                dataWeather.setStationCode(rs.getString("station_code"));
//                dataWeather.setTotalLiquid(rs.getDouble("total_liquid"));
//                dataWeather.setProbability(rs.getInt("probability"));
//                dataWeather.setTemperature(rs.getDouble("temperature"));
//                dataWeather.setUvIndex(rs.getByte("uv_index"));
//                dataWeather.setHumidity(rs.getDouble("humidity"));
//                dataWeather.setWindSpeed(rs.getDouble("wind_speed"));
//                dataWeather.setWindEdge(rs.getDouble("wind_edge"));
//
//                list.add(dataWeather);
//            }
//
//            stmt.close();
//        } catch (SQLException | NumberFormatException e) {
//            logger.error(e.getMessage());
//        }
//        return list;
//    }
//
//    public  void close(){
//        try {
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
