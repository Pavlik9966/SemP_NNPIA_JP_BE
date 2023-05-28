package semp.nnpia.be.utils;

import java.util.HashMap;
import java.util.Map;

public class StateUtils {
    private static final Map<String, String> stateMap = new HashMap<>();

    static {
        stateMap.put("Albania", "AL");
        stateMap.put("Andorra", "AD");
        stateMap.put("Armenia", "AM");
        stateMap.put("Austria", "AT");
        stateMap.put("Azerbaijan", "AZ");
        stateMap.put("Belarus", "BY");
        stateMap.put("Belgium", "BE");
        stateMap.put("Bosnia and Herzegovina", "BA");
        stateMap.put("Bulgaria", "BG");
        stateMap.put("Croatia", "HR");
        stateMap.put("Cyprus", "CY");
        stateMap.put("Czech Republic", "CZ");
        stateMap.put("Denmark", "DK");
        stateMap.put("Estonia", "EE");
        stateMap.put("Finland", "FI");
        stateMap.put("France", "FR");
        stateMap.put("Georgia", "GE");
        stateMap.put("Germany", "DE");
        stateMap.put("Greece", "GR");
        stateMap.put("Hungary", "HU");
        stateMap.put("Iceland", "IS");
        stateMap.put("Ireland", "IE");
        stateMap.put("Italy", "IT");
        stateMap.put("Kazakhstan", "KZ");
        stateMap.put("Kosovo", "XK");
        stateMap.put("Latvia", "LV");
        stateMap.put("Liechtenstein", "LI");
        stateMap.put("Lithuania", "LT");
        stateMap.put("Luxembourg", "LU");
        stateMap.put("Malta", "MT");
        stateMap.put("Moldova", "MD");
        stateMap.put("Monaco", "MC");
        stateMap.put("Montenegro", "ME");
        stateMap.put("Netherlands", "NL");
        stateMap.put("North Macedonia", "MK");
        stateMap.put("Norway", "NO");
        stateMap.put("Poland", "PL");
        stateMap.put("Portugal", "PT");
        stateMap.put("Romania", "RO");
        stateMap.put("Russia", "RU");
        stateMap.put("San Marino", "SM");
        stateMap.put("Serbia", "RS");
        stateMap.put("Slovakia", "SK");
        stateMap.put("Slovenia", "SI");
        stateMap.put("Spain", "ES");
        stateMap.put("Sweden", "SE");
        stateMap.put("Switzerland", "CH");
        stateMap.put("Turkey", "TR");
        stateMap.put("Ukraine", "UA");
        stateMap.put("United Kingdom", "GB");
        stateMap.put("Vatican City", "VA");
    }

    public static String getStateShortcut(String stateName) {
        return stateMap.getOrDefault(stateName, "");
    }
}