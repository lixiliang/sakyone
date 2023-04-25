import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import entity.MemberAtMsg;
import iap.AppleIapResult;
import json.InApp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import utill.GTMDateUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/9/13.
 */
@Slf4j
public class Test {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        String o1 = map.putIfAbsent("key1", "v1");
        String o2 = map.putIfAbsent("key1", "v2");
        System.out.println("o1" + o1);
        System.out.println("o2" + o2);
//        map.remove(o2);
        System.out.println(map.get("key1"));

        List<Token> tokens = Lists.newArrayList();
        Token t1 = new Token("a");
        Token t2 = new Token("b");
        tokens.add(t1);
        tokens.add(t2);
        tokens.add(Token.MULTI);
        tokens.add(new Token("f"));
        String top = topic(tokens);
//        Token







       /* InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println("ip: "+ip);
        System.out.println("hostname: "+hostName);
        int [] a ={1,2};
//        int [] b =[1,2];
        test1();
        t2();*/

    }

    private static String topic(List<Token> tokens) {
        List<String> strTokens = tokens.stream().map(Token::toString).collect(Collectors.toList());
        String topic = String.join("/", strTokens);
        System.out.println(topic);
        return topic;
    }


    public static void test1() {
        String IP = null;
        String host = null;

        try {
            InetAddress ia = InetAddress.getLocalHost();
            host = ia.getHostName();//获取计算机主机名
            IP = ia.getHostAddress();//获取计算机IP
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println(host);
        System.out.println(IP);
    }

    private static void t2() {
        StringBuilder IFCONFIG = new StringBuilder();
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        IFCONFIG.append(inetAddress.getHostAddress().toString() + "\n");
                    }

                }
            }
        } catch (SocketException ex) {

            System.out.println(IFCONFIG);
        } finally {
            System.out.println(IFCONFIG);
        }
    }

    @org.junit.Test
    public void testRound() {
        String str="E:\\git\\gitlab\\Mdd_AppApi\\target\\mdd-api.jar";
        double i = 2.3000000000000003d;
        System.out.println(Math.round(i * 100) * 0.01d);
        String result = String.format("%.2f", i);
        System.out.println(result);
    }

    @org.junit.Test
    public void test02() {
        Entity e1 = new Entity("a1", "t1");
        Entity e2 = new Entity("a2", "t2");
        Entity e3 = new Entity("a3", "t3");
        List<Entity> list = Arrays.asList(e1, e2, e3);
        List<Entity> list2 = list.stream().filter((e) -> !e.getName().equals("a1") && !e.getText().equals("t2")).collect(Collectors.toList());
        System.out.println(list2);
        System.out.println(list);
    }

    @org.junit.Test
    public void test03() {
        String str = "select count(1) count from coupon_weixin cw left join base_info b on cw.base_info_id = b.id WHERE cw.del_flag = ? ; # Time: 190104 11:49:03";
        int index = str.indexOf("# Time:");
        System.out.println(index);
        if (index > 0) {
            str = str.substring(0, index);
            System.out.println(str);
        }
    }

    @org.junit.Test
    public void test04() {
        SlowSql slowSql = new SlowSql();
        slowSql.setSql("select count(1) count from mall_product_order WHERE type in ( ? ) and status in ( ? );");
        slowSql.setDb("DB-02-2");
        slowSql.setCount(1);
        slowSql.setMaxExaminedRow(100);
        slowSql.setMaxQueryTime(10);

        SlowSql slowSql1 = new SlowSql();
        slowSql1.setSql("select count(1) count from mall_product_order WHERE type in ( ? ) and status in ( ? ); ");
        slowSql1.setDb("DB-02-2");
        slowSql1.setCount(2);
        slowSql.setMaxExaminedRow(200);
        slowSql.setMaxQueryTime(20);
        List<SlowSql> list1 = Arrays.asList(slowSql, slowSql1);
        List<SlowSql> list2 = Lists.newArrayList();
        for (SlowSql str : list1) {
            if (!list2.contains(str)) {
                list2.add(str);
            }
        }
        System.out.println(list2.size());
    }

    @org.junit.Test
    public void test05() {
        String sql = "select count(1) count from (select car.branch_id branchId,count(1) chargeTime from customer_account_record car left join customer_member cm on car.member_id=cm.id where cm.shop_id=8015 and car.event_id in( select id from customer_event where event_en_name in( 'pos_recharge', 'subscription_recharge', 'wxapp_recharge', 'alipay_recharge' ) ) and car.create_time>= '2019-01-16' and car.create_time <'2019-01-17' GROUP BY car.branch_id  ) t;";

        String wsql = ParameterizedOutputVisitorUtils.parameterize(sql, JdbcConstants.MYSQL);
        System.out.println(wsql);
    }

    @org.junit.Test
    public void test06() {
        String sql = "DELETE FROM cy_campaign WHERE id IN ( 3904144 )";

        if (sql.equalsIgnoreCase("select 1;")
                || sql.equalsIgnoreCase("select user();")
                || sql.toLowerCase().startsWith("select get_lock")
                || (!sql.toLowerCase().startsWith("select")
                && !sql.toLowerCase().startsWith("update")
                && !sql.toLowerCase().startsWith("insert")
        )
        ) {
            System.out.println("ignore sql:" + sql);
        }

    }

    @org.junit.Test
    public void test07() {
        String sql = "SELECT COUNT(cr.id) count, SUM(ABS(cr.amount)) total, branch_id FROM customer_bonus_record cr LEFT JOIN customer_event ce ON ce.id = cr.event_id LEFT JOIN customer_member cm ON cr.member_id = cm.id WHERE 1= ? and cm.shop_id= ? and cr.branch_id= ? and ce.event_en_name in ( ? ) and cr.create_time >= ? and cr.create_time <= ? GROUP BY cr.branch_id; ";
        int lastIndex = sql.lastIndexOf(";");
        if (lastIndex > 0) {
            sql = sql.substring(0, lastIndex).trim();
        }
        System.out.println(sql);
    }

    @org.junit.Test
    public void test08() {
        String str = " ";
        System.out.println(StringUtils.isBlank(str));
        Map<String, String> paraMap = new TreeMap<String, String>();
        paraMap.put("a2", "a2");
        paraMap.put("a", "a1");
        paraMap.put("d", "d1");
        paraMap.put("c", "c1");
        for (String key : paraMap.keySet()) {
            System.out.println("key:" + key + " value:" + paraMap.get(key));
        }
    }

    @org.junit.Test
    public void test09() {
        String url = "/10549840601068216320";
        String url2 = "/cardcc/101012345678901234567111/";
        String url3 = "/cardcc/101012345678901234567111cc/abc";
//        String serialUrlRegex  = "(.+/)([\\d]{20,})";
        String serialUrlRegex = "(.+/)(10[\\d]{18,})(/*)(.*)";
        Pattern p = Pattern.compile(serialUrlRegex);
        Matcher matcher = p.matcher(url);
        if (matcher.matches()) {
            System.out.println("url:" + matcher.group(2));
        }
        Matcher matcher2 = p.matcher(url2);
        if (matcher2.matches()) {
            System.out.println("url2:" + matcher2.group(2));
        }
        Matcher matcher3 = p.matcher(url3);
        if (matcher3.matches()) {
            System.out.println("url3:" + matcher3.group(2));
        }
    }

    @org.junit.Test
    public void test10() {
        BigDecimal num1 = new BigDecimal(0.005);
        BigDecimal num2 = new BigDecimal(1000000);
        BigDecimal num3 = new BigDecimal(-1000000);
        //尽量用字符串的形式初始化
        BigDecimal num12 = new BigDecimal("0.005");
        BigDecimal num22 = new BigDecimal("1000000");
        BigDecimal num32 = new BigDecimal("-1000000");

        //加法
        BigDecimal result1 = num1.add(num2);
        BigDecimal result12 = num12.add(num22);
        //减法
        BigDecimal result2 = num1.subtract(num2);
        BigDecimal result22 = num12.subtract(num22);
        //乘法
        BigDecimal result3 = num1.multiply(num2);
        BigDecimal result32 = num12.multiply(num22);
        //绝对值
        BigDecimal result4 = num3.abs();
        BigDecimal result42 = num32.abs();
        //除法
        BigDecimal result5 = num2.divide(num1, 20, BigDecimal.ROUND_HALF_UP);
        BigDecimal result52 = num22.divide(num12, 20, BigDecimal.ROUND_HALF_UP);

        System.out.println("加法用value结果：" + result1);
        System.out.println("加法用string结果：" + result12);

        System.out.println("减法value结果：" + result2);
        System.out.println("减法用string结果：" + result22);

        System.out.println("乘法用value结果：" + result3);
        System.out.println("乘法用string结果：" + result32);

        System.out.println("绝对值用value结果：" + result4);
        System.out.println("绝对值用string结果：" + result42);

        System.out.println("除法用value结果：" + result5);
        System.out.println("除法用string结果：" + result52);
    }

    @org.junit.Test
    public void test11() {
        String str = "我是，,中文~`^=";
        str = str.replaceAll("[\\pP]", "");
        System.out.println(str);
    }

    @org.junit.Test
    public void test12() {
        String str = "2019-07-17 00:00:00";
        String tmp = str.replaceFirst(":", "时").replaceFirst(":", "分");
        String str1 = tmp + "秒";
        Assert.assertEquals("2019-07-17 00时00分00秒", str1);
    }

    @org.junit.Test
    public void test13() {
        String data = "13100000379";
        if (data.matches("^(-?\\d+)(\\.\\d+)?$") && !data.contains("%")) {
            if (data.toString().matches("^[-\\+]?[\\d]*$")) {
                System.out.println("matches #,#0");
            } else {
                System.out.println("matches #,##0.00");
            }
        } else {
            System.out.println("str");
        }
    }

    @org.junit.Test
    public void test14() {
     /*   org.json.JSONObject jsonObject = new org.json.JSONObject();
        jsonObject.put("a",1111);
        System.out.println(JSON.toJSONString(jsonObject));*/
        T t = new T();
        A a = new A();
//        a.name = "test1";
        List<A> la = new ArrayList<>();
        la.add(a);
        t.setAlist(la);
        System.out.println(JSON.toJSONString(t));

    }


    @org.junit.Test
    public void test15() {
        List<String> list = Lists.newArrayList();
        for (String a : list) {
            System.out.println(a);
        }
    }

    @org.junit.Test
    public void test16() {
        System.out.println(JSON.toJSONString(null));
    }


    class T {
        List<A> alist;

        public List<A> getAlist() {
            return alist;
        }

        public void setAlist(List<A> alist) {
            this.alist = alist;
        }
    }


    @org.junit.Test
    public void Test17() {
        A a = new A();
        a.setName("nam");
        a.setId(111);
        a.setN1("N1");
//        FieldMain.excluedField(a,Lists.newArrayList("name"));
        FieldMain.includeField(a, Lists.newArrayList("name", "seq"));
        System.out.println(a);
    }

    @org.junit.Test
    public void Test18() {
        Set<MemberAtMsg> asMsgSet = Sets.newHashSet();
        for (int i = 0; i < 10; i++) {
            MemberAtMsg atMsg = new MemberAtMsg();
            atMsg.setPostUuid("11");
            //数据类型：0 帖子、1 评论
            atMsg.setDataType(1);
            atMsg.setReceiveMemberUuid("ruuid");
            atMsg.setSendMemberUuid("suuid");
            atMsg.setTips("");
            asMsgSet.add(atMsg);
        }
        Assert.assertEquals(1, asMsgSet.size());
    }

    @org.junit.Test
    public void test19() {
        List<String> list = Arrays.asList("330000515810475",
                "330000513763921",
                "530000469226511",
                "530000469226511",
                "530000469226511",
                "150000540171106",
                "160000619307294",
                "160000618961265",
                "160000619307294",
                "300000478155282",
                "420000555229075",
                "540000413322137",
                "510000389166984",
                "510000388510327",
                "540000413322137",
                "160000619307294",
                "530000469226511",
                "160000618961265",
                "730000364442860",
                "190000662600383",
                "510000389166984",
                "510000389166984",
                "160000619307294",
                "160000619307294",
                "160000619307294",
                "730000364499114",
                "330000515810475",
                "160000618961265",
                "510000388510327",
                "330000513763921",
                "160000618961265",
                "330000515810475",
                "510000388510327",
                "100000588804408",
                "100000588804408",
                "510000388510327",
                "330000520131876",
                "330000520131876");
        HashSet<String> h = new HashSet(list);
        for (String obj : h) {
            System.out.println(obj);
        }


    }

    @org.junit.Test
    public void test20() {
        HashFunction hf = Hashing.murmur3_128();
        String str = "test";
        String num = Long.toString(hf.newHasher().putString(str, Charsets.UTF_8).hash().asLong());

        System.out.println(num);
        int f = 10;
        int t = 32;
        System.out.println(new BigInteger(num, f).toString(t));
    }

    @org.junit.Test
    public void test21() {
        Integer i = 122222;
        if (i.equals(122222)) {
            System.out.println("equal ");
        } else {
            System.out.println("not equal");
        }
        if (i == 122222) {
            System.out.println("== ");
        } else {
            System.out.println("not ==");
        }

    }

    @org.junit.Test
    public void test22() {
        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("Dolores");
//创建一个空实例
        Optional<String> empty = Optional.ofNullable(null);
//创建一个不允许值为空的空实例
        try {
            empty.orElseThrow(RuntimeException::new);
//            Optional noEmpty = Optional.of(null);

        } catch (Exception e) {
            System.out.println("Exception catched");
        }
    }

    @org.junit.Test
    public void test23() {
        long purchase_date_ms = 1570878012000L;
        String str = "2019-10-12 11:00:12 Etc/GMT";
        Date d = new Date(purchase_date_ms);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        System.out.println(sdf.format(d));
        SimpleDateFormat GTMFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z", java.util.Locale.US);
        GTMFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));  // 设置伦敦时区
        System.out.println(GTMFormat.format(d));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(format.format(new Date()));

        System.out.println(sdf.format(GTMDateUtil.GTMToChina(str)));
    }

    @org.junit.Test
    public void test24() throws ParseException {
//        long purchase_date_ms = 1571887889000L;
//        String str = "2019-10-24 03:31:29 Etc/GMT";
        String str = "2019-10-24 08:07:41 Etc/GMT";
        System.out.println("org: " + str);
//        Date d = new Date(purchase_date_ms);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        SimpleDateFormat GTMFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US);
        GTMFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));
//        GTMFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        Date d = GTMFormat.parse(str);
        System.out.println("local: " + sdf.format(d));
        //        GTMFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));  // 设置伦敦时区
        System.out.println("Etc/GMT: " + GTMFormat.format(d));
        System.out.println("d:" + d);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z", Locale.CHINA);
//        format.setTimeZone(TimeZone.getTimeZone("CST"));
//        System.out.println("UTC Now:"+format.format(new Date()));

        System.out.println("China:" + sdf.format(GTMDateUtil.GTMToChina(str)));
    }

    @org.junit.Test
    public void test25() throws ParseException {
        Date d = new Date();
        System.out.println("now d:" + d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        SimpleDateFormat GTMFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", java.util.Locale.US);
        GTMFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));
//        GTMFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
//        Date d = GTMFormat.parse(str);
        System.out.println("local: " + sdf.format(d));
        //        GTMFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));  // 设置伦敦时区
        String gmstr = GTMFormat.format(d);
        System.out.println("now Etc/GMT: " + GTMFormat.format(d));
        System.out.println("now China:" + sdf.format(GTMDateUtil.GTMToChina(gmstr)));
        System.out.println("from gmstr:" + sdf.format(GTMFormat.parse(gmstr)));

    }

    @org.junit.Test
    public void test26() {
        String purchaseDate = "2019-07-02 09:30:08";
        Long d = Long.parseLong(Pattern.compile("[^0-9]").matcher(purchaseDate).replaceAll("").trim());
        System.out.println(d);
        int re = 0;
        try {
            re = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return;
    }

    @org.junit.Test
    public void test27() {
        long DURATION_MS = 60 * 60 * 24 * 90 * 1000L;
        System.out.println(DURATION_MS);
        InApp result2 = new InApp();
        List<InApp> result = Lists.newArrayList();
        OutData d = new OutData(true, 1, "aa", new JSONArray(result));
        System.out.println(d.getData());
        String str = "{\"leftNum\":0,\"balanceAmount\":1}";
    }

    @org.junit.Test
    public void test28() {
        OutData data = new OutData(false, 1, "xx");
        String str = "{\"code\":0,\"message\":\"\",\"success\":true}";
        Result result = JSON.parseObject(str, Result.class);
        Financial financial = new Financial();
        OutData outData = new OutData(result.isSuccess(), result.getCode(), result.getMessage(), new JSONObject(financial));
        log.info("outData:{}", outData);
        log.info("data:{}", data);
    }

    @org.junit.Test
    public void test29() {
        Financial financial = new Financial();
        String str = "{\"code\":0,\"message\":\"\",\"success\":true}";
        Result result = JSON.parseObject(str, Result.class);
        OutData outData = new OutData(result.isSuccess(), result.getCode(), result.getMessage(), new JSONObject(financial));
        log.info("outData:{}", outData);
        Object data = new Financial();
        ObjectMapper om = new ObjectMapper();
        JsonNode node = null;
        try {
            node = om.readTree(data.toString());
        } catch (JsonProcessingException e) {
            node = null;
        } catch (IOException e) {
            node = null;
        }
        data = node;
        System.out.println(data);
        System.out.println("node:" + JSON.toJSONString(node));
    }

    @org.junit.Test
    public void test30() {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.forEach(item -> System.out.println(item));

    }

    @org.junit.Test
    public void test31() {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put(1, null);
        map.put(2, null);
        System.out.println(map);
    }

    @org.junit.Test
    public void test32() {

        AppleIapResult iapResult = JSON.parseObject("", AppleIapResult.class);
        Integer status = iapResult.getStatus();
    }

    @org.junit.Test
    public void test33() {
        String str = JSON.toJSONString(null);
        System.out.println(str);
    }

    @org.junit.Test
    public void test34() {
        double rate = 1 - ((3 * 1.0) / 31);
        double refundPrice = 18f * rate;

        System.out.println(rate + " :" + refundPrice);
        double f = new BigDecimal(18f * rate).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f);
    }


}

