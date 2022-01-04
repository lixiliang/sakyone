import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author lixiliang
 * @describe
 * @date 2021/4/30
 */
public class RegrexTest {
    @Test
    public void t1(){
        String requestPath = "/api/article/getList.action";
        String in = "/api/(?<path>.*)";
        String out = "/com-api/<path>";

        Pattern incomingRequestPathRegex = compile(in);
        Matcher m1 = incomingRequestPathRegex.matcher(requestPath);
        boolean f = m1.find();

        Pattern placeholderSearchPattern = compile("<([^>]*)");
        List<String> placeholders = new ArrayList<>();
        Matcher matcher = placeholderSearchPattern.matcher(out);
        while (matcher.find()) {
            placeholders.add(matcher.group(1));
        }
       String rewrittenPath = fill(m1,out,placeholders);
        System.out.println(rewrittenPath);
    }

    String fill(Matcher matcher,String value,List<String> placeholders) {
        String filledValue = value;
        for (String placeholder : placeholders) {
            String group = matcher.group(placeholder);
            filledValue = filledValue.replace("<" + placeholder + ">", group);
        }
        return filledValue;
    }
}
