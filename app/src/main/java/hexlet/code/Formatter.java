package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String getOutputFormat(List<Map<String, Object>> diffData, String format) throws Exception {
        switch (format) {
            case "stylish":
                return Stylish.getStylishFormat(diffData);
            case "plain":
                return Plain.getPlainFormat(diffData);
            case "json":
                return Json.getJsonFormat(diffData);
            default:
                throw new Exception();
        }
    }
}
