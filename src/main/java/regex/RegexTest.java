package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    private static boolean isCorrect(String str){
        String regEx1 = "^[\\dA-Za-z!#$&'()*+,/:;=?@\\-._~]{0,32}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(str);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        String str = "1122abCC!#$&'()*+,/:;=?@-._~sss%";
        System.out.println(isCorrect(str));
    }
}
