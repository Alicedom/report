package weather.report.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fieldclimate_forecast_last", schema = "fieldclimate")
public class FieldclimateForecastLast {
    private Integer id;
    private String stationCode;
    private Timestamp time;
    private Timestamp updatedTime;
    private String soiltemperature0To10Cm;
    private String soilmoisture0To10Cm;
    private String skintemperature;
    private String evapotranspiration;
    private String leafwetnessindex;
    private String potentialevapotranspiration;
    private String dewpointtemperature;
    private String referenceevapotranspirationFao;
    private String sensibleheatflux;
    private String precipitation;
    private String snowfraction;
    private String rainspot;
    private String temperature;
    private String felttemperature;
    private String pictocode;
    private String windspeed;
    private String winddirection;
    private String relativehumidity;
    private String sealevelpressure;
    private String precipitationProbability;
    private String convectivePrecipitation;
    private Integer isdaylight;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @Column(name = "soiltemperature_0to10cm")
    public String getSoiltemperature0To10Cm() {
        return soiltemperature0To10Cm;
    }

    public void setSoiltemperature0To10Cm(String soiltemperature0To10Cm) {
        this.soiltemperature0To10Cm = soiltemperature0To10Cm;
    }

    @Basic
    @Column(name = "soilmoisture_0to10cm")
    public String getSoilmoisture0To10Cm() {
        return soilmoisture0To10Cm;
    }

    public void setSoilmoisture0To10Cm(String soilmoisture0To10Cm) {
        this.soilmoisture0To10Cm = soilmoisture0To10Cm;
    }

    @Basic
    @Column(name = "skintemperature")
    public String getSkintemperature() {
        return skintemperature;
    }

    public void setSkintemperature(String skintemperature) {
        this.skintemperature = skintemperature;
    }

    @Basic
    @Column(name = "evapotranspiration")
    public String getEvapotranspiration() {
        return evapotranspiration;
    }

    public void setEvapotranspiration(String evapotranspiration) {
        this.evapotranspiration = evapotranspiration;
    }

    @Basic
    @Column(name = "leafwetnessindex")
    public String getLeafwetnessindex() {
        return leafwetnessindex;
    }

    public void setLeafwetnessindex(String leafwetnessindex) {
        this.leafwetnessindex = leafwetnessindex;
    }

    @Basic
    @Column(name = "potentialevapotranspiration")
    public String getPotentialevapotranspiration() {
        return potentialevapotranspiration;
    }

    public void setPotentialevapotranspiration(String potentialevapotranspiration) {
        this.potentialevapotranspiration = potentialevapotranspiration;
    }

    @Basic
    @Column(name = "dewpointtemperature")
    public String getDewpointtemperature() {
        return dewpointtemperature;
    }

    public void setDewpointtemperature(String dewpointtemperature) {
        this.dewpointtemperature = dewpointtemperature;
    }

    @Basic
    @Column(name = "referenceevapotranspiration_fao")
    public String getReferenceevapotranspirationFao() {
        return referenceevapotranspirationFao;
    }

    public void setReferenceevapotranspirationFao(String referenceevapotranspirationFao) {
        this.referenceevapotranspirationFao = referenceevapotranspirationFao;
    }

    @Basic
    @Column(name = "sensibleheatflux")
    public String getSensibleheatflux() {
        return sensibleheatflux;
    }

    public void setSensibleheatflux(String sensibleheatflux) {
        this.sensibleheatflux = sensibleheatflux;
    }

    @Basic
    @Column(name = "precipitation")
    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    @Basic
    @Column(name = "snowfraction")
    public String getSnowfraction() {
        return snowfraction;
    }

    public void setSnowfraction(String snowfraction) {
        this.snowfraction = snowfraction;
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
    @Column(name = "temperature")
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Basic
    @Column(name = "felttemperature")
    public String getFelttemperature() {
        return felttemperature;
    }

    public void setFelttemperature(String felttemperature) {
        this.felttemperature = felttemperature;
    }

    @Basic
    @Column(name = "pictocode")
    public String getPictocode() {
        return pictocode;
    }

    public void setPictocode(String pictocode) {
        this.pictocode = pictocode;
    }

    @Basic
    @Column(name = "windspeed")
    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    @Basic
    @Column(name = "winddirection")
    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    @Basic
    @Column(name = "relativehumidity")
    public String getRelativehumidity() {
        return relativehumidity;
    }

    public void setRelativehumidity(String relativehumidity) {
        this.relativehumidity = relativehumidity;
    }

    @Basic
    @Column(name = "sealevelpressure")
    public String getSealevelpressure() {
        return sealevelpressure;
    }

    public void setSealevelpressure(String sealevelpressure) {
        this.sealevelpressure = sealevelpressure;
    }

    @Basic
    @Column(name = "precipitation_probability")
    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    @Basic
    @Column(name = "convective_precipitation")
    public String getConvectivePrecipitation() {
        return convectivePrecipitation;
    }

    public void setConvectivePrecipitation(String convectivePrecipitation) {
        this.convectivePrecipitation = convectivePrecipitation;
    }

    @Basic
    @Column(name = "isdaylight")
    public Integer getIsdaylight() {
        return isdaylight;
    }

    public void setIsdaylight(Integer isdaylight) {
        this.isdaylight = isdaylight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldclimateForecastLast that = (FieldclimateForecastLast) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(stationCode, that.stationCode) &&
                Objects.equals(time, that.time) &&
                Objects.equals(updatedTime, that.updatedTime) &&
                Objects.equals(soiltemperature0To10Cm, that.soiltemperature0To10Cm) &&
                Objects.equals(soilmoisture0To10Cm, that.soilmoisture0To10Cm) &&
                Objects.equals(skintemperature, that.skintemperature) &&
                Objects.equals(evapotranspiration, that.evapotranspiration) &&
                Objects.equals(leafwetnessindex, that.leafwetnessindex) &&
                Objects.equals(potentialevapotranspiration, that.potentialevapotranspiration) &&
                Objects.equals(dewpointtemperature, that.dewpointtemperature) &&
                Objects.equals(referenceevapotranspirationFao, that.referenceevapotranspirationFao) &&
                Objects.equals(sensibleheatflux, that.sensibleheatflux) &&
                Objects.equals(precipitation, that.precipitation) &&
                Objects.equals(snowfraction, that.snowfraction) &&
                Objects.equals(rainspot, that.rainspot) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(felttemperature, that.felttemperature) &&
                Objects.equals(pictocode, that.pictocode) &&
                Objects.equals(windspeed, that.windspeed) &&
                Objects.equals(winddirection, that.winddirection) &&
                Objects.equals(relativehumidity, that.relativehumidity) &&
                Objects.equals(sealevelpressure, that.sealevelpressure) &&
                Objects.equals(precipitationProbability, that.precipitationProbability) &&
                Objects.equals(convectivePrecipitation, that.convectivePrecipitation) &&
                Objects.equals(isdaylight, that.isdaylight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stationCode, time, updatedTime, soiltemperature0To10Cm, soilmoisture0To10Cm, skintemperature, evapotranspiration, leafwetnessindex, potentialevapotranspiration, dewpointtemperature, referenceevapotranspirationFao, sensibleheatflux, precipitation, snowfraction, rainspot, temperature, felttemperature, pictocode, windspeed, winddirection, relativehumidity, sealevelpressure, precipitationProbability, convectivePrecipitation, isdaylight);
    }
}
