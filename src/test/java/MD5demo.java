import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MD5demo {
    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("partner_code", "100001");
        params.put("offer_id", "0101");
        params.put("action", "tob_provide_coupon");
        params.put("provide_no_type", "midas_tel_id");
        params.put("provide_uin", "86 18612345678");
        params.put("product_id", "txspvip_12month");
        params.put("out_trade_no", "2019081510001");
        params.put("user_id", "default");
        params.put("user_name", "default");
        params.put("user_id_type", "default");
        params.put("user_ip", "127.0.0.1");
        long l1 = System.currentTimeMillis();
        params.put("ts", (l1 / 1000) + "");
        params.put("version", "1.0");
        params.put("sale_price", "100");
        params.put("act_name", "测试");
        params.put("remark", "测试");
        String signkey = "123456";
        Object[] keys = params.keySet().toArray();
        Arrays.sort(keys);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            builder.append(params.get(keys[i]));
        }
        String signStr = builder.toString() + signkey;
        System.out.println("加签字符串:" + builder + signkey);
        String sig = MD5demo.md5sign(signStr, "utf-8");
        System.out.println("签名:" + sig);
        params.put("sig", sig);
    }

    public static String md5sign(String source, String encode) {
        String result = null;
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source.getBytes(encode));
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            result = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
