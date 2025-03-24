package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {

    private static Path getFilePath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private static Map<String, Object> parse(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] content = Files.readAllBytes(path);
        return mapper.readValue(content, new TypeReference<>(){});
    }

    public static void getData(String filepath1, String filepath2) throws IOException {
        Path path1 = getFilePath(filepath1);
        Map<String, Object> data1 = parse(path1);

        Path path2 = getFilePath(filepath2);
        Map<String, Object> data2 = parse(path2);

        System.out.println(data1.toString());
        System.out.println(data2.toString());
    }

}
