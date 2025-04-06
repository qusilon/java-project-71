package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String getOutputFormat(List<Map<String, Object>> diffData, String format) throws Exception {
        switch (format) {
            case "stylish":
                return Stylish.getStylishFormat(diffData);
            case "plain":
                return "";
            default:
                throw new Exception();
        }
    }
}
