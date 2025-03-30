package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    @Test
    void testDifferStylish() throws IOException {
        Path expectedPath = Paths.get("./src/test/resources/expected_stylish.txt");
        String json1Path = "./src/test/resources/file1.json";
        String json2Path = "./src/test/resources/file2.json";

        String actual = Differ.generate(json1Path, json2Path);
        String expected = Files.readString(expectedPath);

        assertEquals(expected, actual);
    }
}
