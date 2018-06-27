package weather.report.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "gfs025_hourly", schema = "fieldclimate")
public class Gfs025Hourly extends WeatherHourly{
    private Long id;
    private String stationCode;
    private Timestamp time;
    private Timestamp updatedTime;
    private Timestamp getTime;
    private String dir;
    private Byte file;
    private Double humidity;
    private Double temperature;
    private Double realfileTemperature;
    private Double dewPoint;
    private Double temperatureSurface;
    private Double totalLiquid;
    private Double probability;
    private Double uwind;
    private Double vwind;
    private Double windSpeed;
    private Double windEdge;
    private String windDirection;
    private Double windgustSpeed;
    private Double visibility;
    private Byte uvIndex;
    private Byte cloudCover;
    private Double pressure;

    public Gfs025Hourly() {
        super();
    }

    public Gfs025Hourly(Long id, String stationCode, Timestamp time, Timestamp updatedTime, Timestamp getTime, String dir, Byte file, Double humidity, Double temperature, Double realfileTemperature, Double dewPoint, Double temperatureSurface, Double totalLiquid, Double probability, Double uwind, Double vwind, Double windSpeed, Double windEdge, String windDirection, Double windgustSpeed, Double visibility, Byte uvIndex, Byte cloudCover, Double pressure) {
        super(time, stationCode, temperature, totalLiquid, probability,null, windSpeed, humidity, windEdge, windDirection);
        this.id = id;
        this.stationCode = stationCode;
        this.time = time;
        this.updatedTime = updatedTime;
        this.getTime = getTime;
        this.dir = dir;
        this.file = file;
        this.humidity = humidity;
        this.temperature = temperature;
        this.realfileTemperature = realfileTemperature;
        this.dewPoint = dewPoint;
        this.temperatureSurface = temperatureSurface;
        this.totalLiquid = totalLiquid;
        this.probability = probability;
        this.uwind = uwind;
        this.vwind = vwind;
        this.windSpeed = windSpeed;
        this.windEdge = windEdge;
        this.windDirection = windDirection;
        this.windgustSpeed = windgustSpeed;
        this.visibility = visibility;
        this.uvIndex = uvIndex;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
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
    @Column(name = "get_time")
    public Timestamp getGetTime() {
        return getTime;
    }

    public void setGetTime(Timestamp getTime) {
        this.getTime = getTime;
    }

    @Basic
    @Column(name = "dir")
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Basic
    @Column(name = "file")
    public Byte getFile() {
        return file;
    }

    public void setFile(Byte file) {
        this.file = file;
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
    @Column(name = "temperature_surface")
    public Double getTemperatureSurface() {
        return temperatureSurface;
    }

    public void setTemperatureSurface(Double temperatureSurface) {
        this.temperatureSurface = temperatureSurface;
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
    @Column(name = "uwind")
    public Double getUwind() {
        return uwind;
    }

    public void setUwind(Double uwind) {
        this.uwind = uwind;
    }

    @Basic
    @Column(name = "vwind")
    public Double getVwind() {
        return vwind;
    }

    public void setVwind(Double vwind) {
        this.vwind = vwind;
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
    @Column(name = "pressure")
    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gfs025Hourly that = (Gfs025Hourly) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(stationCode, that.stationCode) &&
                Objects.equals(time, that.time) &&
                Objects.equals(updatedTime, that.updatedTime) &&
                Objects.equals(getTime, that.getTime) &&
                Objects.equals(dir, that.dir) &&
                Objects.equals(file, that.file) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(realfileTemperature, that.realfileTemperature) &&
                Objects.equals(dewPoint, that.dewPoint) &&
                Objects.equals(temperatureSurface, that.temperatureSurface) &&
                Objects.equals(totalLiquid, that.totalLiquid) &&
                Objects.equals(probability, that.probability) &&
                Objects.equals(uwind, that.uwind) &&
                Objects.equals(vwind, that.vwind) &&
                Objects.equals(windSpeed, that.windSpeed) &&
                Objects.equals(windEdge, that.windEdge) &&
                Objects.equals(windDirection, that.windDirection) &&
                Objects.equals(windgustSpeed, that.windgustSpeed) &&
                Objects.equals(visibility, that.visibility) &&
                Objects.equals(uvIndex, that.uvIndex) &&
                Objects.equals(cloudCover, that.cloudCover) &&
                Objects.equals(pressure, that.pressure);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationCode, time, updatedTime, getTime, dir, file, humidity, temperature, realfileTemperature, dewPoint, temperatureSurface, totalLiquid, probability, uwind, vwind, windSpeed, windEdge, windDirection, windgustSpeed, visibility, uvIndex, cloudCover, pressure);
    }

    @Override
    public String toString() {
        return "Gfs025Hourly{" +
                "id=" + id +
                ", stationCode='" + stationCode + '\'' +
                ", time=" + time +
                ", updatedTime=" + updatedTime +
                ", getTime=" + getTime +
                ", dir='" + dir + '\'' +
                ", file=" + file +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", realfileTemperature=" + realfileTemperature +
                ", dewPoint=" + dewPoint +
                ", temperatureSurface=" + temperatureSurface +
                ", totalLiquid=" + totalLiquid +
                ", probability=" + probability +
                ", uwind=" + uwind +
                ", vwind=" + vwind +
                ", windSpeed=" + windSpeed +
                ", windEdge=" + windEdge +
                ", windDirection='" + windDirection + '\'' +
                ", windgustSpeed=" + windgustSpeed +
                ", visibility=" + visibility +
                ", uvIndex=" + uvIndex +
                ", cloudCover=" + cloudCover +
                ", pressure=" + pressure +
                '}';
    }
}
