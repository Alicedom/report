//package weather.report.services;
//
//import weather.report.entities.WeatherHourly;
//
//public class SumWeatherHourly {
//    private Count temperature;
//    private Count totalLiquid;
//    private Count probability;
//    private Count uvIndex;
//    private Count windSpeed;
//    private Count humidity;
//    private Count windEdge;
//
//    public void addWeatherHourly(WeatherHourly weatherHourly) {
//        Double data = null;
//
//        data = weatherHourly.getTemperature();
//        sumData(this.temperature, data);
//
//        data = weatherHourly.getTotalLiquid();
//        sumData(this.totalLiquid, data);
//
//        data = weatherHourly.getProbability();
//        sumData(this.probability, data);
//
//        data = Double.valueOf(weatherHourly.getUvIndex());
//        sumData(this.uvIndex, data);
//
//        data = weatherHourly.getWindSpeed();
//        sumData(this.windSpeed, data);
//
//        data = weatherHourly.getHumidity();
//        sumData(this.humidity, data);
//
//        data = weatherHourly.getWindEdge();
//        sumData(this.windEdge, data);
//    }
//
//    private void sumData(Count count, Double data) {
//        if (data == null) {
//        } else if (count == null) {
//            count = new Count(data);
//        } else if (count != null) {
//            count.up(data);
//        }
//    }
//
//    public Count getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(Count temperature) {
//        this.temperature = temperature;
//    }
//
//    public Count getTotalLiquid() {
//        return totalLiquid;
//    }
//
//    public void setTotalLiquid(Count totalLiquid) {
//        this.totalLiquid = totalLiquid;
//    }
//
//    public Count getProbability() {
//        return probability;
//    }
//
//    public void setProbability(Count probability) {
//        this.probability = probability;
//    }
//
//    public Count getUvIndex() {
//        return uvIndex;
//    }
//
//    public void setUvIndex(Count uvIndex) {
//        this.uvIndex = uvIndex;
//    }
//
//    public Count getWindSpeed() {
//        return windSpeed;
//    }
//
//    public void setWindSpeed(Count windSpeed) {
//        this.windSpeed = windSpeed;
//    }
//
//    public Count getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(Count humidity) {
//        this.humidity = humidity;
//    }
//
//    public Count getWindEdge() {
//        return windEdge;
//    }
//
//    public void setWindEdge(Count windEdge) {
//        this.windEdge = windEdge;
//    }
//
//    public class Count {
//        int nummerSite;
//        Double data;
//
//        public Count(Double data) {
//            this.nummerSite = 1;
//            this.data = data;
//        }
//
//        public void up(Double data) {
//            this.nummerSite++;
//            this.data += data;
//
//        }
//    }
//}
