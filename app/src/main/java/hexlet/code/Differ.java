package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {

    private static Path getFilePath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private static Map<String, Object> parse(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] content = Files.readAllBytes(path);
        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    private static String stringGenerator(Map<String, Object> data1,
                                          Map<String, Object> data2,
                                          Map<String, String> dataKeys) {
        StringBuilder result = new StringBuilder("{\n");
        dataKeys.forEach((key, value) -> {
            switch (value) {
                case "unchanged":
                    result.append("    ").append(key).append(": ").append(data1.get(key)).append("\n");
                    break;
                case "deleted":
                    result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                    break;
                case "added":
                    result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
                    break;
                case "changed":
                    result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n").
                        append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
                    break;
                default:
                    System.out.println("Error");
            }
        });
        result.append("}");
        return result.toString();
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        Path path1 = getFilePath(filepath1);
        Map<String, Object> data1 = parse(path1);

        Path path2 = getFilePath(filepath2);
        Map<String, Object> data2 = parse(path2);

        Map<String, String> resultData = new TreeMap<>(Comparator.naturalOrder());
        data1.forEach((key, value) -> {
            if (data2.containsKey(key)) {
                if (value.equals(data2.get(key))) {
                    resultData.put(key, "unchanged");
                } else {
                    resultData.put(key, "changed");
                }
            } else {
                resultData.put(key, "deleted");
            }
        });

        data2.forEach((key, value) -> {
            if (!data1.containsKey(key)) {
                resultData.put(key, "added");
            }
        });

        return stringGenerator(data1, data2, resultData);
    }
}
