package weather.report.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Station {
    private String stationCode;
    private String stationName;
    private String stationNameVi;
    private Double lat;
    private Double lon;
    private String accuweatherKey;
    private Boolean apiEnable;

    public Station() {
    }

    public Station(String stationCode, String stationName, String stationNameVi, Double lat, Double lon, String accuweatherKey, Boolean apiEnable) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.stationNameVi = stationNameVi;
        this.lat = lat;
        this.lon = lon;
        this.accuweatherKey = accuweatherKey;
        this.apiEnable = apiEnable;
    }

    @Id
    @Column(name = "station_code")
    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    @Basic
    @Column(name = "station_name")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Basic
    @Column(name = "station_name_vi")
    public String getStationNameVi() {
        return stationNameVi;
    }

    public void setStationNameVi(String stationNameVi) {
        this.stationNameVi = stationNameVi;
    }

    @Basic
    @Column(name = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lon")
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Basic
    @Column(name = "accuweather_key")
    public String getAccuweatherKey() {
        return accuweatherKey;
    }

    public void setAccuweatherKey(String accuweatherKey) {
        this.accuweatherKey = accuweatherKey;
    }

    @Basic
    @Column(name = "api_enable")
    public Boolean getApiEnable() {
        return apiEnable;
    }

    public void setApiEnable(Boolean apiEnable) {
        this.apiEnable = apiEnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(stationCode, station.stationCode) &&
                Objects.equals(stationName, station.stationName) &&
                Objects.equals(stationNameVi, station.stationNameVi) &&
                Objects.equals(lat, station.lat) &&
                Objects.equals(lon, station.lon) &&
                Objects.equals(accuweatherKey, station.accuweatherKey) &&
                Objects.equals(apiEnable, station.apiEnable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stationCode, stationName, stationNameVi, lat, lon, accuweatherKey, apiEnable);
    }
}
