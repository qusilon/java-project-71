package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    @Test
    void testDifferJsonStylish() throws IOException {
        Path expectedPath = Paths.get("./src/test/resources/expected_stylish.txt");
        String file1Path = Paths.get("./src/test/resources/file1.json").toAbsolutePath().normalize().toString();
        String file2Path = Paths.get("./src/test/resources/file2.json").toAbsolutePath().normalize().toString();

        String actual = Differ.generate(file1Path, file2Path);
        String expected = Files.readString(expectedPath);

        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlStylish() throws IOException {
        Path expectedPath = Paths.get("./src/test/resources/expected_stylish.txt");
        String file1Path = Paths.get("./src/test/resources/file1.yaml").toAbsolutePath().normalize().toString();
        String file2Path = Paths.get("./src/test/resources/file2.yaml").toAbsolutePath().normalize().toString();

        String actual = Differ.generate(file1Path, file2Path);
        String expected = Files.readString(expectedPath);

        assertEquals(expected, actual);
    }
}
