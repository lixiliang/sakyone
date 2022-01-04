package utill;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Util {

    public static String encodeBase64(String content) {
        String encodedString = new String(Base64.encodeBase64(content.getBytes(StandardCharsets.UTF_8), false), StandardCharsets.UTF_8);
        return encodedString;
    }

    public static String decodeBase64(String base64Content) {
        String decodeString = new String(Base64.decodeBase64(base64Content),StandardCharsets.UTF_8);
        return decodeString;
    }

}
