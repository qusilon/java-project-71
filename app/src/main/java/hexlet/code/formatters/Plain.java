package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String getPlainFormat(List<Map<String, Object>> diffData) {

        StringBuilder result = new StringBuilder();
        diffData.forEach((currentMap) -> {
            String status = String.valueOf(currentMap.get("status"));
            String key = String.valueOf(currentMap.get("key"));
            Object value;
            switch (status) {
                case "unchanged":
                    break;
                case "removed":
                    result.append(String.format("Property '%s' was %s\n", key, status));
                    break;
                case "added":
                    value = getPlainObject(currentMap.get("value"));
                    result.append(String.format("Property '%s' was %s with value: %s\n", key, status, value));
                    break;
                case "updated":
                    Object value1 = getPlainObject(currentMap.get("value1"));
                    Object value2 = getPlainObject(currentMap.get("value2"));
                    result.append(String.format("Property '%s' was %s. From %s to %s\n", key, status, value1, value2));
                    break;
                default:
                    System.out.println("Error");
            }
        });
        return result.toString();
    }

    private static Object getPlainObject(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        } else if (value instanceof String) {
            return String.format("'%s'", value);
        }
        return value;
    }
}

