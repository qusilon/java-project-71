package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;

    @Parameters(paramLabel = "filepath1",
            description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format",
            defaultValue = "stylish")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
}
