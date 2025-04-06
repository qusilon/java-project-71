package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);
        List<Map<String, Object>> diffData = diffBuilder(data1, data2);
        return Formatter.getOutputFormat(diffData, format);
    }

    private static Map<String, Object> getData(String filePath) throws Exception {
        Path path = getFilePath(filePath);
        String fileFormat = getFileFormat(path);
        return Parser.parse(path, fileFormat);
    }

    private static Path getFilePath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private static String getFileFormat(Path path) {
        String result = path.toString();
        return result.substring(result.indexOf('.') + 1);
    }

    private static List<Map<String, Object>> diffBuilder(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> sortedKeys = new TreeSet<>();
        sortedKeys.addAll(data1.keySet());
        sortedKeys.addAll(data2.keySet());

        List<Map<String, Object>> diffData = new ArrayList<>();

        sortedKeys.forEach(key -> {
            if (data1.containsKey(key) && data2.containsKey(key)) {
                if (Objects.equals(data1.get(key), data2.get(key))) {
                    Map<String, Object> currentMap = new HashMap<>();
                    currentMap.put("key", key);
                    currentMap.put("value", data1.get(key));
                    currentMap.put("status", "unchanged");
                    diffData.add(currentMap);
                } else {
                    Map<String, Object> currentMap = new HashMap<>();
                    currentMap.put("key", key);
                    currentMap.put("value1", data1.get(key));
                    currentMap.put("value2", data2.get(key));
                    currentMap.put("status", "updated");
                    diffData.add(currentMap);
                }
            } else if (data1.containsKey(key) && !data2.containsKey(key)) {
                Map<String, Object> currentMap = new HashMap<>();
                currentMap.put("key", key);
                currentMap.put("value", data1.get(key));
                currentMap.put("status", "removed");
                diffData.add(currentMap);
            } else {
                Map<String, Object> currentMap = new HashMap<>();
                currentMap.put("key", key);
                currentMap.put("value", data2.get(key));
                currentMap.put("status", "added");
                diffData.add(currentMap);
            }
        });
        return diffData;
    }
}
