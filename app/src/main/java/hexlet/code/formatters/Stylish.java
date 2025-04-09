package hexlet.code.formatters;

import hexlet.code.Changes;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String getStylishFormat(List<Map<String, Object>> diffData) {

        StringBuilder result = new StringBuilder("{\n");
        diffData.forEach((currentMap) -> {
            Changes status = (Changes) currentMap.get("status");
            String key = String.valueOf(currentMap.get("key"));
            Object value;
            switch (status) {
                case UNCHANGED:
                    value = currentMap.get("value");
                    result.append(String.format("    %s: %s\n", key, value));
                    break;
                case REMOVED:
                    value = currentMap.get("value");
                    result.append(String.format("  - %s: %s\n", key, value));
                    break;
                case ADDED:
                    value = currentMap.get("value");
                    result.append(String.format("  + %s: %s\n", key, value));
                    break;
                case UPDATED:
                    Object value1 = currentMap.get("value1");
                    Object value2 = currentMap.get("value2");
                    result.append(String.format("  - %s: %s\n", key, value1));
                    result.append(String.format("  + %s: %s\n", key, value2));
                    break;
                default:
                    System.out.println("Error");
            }
        });
        result.append("}");
        return result.toString();
    }
}
