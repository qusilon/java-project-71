package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String getStylishFormat(List<Map<String, Object>> diffData) {

        StringBuilder result = new StringBuilder("{\n");
        diffData.forEach((currentMap) -> {
            String status = String.valueOf(currentMap.get("status"));
            String key = String.valueOf(currentMap.get("key"));
            Object value;
            switch (status) {
                case "unchanged":
                    value = currentMap.get("value");
                    result.append(String.format("    %s: %s\n", key, value));
                    break;
                case "removed":
                    value = currentMap.get("value");
                    result.append(String.format("  - %s: %s\n", key, value));
                    break;
                case "added":
                    value = currentMap.get("value");
                    result.append(String.format("  + %s: %s\n", key, value));
                    break;
                case "updated":
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
