import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/9/13.
 */
public class Test {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Map<String,String> map = new ConcurrentHashMap<String,String>();
        String o1 = map.putIfAbsent("key1","v1");
        String o2 = map.putIfAbsent("key1","v2");
        System.out.println("o1"+o1);
        System.out.println("o2"+o2);
//        map.remove(o2);
        System.out.println(map.get("key1"));

        List<Token> tokens = Lists.newArrayList();
        Token t1 = new Token("a");
        Token t2 = new Token("b");
        tokens.add(t1);
        tokens.add(t2);
        tokens.add(Token.MULTI);
        tokens.add(new Token("f"));
        String top= topic(tokens);
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



    public static void test1()
    {
        String IP = null;
        String host = null;

        try
        {
            InetAddress ia = InetAddress.getLocalHost();
            host = ia.getHostName();//获取计算机主机名
            IP= ia.getHostAddress();//获取计算机IP
        }
        catch(UnknownHostException e)
        {
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
        }finally {
            System.out.println(IFCONFIG);
        }
    }
    @org.junit.Test
    public void testRound(){
        double i = 2.3000000000000003d;
        System.out.println(Math.round(i*100)*0.01d);
        String result = String.format("%.2f", i);
        System.out.println(result);
    }
    @org.junit.Test
    public void test02(){
        Entity e1 = new Entity("a1","t1");
        Entity e2 = new Entity("a2","t2");
        Entity e3 = new Entity("a3","t3");
        List<Entity> list = Arrays.asList(e1,e2,e3);
        List<Entity> list2 = list.stream().filter((e) -> !e.getName().equals("a1") && !e.getText().equals("t2")).collect(Collectors.toList());
        System.out.println(list2);
    }
    @org.junit.Test
    public void test03(){
        String str = "select count(1) count from coupon_weixin cw left join base_info b on cw.base_info_id = b.id WHERE cw.del_flag = ? ; # Time: 190104 11:49:03";
        int index = str.indexOf("# Time:");
        System.out.println(index);
        if(index > 0 ){
            str = str.substring(0,index);
            System.out.println(str);
        }
    }
    @org.junit.Test
    public void test04(){
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
        List<SlowSql> list1 = Arrays.asList(slowSql,slowSql1);
        List<SlowSql> list2 = Lists.newArrayList();
        for (SlowSql str:list1){
            if(!list2.contains(str)){
                list2.add(str);
            }
        }
        System.out.println(list2.size());
    }
    @org.junit.Test
    public void test05(){
        String sql = "select count(1) count from (select car.branch_id branchId,count(1) chargeTime from customer_account_record car left join customer_member cm on car.member_id=cm.id where cm.shop_id=8015 and car.event_id in( select id from customer_event where event_en_name in( 'pos_recharge', 'subscription_recharge', 'wxapp_recharge', 'alipay_recharge' ) ) and car.create_time>= '2019-01-16' and car.create_time <'2019-01-17' GROUP BY car.branch_id  ) t;";

        String wsql = ParameterizedOutputVisitorUtils.parameterize(sql,JdbcConstants.MYSQL);
        System.out.println(wsql);
    }
    @org.junit.Test
    public void test06(){
        String sql = "DELETE FROM cy_campaign WHERE id IN ( 3904144 )";

        if(sql.equalsIgnoreCase("select 1;")
                        || sql.equalsIgnoreCase("select user();")
                        || sql.toLowerCase().startsWith("select get_lock")
                        || ( !sql.toLowerCase().startsWith("select")
                            && !sql.toLowerCase().startsWith("update")
                            && !sql.toLowerCase().startsWith("insert")
        )
                        ){
            System.out.println("ignore sql:"+sql);
        }

    }
    @org.junit.Test
    public void  test07(){
        String sql = "SELECT COUNT(cr.id) count, SUM(ABS(cr.amount)) total, branch_id FROM customer_bonus_record cr LEFT JOIN customer_event ce ON ce.id = cr.event_id LEFT JOIN customer_member cm ON cr.member_id = cm.id WHERE 1= ? and cm.shop_id= ? and cr.branch_id= ? and ce.event_en_name in ( ? ) and cr.create_time >= ? and cr.create_time <= ? GROUP BY cr.branch_id; ";
        int lastIndex = sql.lastIndexOf(";");
        if(lastIndex > 0){
            sql = sql.substring(0,lastIndex).trim();
        }
        System.out.println(sql);
    }
    @org.junit.Test
    public void test08(){
        String str = " ";
        System.out.println(StringUtils.isBlank(str));
        Map<String, String> paraMap = new TreeMap<String, String>();
        paraMap.put("a2","a2");
        paraMap.put("a","a1");
        paraMap.put("d","d1");
        paraMap.put("c","c1");
        for (String key:paraMap.keySet()){
            System.out.println("key:"+key +" value:"+paraMap.get(key));
        }
    }
    @org.junit.Test
    public void test09(){
        String url = "/10549840601068216320";
        String url2 = "/cardcc/101012345678901234567111/";
        String url3 = "/cardcc/101012345678901234567111cc/abc";
//        String serialUrlRegex  = "(.+/)([\\d]{20,})";
        String serialUrlRegex  = "(.+/)(10[\\d]{18,})(/*)(.*)";
        Pattern p = Pattern.compile(serialUrlRegex);
        Matcher matcher = p.matcher(url);
        if (matcher.matches()) {
            System.out.println("url:"+matcher.group(2));
        }
        Matcher matcher2 = p.matcher(url2);
        if (matcher2.matches()) {
            System.out.println("url2:"+matcher2.group(2));
        }
        Matcher matcher3 = p.matcher(url3);
        if (matcher3.matches()) {
            System.out.println("url3:"+matcher3.group(2));
        }
    }
    @org.junit.Test
    public void test10(){
        System.out.println(System.currentTimeMillis());
    }


}
