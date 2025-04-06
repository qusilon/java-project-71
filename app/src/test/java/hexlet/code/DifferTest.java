package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {

    @Test
    void testDifferJsonStylish() throws Exception {
        String expectedPath = "/home/qusilon/java-project-71/app/src/test/resources/expected_stylish.txt";
        String file1Path = "/home/qusilon/java-project-71/app/src/test/resources/file1.json";
        String file2Path = "/home/qusilon/java-project-71/app/src/test/resources/file2.json";
        String format = "stylish";

        String actual = Differ.generate(file1Path, file2Path, format);
        String expected = Files.readString(Paths.get(expectedPath));

        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlStylish() throws Exception {
        String expectedPath = "/home/qusilon/java-project-71/app/src/test/resources/expected_stylish.txt";
        String file1Path = "/home/qusilon/java-project-71/app/src/test/resources/file1.yaml";
        String file2Path = "/home/qusilon/java-project-71/app/src/test/resources/file2.yaml";
        String format = "stylish";

        String actual = Differ.generate(file1Path, file2Path, format);
        String expected = Files.readString(Paths.get(expectedPath));

        assertEquals(expected, actual);
    }
}
