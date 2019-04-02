package utill;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加解密工具类
 * 
 * @author Ternence
 * @date 2017年5月27日
 */
public final class AESCoder {
    private static final String ALGORITHM_NAME = "AES";

    public static final String ALGORITHM_CBC_PKCS5_SPEC = "AES/CBC/PKCS5Padding";
    public static final String ALGORITHM_ECB_PKCS5_SPEC = "AES/ECB/PKCS5Padding";

    private AESCoder() {

    }

    /**
     * 加密
     *
     * @param oriData 原始数据
     * @param key 加密密钥
     * @param iv 初始化向量
     * @return
     * @throws Exception
     * @date 2017年5月27日
     * @author Ternence
     */
    public static byte[] encrypt(byte[] oriData, String algorighmSpec, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM_NAME);
        Cipher cipher = Cipher.getInstance(algorighmSpec);

        if (iv != null) {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivspec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        }

        return cipher.doFinal(oriData);
    }

    /**
     * 解密
     *
     * @param encryptedData 加密数据
     * @param key 加密密钥
     * @param iv 初始化向量
     * @return
     * @throws Exception
     * @date 2017年5月27日
     * @author Ternence
     */
    public static byte[] decrypt(byte[] encryptedData, String algorighmSpec, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM_NAME);
        Cipher cipher = Cipher.getInstance(algorighmSpec);

        if (iv != null) {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivspec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
        }


        return cipher.doFinal(encryptedData);
    }


}
