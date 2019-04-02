package utill;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lixiliang on 2019/3/8.
 */
public class AESUtil {
    /**
     * 加密token，并返回base64编码字符串
     *
     * @param token
     * @return
     * @date 2017年6月9日
     * @author Ternence
     */
    public static String encode(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("token 不能为空");
        }


        byte[] key = "yunnex8888888888".getBytes(); // 加密密钥
        byte[] iv = "8888888888888888".getBytes(); // 加密IV

        byte[] oriData = token.getBytes();
        byte[] encData = null;
        try {
            encData = AESCoder.encrypt(oriData, AESCoder.ALGORITHM_CBC_PKCS5_SPEC, key, iv);
        } catch (Exception e) {
            return null;
        }
        return new String(Base64.encode(encData));
    }

    public static String encodeWithHex(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("token 不能为空");
        }
        byte[] key = "yunnex8888888888".getBytes(); // 加密密钥
        byte[] iv = "8888888888888888".getBytes(); // 加密IV

        byte[] oriData = token.getBytes();
        byte[] encData = null;
        try {
            encData = AESCoder.encrypt(oriData, AESCoder.ALGORITHM_CBC_PKCS5_SPEC, key, iv);
        } catch (Exception e) {
            return null;
        }
        return byte2HexStr(encData);
//        return new String(encData);
    }

    /**
     * 将二进制数组转换成16进制字符串
     *
     * @param buf
     * @return
     */
    public static String byte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 16进制字符串转换成二进制数组
     * @param hexStr
     * @return String
     */
    public static byte[] hexStr2Byte(String hexStr) {

        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return bytes;
    }

    /**
     * 解密token，返回原始token值
     *
     * @param encToken 加密后经过base64编码的token
     * @return
     * @date 2017年6月9日
     * @author Ternence
     */
    public static String decode(String encToken) {


        // 通过cookie获取到的值可能存在符号丢失问题，base64编码后面的等于号属于特殊符号，这里做补全处理
        switch (encToken.length() % 4) {
            case 3:
                encToken += "===";
                break; // 注：其实只需要补充一个或者两个等号，不存在补充三个等号的情况
            case 2:
                encToken += "==";
                break;
            case 1:
                encToken += "=";
                break;
            default:
        }

        if (StringUtils.isEmpty(encToken)) {
            throw new RuntimeException("token 不能为空");
        }

        byte[] key = "yunnex8888888888".getBytes(); // 加密密钥
        byte[] iv = "8888888888888888".getBytes(); // 加密IV

        byte[] oriData = Base64.decode(encToken.getBytes());
        byte[] token = null;
        try {
            token = AESCoder.decrypt(oriData, AESCoder.ALGORITHM_CBC_PKCS5_SPEC, key, iv);
        } catch (Exception e) {
            return null;
        }
        return new String(token);

    }

    /**
     * 解密token，返回原始token值
     *
     * @param encToken 加密后经过base64编码的token
     * @return
     * @date 2017年6月9日
     * @author Ternence
     */
    public static String decodeWithHex(String encToken) {

        if (StringUtils.isEmpty(encToken)) {
            throw new RuntimeException("token 不能为空");
        }

        byte[] key = "yunnex8888888888".getBytes(); // 加密密钥
        byte[] iv = "8888888888888888".getBytes(); // 加密IV
//        byte[] oriData = Base64.decode(encToken.getBytes());
        byte[] oriData = new byte[0];
        oriData = hexStr2Byte(encToken);
//        oriData = parseHexStr2Byte(encToken);
//        oriData = encToken.getBytes();
        byte[] token = null;
        try {
            token = AESCoder.decrypt(oriData, AESCoder.ALGORITHM_CBC_PKCS5_SPEC, key, iv);
        } catch (Exception e) {
            return null;
        }
        return new String(token);

    }
}
