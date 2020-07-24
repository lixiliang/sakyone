package sms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import utill.ConfigSms;
import utill.SHA256;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class SmsService {

    public void sendSms(String phone, String tplId, Map<String,Object> paramMap){
        JSONObject telData;
        JSONObject sendData = new JSONObject();
        JSONArray paramsData = new JSONArray();
        if(MapUtils.isNotEmpty(paramMap)){
            JSONObject paramJson = new JSONObject(paramMap);
            paramsData.put(paramJson);
        }
        long curSecond = System.currentTimeMillis() / 1000;
        //执行发送的方法
        long random = new Random().nextInt(999999) % 900000 + 100000;
        telData = new JSONObject();

        telData.put("nationcode", "86");
        telData.put("mobile", phone);
        sendData.put("tel", telData);
        sendData.put("tpl_id", tplId);
        sendData.put("params", paramsData);
        sendData.put("time", curSecond);
        sendData.put("extend", "");
        sendData.put("ext", "");
        sendData.put("sig", SHA256.getSHA256StrJava(String.format("appkey=%s&random=%d&time=%d&mobile=%s", ConfigSms.SMS_APPKEY, random, curSecond, phone)));

        String url = String.format("%s?sdkappid=%s&random=%d", ConfigSms.SMS_URL, ConfigSms.SMS_APPID, random);
        String result = doPost(url, sendData.toString(), "UTF-8", "text/xml");
        // log.info("processSmsTask腾讯云返回：" + result);
        JSONObject ret = new JSONObject(result);
        if (ret != null && ret.has("result") && 0 == ret.getInt("result")) {
            log.info("sendSms success,phone:{},tpl:{},pram:{}", phone,tplId,paramMap);
        } else {
            log.error("sendSms failed,phone:{},tpl:{},pram:{}", phone,tplId,paramMap);
        }
    }
    public static boolean isNull(Object obj) {
        if (null == obj || obj.toString().trim().equals("")) {
            return true;
        }
        return false;
    }
    /**
     * POST
     * posturl:网址
     * params:传参
     * charset:编码
     */
    public static String doPost(String posturl, String postStr, String charset, String contentType) {
        if (isNull(charset)) {
            charset = "UTF-8";
        }
        if (isNull(contentType)) {
            contentType = "application/x-www-form-urlencoded";
        }
        StringBuffer sb = new StringBuffer();
        try {
            // 建立连接
            URL url = new URL(posturl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            //3秒连接不上，关闭链接！(服务器通讯时间，并非数据交换时间)
            httpConn.setConnectTimeout(3000);

            // 设置连接属性
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(false);// 忽略缓存
            httpConn.setRequestMethod("POST");// 设置URL请求方法
            httpConn.setRequestProperty("Content-Type", contentType);
            httpConn.connect();
            // 建立输出流
            OutputStreamWriter outputStream = new OutputStreamWriter(httpConn.getOutputStream(), charset);
            outputStream.write(postStr);
            outputStream.flush();
            outputStream.close();
            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
                // 当正确响应时处理数据
                String readLine;
                BufferedReader responseReader;
                // 处理响应流，必须与服务器响应流输出的编码一致
                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charset));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine);
                }
                responseReader.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SmsService smsService = new SmsService();
        HashMap map = new HashMap();
//        map.put("a",1);
        smsService.sendSms("15975615231","659187",map);
        String str="{\"appKey\":\"key1\",\"attach\":\"attd\",\"cardNum\":\"234500000274000007\",\"exchangeCode\":\"nwE4Uh7ecH\",\"mchId\":1000,\"notifyUrl\":\"http://xx\",\"orderId\":3122563601051648,\"outTradeNo\":\"\"}";
    }
}