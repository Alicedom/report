package weather.report;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        System.out.println(map.containsKey("a") );
        Map<String, String> map1 = null;
        System.out.println(map1.containsKey("a") );
    }

}
