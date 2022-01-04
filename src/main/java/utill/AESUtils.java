package utill;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author lixiliang
 * @describe
 * @date 2021/5/20
 */
public class AESUtils {
    private static final String CHARSET_NAME = "UTF-8";
    private static final String AES_NAME = "AES";
    // 加密模式
    public static final String CIPHER_MODE_ECB_PKCS7PADDING = "AES/CBC/PKCS7Padding";
    // 密钥
    public static final String KEY = "123";
    // 向量/偏移量
    public static final String IV = "1234567890000000";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    /**
     * 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encrypt(@NotNull String content, @NotNull String key, @NotNull String iv) {
        String result;
        try {
            byte[] ivByte = format16BitArray(iv, "0");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivByte);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE_ECB_PKCS7PADDING);
            byte[] keyByte = format16BitArray(key,"0");
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, AES_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            byte[] rs = cipher.doFinal(content.getBytes(CHARSET_NAME));
            result = byte2Base64(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }

    /**
     * 格式化成16位的byte
     * 只支持ASCII
     * @param str
     * @param padStr
     * @return
     */
    private static byte[] format16BitArray(String str, String padStr) {
        if (StringUtils.isBlank(str)) {
            //默认16个0
            return new byte[16];
        }
        StringBuilder builder = new StringBuilder(str);
        int len = str.length();
        try {
            if (len < 16) {
                for (int i = 0; i < 16 - len; i++) {
                    builder.append(padStr);
                }
                return builder.toString().getBytes(CHARSET_NAME);
            } else {
                String tmp = StringUtils.substring(str, 0, 16);
                return tmp.getBytes(CHARSET_NAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[16];
    }

    /**
     * 解密
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(@NotNull String content, @NotNull String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_MODE_ECB_PKCS7PADDING);
            byte[] keyByte = format16BitArray(key,"0");
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, AES_NAME);
            byte[] ivByte = format16BitArray(iv, "0");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String( cipher.doFinal(base642Byte(content)), CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }
    /**
     * 字节数组转Base64编码
     *
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * Base64编码转字节数组
     *
     * @param base64Key
     * @return
     * @throws IOException
     */
    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }
}
