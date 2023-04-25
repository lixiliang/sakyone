package utill;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lixiliang
 * @describe
 * @date 2023/2/6
 */
public class StringHelper {
    private final  static Pattern p = Pattern.compile("[A-Z]");
    public static String charToUnderLine(String param) {
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }

        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
    private static String convertToColumn(List<String> validFields) {
        return validFields.stream().map(f-> StringHelper.charToUnderLine(f)).collect(Collectors.joining(","));
    }
}
