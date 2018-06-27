package weather.report.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "darksky_hourly", schema = "fieldclimate")
public class DarkskyHourly extends WeatherHourly {
    private Long id;
    private String stationCode;
    private Timestamp updatedTime;
    private Timestamp time;
    private Double temperature;
    private Double realfileTemperature;
    private Double dewPoint;
    private Double humidity;
    private Double totalLiquid;
    private Double probability;
    private String liquidType;
    private Double windSpeed;
    private Double windEdge;
    private String windDirection;
    private Double windgustSpeed;
    private Double visibility;
    private Byte uvIndex;
    private Byte cloudCover;
    private Double ozone;
    private Double pressure;
    private String icon;
    private String summary;


    public DarkskyHourly() {
        super();
    }

    public DarkskyHourly(Long id, String stationCode, Timestamp updatedTime, Timestamp time, Double temperature, Double realfileTemperature, Double dewPoint, Double humidity, Double totalLiquid, Double probability, String liquidType, Double windSpeed, Double windEdge, String windDirection, Double windgustSpeed, Double visibility, Byte uvIndex, Byte cloudCover, Double ozone, Double pressure, String icon, String summary) {
        super(time, stationCode, temperature, totalLiquid, probability, uvIndex, windSpeed, humidity, windEdge, windDirection);
        this.id = id;
        this.stationCode = stationCode;
        this.updatedTime = updatedTime;
        this.time = time;
        this.temperature = temperature;
        this.realfileTemperature = realfileTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.totalLiquid = totalLiquid;
        this.probability = probability;
        this.liquidType = liquidType;
        this.windSpeed = windSpeed;
        this.windEdge = windEdge;
        this.windDirection = windDirection;
        this.windgustSpeed = windgustSpeed;
        this.visibility = visibility;
        this.uvIndex = uvIndex;
        this.cloudCover = cloudCover;
        this.ozone = ozone;
        this.pressure = pressure;
        this.icon = icon;
        this.summary = summary;
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
    @Column(name = "updated_time")
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
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
    @Column(name = "temperature")
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
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
    @Column(name = "total_liquid")
    public Double getTotalLiquid() {
        return totalLiquid;
    }

    public void setTotalLiquid(Double totalLiquid) {
        this.totalLiquid = totalLiquid;
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
    @Column(name = "liquid_type")
    public String getLiquidType() {
        return liquidType;
    }

    public void setLiquidType(String liquidType) {
        this.liquidType = liquidType;
    }

    @Basic
    @Column(name = "wind_speed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
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
    @Column(name = "wind_direction")
    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    @Basic
    @Column(name = "windgust_speed")
    public Double getWindgustSpeed() {
        return windgustSpeed;
    }

    public void setWindgustSpeed(Double windgustSpeed) {
        this.windgustSpeed = windgustSpeed;
    }

    @Basic
    @Column(name = "visibility")
    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    @Basic
    @Column(name = "uv_index")
    public Byte getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Byte uvIndex) {
        this.uvIndex = uvIndex;
    }

    @Basic
    @Column(name = "cloud_cover")
    public Byte getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Byte cloudCover) {
        this.cloudCover = cloudCover;
    }

    @Basic
    @Column(name = "ozone")
    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
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
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DarkskyHourly that = (DarkskyHourly) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(stationCode, that.stationCode) &&
                Objects.equals(updatedTime, that.updatedTime) &&
                Objects.equals(time, that.time) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(realfileTemperature, that.realfileTemperature) &&
                Objects.equals(dewPoint, that.dewPoint) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(totalLiquid, that.totalLiquid) &&
                Objects.equals(probability, that.probability) &&
                Objects.equals(liquidType, that.liquidType) &&
                Objects.equals(windSpeed, that.windSpeed) &&
                Objects.equals(windEdge, that.windEdge) &&
                Objects.equals(windDirection, that.windDirection) &&
                Objects.equals(windgustSpeed, that.windgustSpeed) &&
                Objects.equals(visibility, that.visibility) &&
                Objects.equals(uvIndex, that.uvIndex) &&
                Objects.equals(cloudCover, that.cloudCover) &&
                Objects.equals(ozone, that.ozone) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationCode, updatedTime, time, temperature, realfileTemperature, dewPoint, humidity, totalLiquid, probability, liquidType, windSpeed, windEdge, windDirection, windgustSpeed, visibility, uvIndex, cloudCover, ozone, pressure, icon, summary);
    }
}
