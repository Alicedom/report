package weather.report.sms;

import weather.report.entities.WeatherHourly;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Utils {
    public static final String AC = "accuweather_hourly";
    public static final String FI = "fieldclimate_hourly";
    public static final String LA = "fieldclimatelast";
    public static final String DA = "darksky_hourly";
    public static final String GF = "gfs025_hourly";
    public static final String WE = "weather_hourly";

    public static final String DATA_RAW = "raw";
    public static final String SMS = "sms";

    public static String getSite(String site) {
        String table = null;
        String[] array = new String[]{LA, GF, DA, GF, FI, AC, WE};
        for (int i = 0; i < array.length; i++) {
            if (array[i].contains(site)) {
                table = array[i];
                break;
            }
        }
        return table;
    }


    public static String getCurTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return df.format(new Date(System.currentTimeMillis()));
    }


    public static void checkAndCreateNewFile(String saveDir) throws IOException {
        File file = new File(saveDir);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists())
            file.createNewFile();

    }


    public static WeatherHourly fixData(WeatherHourly e) {
        String windDirection = e.getWindDirection();
        if (windDirection.length() == 0) {
            windDirection = "__0";
        } else if (windDirection.length() == 1)
            windDirection = "__" + windDirection;
        else if (windDirection.length() == 2)
            windDirection = "_" + windDirection;
        e.setWindDirection(windDirection);

        // fix null data
        if (e.getUvIndex() == null) {e.setUvIndex((byte) 0);}
        if (e.getWindSpeed() == null) {e.setWindSpeed(0.0);}
        if (e.getProbability() == null) {e.setProbability(0.0);}
        if (e.getWindEdge() == null) {e.setWindEdge(0.0);}
        if (e.getTotalLiquid() == null) {e.setTotalLiquid(0.0);}
        if (e.getHumidity() == null) {e.setHumidity(0.0);}

        return e;
    }

    public static int count(final String string, final String substring) {
        int count = 0;
        int idx = 0;

        while ((idx = string.indexOf(substring, idx)) != -1) {
            idx++;
            count++;
        }

        return count;
    }

    public static <K> Map.Entry getMaxWind(Map<K, Integer> map) {

        Map.Entry<K, Integer> maxEntry = null;

        for (Map.Entry<K, Integer> entry : map.entrySet()) {

            if (maxEntry == null
                    || entry.getValue().compareTo(maxEntry.getValue()) >= 0) {
                maxEntry = entry;

            }
        }
        return maxEntry;
    }

    public static <K, V> List convert(Map<K, V> map) {
        List<V> list = new LinkedList<V>();

        map.forEach((k, v) -> list.add(v));

        return list;
    }

    // ham tra ve sms khong dau
    public static String getNonSign(String s) {
        String nfdNormalizedString = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static <K, V> Map<K, V> sorted(Map<K, V> map) {
        Map<K, V> sorted = new TreeMap<>();
        map.forEach((k, v) -> sorted.put(k, v));
        return sorted;
    }

    public static String convertELWind(String wind){
        List<String> sms = SMSRule.getListSMS(SMSRule.getGetWindDirectSMSRule());
        List<String> el = SMSRule.getListSMS(SMSRule.getGetWindDirectELRule());
        String smsName = null;
        for (int i = 0; i < el.size(); i++) {
            if(el.get(i).equals(wind)){

                smsName = sms.get(i);
                break;
            }
        }

        return smsName;
    }

}
