package hexlet.code.formatters;

import hexlet.code.Changes;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String getPlainFormat(List<Map<String, Object>> diffData) {

        StringBuilder result = new StringBuilder();
        diffData.forEach((currentMap) -> {
            Changes status = (Changes) currentMap.get("status");
            String key = String.valueOf(currentMap.get("key"));
            Object value;
            switch (status) {
                case UNCHANGED:
                    break;
                case REMOVED:
                    result.append(String.format("Property '%s' was removed\n", key));
                    break;
                case ADDED:
                    value = getPlainObject(currentMap.get("value"));
                    result.append(String.format("Property '%s' was added with value: %s\n", key, value));
                    break;
                case UPDATED:
                    Object value1 = getPlainObject(currentMap.get("value1"));
                    Object value2 = getPlainObject(currentMap.get("value2"));
                    result.append(String.format("Property '%s' was updated. From %s to %s\n", key, value1, value2));
                    break;
                default:
                    System.out.println("Error");
            }
        });
        return result.toString().trim();
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

