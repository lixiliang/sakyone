import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

public class SignOfCMCC {
    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);

        String appSecret = "appSecret"; // 分配的密钥
        AsyncSendGoodsRequest model = new AsyncSendGoodsRequest();
        model.setAppKey("appKey");
        model.setChannelId("GSYKSPK");
        model.setNum("1");
        model.setOrderId("1533118783828437269");
        model.setProductType("YOUKU");
        model.setShipInfo("{\"phoneNum\":\"\",\"account\":\"12926154687\"}");
        model.setSkuCode("YOUKU_1M_XSK");
        model.setSrvType("recharge");
        model.setTimestamp("currentTimeMillis");
        Map<String, String> paramValues = ConvertObjToMap(model); // 将实体类转换成map集合
        paramValues.remove("sign");
        String mySign = signToOpenApi(paramValues, appSecret);
        System.out.println(mySign);
    }

    /**
     * 利用反射技术,将bean转换成map集合
     *
     * @param
     * @return hashMap<String,String>
     */
    public static Map<String, String> ConvertObjToMap(Object obj) {
        Map<String, String> reMap = new HashMap<String, String>();
        // 如果bean为空,则返回空
        if (obj == null)
            return null;

        // 获取bean的所有属性，返回Field数组
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field field = obj.getClass().getDeclaredField(fields[i].getName());
                    field.setAccessible(true);
                    String filedVal = null;
                    Object o = field.get(obj);
                    if (o instanceof String) {
                        filedVal = (String) o;
                    }
                    if (filedVal != null && !"".equals(filedVal)) {
                        reMap.put(fields[i].getName(), filedVal);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

    /**
     * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
     * uppercase(hex(sha1(secretkey1value1key2value2...secret))
     *
     * @param paramValues 参数列表
     * @param secret
     * @return
     */
    public static String signToOpenApi(Map<String, String> paramValues, String secret) {
        return signToOpenApi(paramValues, null, secret);
    }

    /**
     * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
     *
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
     */
    public static String signToOpenApi(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
        try{
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if ( ignoreParamNames != null && ignoreParamNames.size() > 0 ){
                for (String ignoreParamName : ignoreParamNames){
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            sb.append(secret);
            for (String paramName : paramNames){
                sb.append(paramName).append("=").append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static byte[] getSHA1Digest(String data) throws IOException{
        byte[] bytes = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse){
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes){
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++){
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if ( hex.length() == 1 ){
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }


}
@Data
class AsyncSendGoodsRequest {

    @NotBlank(message = "参数{channelId},不能为空")
    private String channelId;	// 代理商Id，由蜂助手分配

    @NotBlank(message = "参数{appKey},不能为空")
    private String appKey;		// 接入服务的appKey，由蜂助手分配

    @NotBlank(message = "参数{timestamp},不能为空")
    private String timestamp;	// 时间戳，使用中国区的时间，要求时间偏离在10分钟以内。

    @NotBlank(message = "参数{srvType},不能为空")
    private String srvType;		// 服务类型，recharge充值类，card卡密类

    private String productType;	// 商品所属分类编码。请向商务索要。

    @NotBlank(message = "参数{skuCode},不能为空")
    private String skuCode;		// 商品编码。请向商务索要。

    @NotBlank(message = "参数{orderId},不能为空")
    private String orderId;		// 商户订单Id，要求唯一

    @NotBlank(message = "参数{num},不能为空")
    private String num;			// 充值类都是1，卡密类在1-100之间

    @NotBlank(message = "参数{shipInfo},不能为空")
    private String shipInfo;	// 发货信息。不同的productType有不同的参数名。比如{"phoneNum":"1312222222","account":"12312"}，请用utf8编码，urlencode对shipInfo的值做处理，防止中文和特殊字符乱码。


    private String returl;		// 回调的URL

    @NotBlank(message = "参数{sign},不能为空")
    private String sign;		// 参数签名，详见签名算法

}