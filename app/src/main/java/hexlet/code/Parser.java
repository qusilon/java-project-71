package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(Path path, String format) throws IOException {
        byte[] content = Files.readAllBytes(path);
        switch (format) {
            case "json":
                ObjectMapper mapperJson = new ObjectMapper();
                return mapperJson.readValue(content, new TypeReference<>() {
                });
            case "yaml", "aml":
                ObjectMapper mapperYaml = new YAMLMapper();
                return mapperYaml.readValue(content, new TypeReference<>() {
                });
            default:
                System.out.println("Error");
                throw new IOException();
        }

    }
}
