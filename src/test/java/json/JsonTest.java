package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonTest {
    @Test
    public void fastJsonBug() throws UnsupportedEncodingException {
        final String DEATH_STRING = "{\"a\":\"\\x";
//        JSON.parse(DEATH_STRING);

        String line = "[{\\x22a\\x22:\\x22a\\xB1ph.\\xCD\\x86\\xBEI\\xBA\\xC3\\xBCiM+\\xCE\\xCE\\x1E\\xDF7\\x1E\\xD9z\\xD9Q\\x8A}\\xD4\\xB2\\xD5\\xA0y\\x98\\x08@\\xE1!\\xA8\\xEF^\\x0D\\x7F\\xECX!\\xFF\\x06IP\\xEC\\x9F[\\x85;\\x02\\x817R\\x87\\xFB\\x1Ch\\xCB\\xC7\\xC6\\x06\\x8F\\xE2Z\\xDA^J\\xEB\\xBCF\\xA6\\xE6\\xF4\\xF7\\xC1\\xE3\\xA4T\\x89\\xC6\\xB2\\x5Cx]";
        line = line.replaceAll("\\\\x", "%");
        String decodeLog = URLDecoder.decode(line, "UTF-8");
        System.out.println(decodeLog);
        try {
            Object obj = JSON.parse(decodeLog);
            obj = JSON.parse(DEATH_STRING);
        } catch (Exception e) {
            assertEquals(e.getClass(), JSONException.class);
            assertTrue(e.getMessage().contains("invalid escape character \\x"));
        }
    }

    @Test
    public void testParse() {
        String jsonstr = "\t\t\t{\n" +
                "\t\t\t\t\"quantity\": \"1\",\n" +
                "\t\t\t\t\"product_id\": \"test.vip.1m.auto\",\n" +
                "\t\t\t\t\"transaction_id\": \"1000000579269478\",\n" +
                "\t\t\t\t\"original_transaction_id\": \"1000000579259627\",\n" +
                "\t\t\t\t\"purchase_date\": \"2019-10-15 09:10:50 Etc/GMT\",\n" +
                "\t\t\t\t\"purchase_date_ms\": \"1571130650000\",\n" +
                "\t\t\t\t\"purchase_date_pst\": \"2019-10-15 02:10:50 America/Los_Angeles\",\n" +
                "\t\t\t\t\"original_purchase_date\": \"2019-10-15 08:55:51 Etc/GMT\",\n" +
                "\t\t\t\t\"original_purchase_date_ms\": \"1571129751000\",\n" +
                "\t\t\t\t\"original_purchase_date_pst\": \"2019-10-15 01:55:51 America/Los_Angeles\",\n" +
                "\t\t\t\t\"expires_date\": \"2019-10-15 09:15:50 Etc/GMT\",\n" +
                "\t\t\t\t\"expires_date_ms\": \"1571130950000\",\n" +
                "\t\t\t\t\"expires_date_pst\": \"2019-10-15 02:15:50 America/Los_Angeles\",\n" +
                "\t\t\t\t\"web_order_line_item_id\": \"1000000047541601\",\n" +
                "\t\t\t\t\"is_trial_period\": \"true\",\n" +
                "\t\t\t\t\"is_in_intro_offer_period\": \"false\"\n" +
                "\t\t\t}";
        InApp app = JSON.parseObject(jsonstr, InApp.class);
        System.out.println(app);
        System.out.println(JSON.toJSONString(app));
    }

    @Test
    public void test01() {
        InApp app = JSON.parseObject("{}", InApp.class);
        System.out.println(app);
        String id = "bbdev2";
        String devBundleId = "bbdev";
        String prodBundleId = "bb";
        if (!id.equals(devBundleId) && !id.equals(prodBundleId)) {

            System.out.println("not same");
        } else {
            System.out.println("same");
        }
    }
    Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    @Test
    public void test02() {

        MemberBaseInfo baseInfo = new MemberBaseInfo();
        baseInfo.setHeadimgurl("url").setMemberUuid("uuid").setNickName("name").setNum("159");
        String str = JSON.toJSONString(baseInfo, SerializerFeature.WriteClassName);
        System.out.println(str);
    }

    @Test
   public void test03(){
       MemberBaseInfo baseInfo = new MemberBaseInfo();
       baseInfo.setHeadimgurl("url").setMemberUuid("uuid").setNickName("name").setNum("159");
       String str = JSON.toJSONString(baseInfo, SerializerFeature.WriteClassName);
       byte[] bytes = str.getBytes(DEFAULT_CHARSET);
        String str1 = new String(bytes, DEFAULT_CHARSET);
//       String str = new String(bytes, DEFAULT_CHARSET);
       MemberBaseInfo deserialize = JSON.parseObject(str1, MemberBaseInfo.class);
       System.out.println(deserialize.getNum());

    }

    @Test
    public void test04(){
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        List<String> ids = Arrays.asList("a","b");
        String js = "{\n" +
                "  \"@type\": \"json.MemberBaseInfo\",\n" +
                "  \"headimgurl\": \"\",\n" +
                "  \"memberUuid\": \"00076d546fcc4ae99f86571665f007fc\",\n" +
                "  \"nickName\": \"埋堆用户113****0345\",\n" +
                "  \"num\": \"11300000345\"\n" +
                "}";
        String js2 = "{\n" +
                "\t\"@type\": \"json.MemberBaseInfo\",\n" +
                "\t\"headimgurl\": \"\",\n" +
                "\t\"memberUuid\": \"000de8209d564228a28f09e8018104bb\",\n" +
                "\t\"nickName\": \"埋堆用户113****0345\",\n" +
                "\t\"num\": \"11300000345\"\n" +
                "}";
        List<String> dataList = Arrays.asList(js,js2);
        MemberBaseInfo info = JSON.parseObject(js,MemberBaseInfo.class);
//        System.out.println(info);
        List<MemberBaseInfo> MemberBaseInfos = dataList.stream().map(item -> {
            return JSON.parseObject(item, MemberBaseInfo.class);
        }).collect(Collectors.toList());
//        List<MemberBaseInfo> MemberBaseInfos = Lists.newArrayListWithCapacity(ids.size());
//        MemberBaseInfos = ids.stream().map(item -> JSON.parseObject(js,MemberBaseInfo.class)).collect(Collectors.toList());
        MemberBaseInfos.forEach(System.out::print);
    }
    @Test
    public void test05(){

        List<String> names = Lists.newArrayList("Answer", "AnswerAIL", "AI");
        Map<String, MemberBaseInfo> map = names.stream().collect(Collectors.toMap(v -> v, v -> new MemberBaseInfo()));

        Map<String, String> result = names.stream().collect(
                Collectors.toMap((uuid) -> uuid,
                        (uuid) -> names.stream().filter(item -> item.equals("xx"+uuid)).findAny().orElseGet(() -> getBaseInfo(uuid)))
        );
        System.out.println(result);
    }

    String getBaseInfo(String name){
        System.out.println("getBaseInfo:"+name);
        if(name.equals("AI")) return null;
        return name+"null-data";
    }
}
