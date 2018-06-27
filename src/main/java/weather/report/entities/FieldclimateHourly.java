package weather.report.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fieldclimate_hourly", schema = "fieldclimate")
public class FieldclimateHourly extends WeatherHourly{
    private Long id;
    private String stationCode;
    private Timestamp time;
    private Timestamp updatedTime;
    private Double dewPoint;
    private Double humidity;
    private Boolean isdaylight;
    private Double pictocode;
    private Double pressure;
    private Double probability;
    private String rainspot;
    private Double realfileTemperature;
    private Double temperature;
    private Double totalLiquid;
    private String windDirection;
    private Double windEdge;
    private Double windSpeed;



    public FieldclimateHourly() {
        super();
    }

    public FieldclimateHourly(Long id, String stationCode, Timestamp time, Timestamp updatedTime, Double dewPoint, Double humidity, Boolean isdaylight, Double pictocode, Double pressure, Double probability, String rainspot, Double realfileTemperature, Double temperature, Double totalLiquid, String windDirection, Double windEdge, Double windSpeed) {
        super(time, stationCode, temperature, totalLiquid, probability, null, windSpeed, humidity, windEdge, windDirection);
        this.id = id;
        this.stationCode = stationCode;
        this.time = time;
        this.updatedTime = updatedTime;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.isdaylight = isdaylight;
        this.pictocode = pictocode;
        this.pressure = pressure;
        this.probability = probability;
        this.rainspot = rainspot;
        this.realfileTemperature = realfileTemperature;
        this.temperature = temperature;
        this.totalLiquid = totalLiquid;
        this.windDirection = windDirection;
        this.windEdge = windEdge;
        this.windSpeed = windSpeed;
    }

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "station_code")
    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "updated_time")
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Basic
    @Column(name = "dew_point")
    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    @Basic
    @Column(name = "humidity")
    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    @Basic
    @Column(name = "isdaylight")
    public Boolean getIsdaylight() {
        return isdaylight;
    }

    public void setIsdaylight(Boolean isdaylight) {
        this.isdaylight = isdaylight;
    }

    @Basic
    @Column(name = "pictocode")
    public Double getPictocode() {
        return pictocode;
    }

    public void setPictocode(Double pictocode) {
        this.pictocode = pictocode;
    }

    @Basic
    @Column(name = "pressure")
    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    @Basic
    @Column(name = "probability")
    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Basic
    @Column(name = "rainspot")
    public String getRainspot() {
        return rainspot;
    }

    public void setRainspot(String rainspot) {
        this.rainspot = rainspot;
    }

    @Basic
    @Column(name = "realfile_temperature")
    public Double getRealfileTemperature() {
        return realfileTemperature;
    }

    public void setRealfileTemperature(Double realfileTemperature) {
        this.realfileTemperature = realfileTemperature;
    }

    @Basic
    @Column(name = "temperature")
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Basic
    @Column(name = "total_liquid")
    public Double getTotalLiquid() {
        return totalLiquid;
    }

    public void setTotalLiquid(Double totalLiquid) {
        this.totalLiquid = totalLiquid;
    }

    @Basic
    @Column(name = "wind_direction")
    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    @Basic
    @Column(name = "wind_edge")
    public Double getWindEdge() {
        return windEdge;
    }

    public void setWindEdge(Double windEdge) {
        this.windEdge = windEdge;
    }

    @Basic
    @Column(name = "windspeed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windspeed) {
        this.windSpeed = windspeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldclimateHourly that = (FieldclimateHourly) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(stationCode, that.stationCode) &&
                Objects.equals(time, that.time) &&
                Objects.equals(updatedTime, that.updatedTime) &&
                Objects.equals(dewPoint, that.dewPoint) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(isdaylight, that.isdaylight) &&
                Objects.equals(pictocode, that.pictocode) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(probability, that.probability) &&
                Objects.equals(rainspot, that.rainspot) &&
                Objects.equals(realfileTemperature, that.realfileTemperature) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(totalLiquid, that.totalLiquid) &&
                Objects.equals(windDirection, that.windDirection) &&
                Objects.equals(windEdge, that.windEdge) &&
                Objects.equals(windSpeed, that.windSpeed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationCode, time, updatedTime, dewPoint, humidity, isdaylight, pictocode, pressure, probability, rainspot, realfileTemperature, temperature, totalLiquid, windDirection, windEdge, windSpeed);
    }
}
