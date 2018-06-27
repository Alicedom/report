package weather.report.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/*
    Class for weather data per hour
    Some function for convert data unit for many data source
 */
@Entity
public class WeatherHourly {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp time;
    private String stationCode;
    private Double temperature;
    private Double totalLiquid;
    private Double probability;
    private Byte uvIndex;
    private Double windSpeed;
    private Double humidity;
    private Double windEdge;
    private String windDirection;

    public WeatherHourly() {
    }

    public WeatherHourly(Timestamp time, String stationCode, Double temperature, Double rain, Double probability, Byte uvIndex, Double winSpeed, Double humidity, Double windEdge, String windDirection) {
        this.time = time;
        this.stationCode = stationCode;
        this.temperature = temperature;
        this.totalLiquid = rain;
        this.probability = probability;
        this.uvIndex = uvIndex;
        this.windSpeed = winSpeed;
        this.humidity = humidity;
        this.windEdge = windEdge;
        this.windDirection = windDirection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String station_code) {
        this.stationCode = station_code;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTotalLiquid() {
        return totalLiquid;
    }

    public void setTotalLiquid(Double totalLiquid) {
        this.totalLiquid = totalLiquid;
    }

    public Byte getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Byte uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }


    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWindEdge() {
        return windEdge;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public void setWindEdge(Double windEdge) {
        this.windEdge = windEdge;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void setWindEdge(double windEdge) {
        this.windEdge = windEdge;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }


}
