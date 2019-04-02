package utill;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lixiliang on 2019/3/8.
 */
public class AESUtilTest {

    @Test
    public void encode() {
        System.out.println(AESUtil.encode("123456"));
    }
    @Test
    public void decode() {
        System.out.println(AESUtil.decode("YgP1uhT9KXnvQ7+NFHGzzg=="));
    }

    @Test
    public void encodeWithHex() {
        System.out.println(AESUtil.encodeWithHex("1234567890你好啊long long long"));
    }
    @Test
    public void decodeWithHex() {
//        System.out.println(AESUtil.decodeWithHex("19d5cd89016bf8aa5b22b17ac1f244c0"));
        System.out.println(AESUtil.decodeWithHex("32138FB14285515695A4474047F916E656039639F189F752B11616D3EBD3FA6342B142247B26F507C0E9628FC37E098E"));
//        System.out.println(AESUtil.decodeWithHex("19D5CD89016BF8AA5B22B17AC1F244C0"));
    }
   /* @Test
    public void toHexString() {
        System.out.println(AESUtil.toHexString("12你好啊".getBytes()));
        System.out.println(AESUtil.bytesToHexString("12你好啊".getBytes()));
    }
    @Test
    public void hexStr2Str() {
        System.out.println(AESUtil.hexStr2Str("3132e4bda0e5a5bde5958a"));
    }*/




}
