package utill;

import org.junit.Test;

/**
 * Created by lixiliang on 2019/3/8.
 */
public class AESUtilsTest {

    @Test
    public void encode() {
        String key  ="1234567812345678";
        String iv  ="0102030405060708";
        String iv2  ="0909090000011111";
        String eStr = AESUtils.encrypt("13100000103",key,iv);
        System.out.println(eStr);
        String content = AESUtils.decrypt(eStr,key,iv);
        System.out.println(content);
    }





}
