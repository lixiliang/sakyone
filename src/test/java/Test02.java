import async.Transaction;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import entity.RankConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utill.DateUtils;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class Test02 {


    @Test
    public void test3() throws ParseException {
        Date vipEndTime = DateUtils.stringToDate("2021-11-30 23:59:59");
        Date entrustTime = DateUtils.stringToDate("2020-11-28 06:38:07");
        Date result = calculateRenewTime(vipEndTime,entrustTime);
        System.out.println(DateUtils.dateToString(result));
        Date v2 = DateUtils.stringToDate("2021-11-26 23:59:59");
        result = calculateRenewTime(v2,entrustTime);
        System.out.println(DateUtils.dateToString(result));

        Date v3 = DateUtils.stringToDate("2021-11-28 23:59:59");
        result = calculateRenewTime(v3,entrustTime);
        System.out.println(DateUtils.dateToString(result));
    }

    public Date calculateRenewTime(Date vipEndTime, Date entrustTime) {
        Date now = new Date();
        if(vipEndTime==null){
            return now;
        }
        String daySeg = DateUtils.dateToString(DateUtils.addDay(vipEndTime, -1), DateUtils.YYYY_MM_DD);
        String timeSeg = DateUtils.getTimeShort(entrustTime);
        Date date = DateUtils.stringToDate(daySeg + " " + timeSeg);
        Date renewTime = date;

        if (renewTime.before(now)) {
            //若应该续费的时间早于当前时间，可能由于其它原因错过续费时间，则需立刻进行续费
            renewTime = now;
        }
        return renewTime;
    }


    @Test
    public void test4() throws ParseException, JsonProcessingException {
        String m = "85268426719";
//        System.out.println(m.equals(null));
        JSONObject obj = new JSONObject();
        OutData od = new OutData(false, 11, "msg", obj);
        System.out.println(JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(JSON.toJSONStringWithDateFormat(od, "yyyy-MM-dd HH:mm:ss"));

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(od);
        System.out.println(userJson);
    }

    @Test
    public void test5() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i+"####"+System.currentTimeMillis() );
        }

    }

    @Test
    public void test6() {
        List<CompletableFuture> futures = Lists.newArrayList();
        Map<String, Integer> starValueMap = Maps.newHashMapWithExpectedSize(11);
        futures.add(CompletableFuture.supplyAsync(() -> {
                    Map<String, Integer> result = new HashMap();
                    log.info("aa");
                    result.put("normal", 1);
                    int i = 1 / 0;
                    return result;
                }).exceptionally((ex) -> {
                    log.error("error,msg:{}", ex.getMessage());
                    Map<String, Integer> result = new HashMap();
                    result.put("error", 2);
                    return result;
                }).thenAccept(((rst) -> {
                    log.info("accept.{}",rst);
                    starValueMap.putAll(rst);
                    throw new RuntimeException("runtime");
                })).exceptionally((ex) -> {
                    log.error("err2 ,{}", ex.getMessage());
                    return null;
                })
        );
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        log.info("{}", starValueMap);
    }

    @Test
    public void test7() {
        String config = "[{\"rank\":\"star_hour\",\"name\":\"小时榜\",\"type\":\"star\",\"isHide\":0},{\"rank\":\"star_day\",\"name\":\"日榜\",\"type\":\"star\",\"isHide\":0},{\"rank\":\"star_week\",\"name\":\"周榜\",\"type\":\"star\",\"isHide\":0},{\"rank\":\"star_month\",\"name\":\"月榜\",\"type\":\"star\",\"isHide\":0},{\"rank\":\"pk_day\",\"name\":\"PK日榜\",\"type\":\"pk\",\"isHide\":0},{\"rank\":\"pk_week\",\"name\":\"PK周榜\",\"type\":\"pk\",\"isHide\":0}]";
        List<RankConfig> rankConfigs = JSON.parseObject(config, new TypeReference<List<RankConfig>>() {
        });
        rankConfigs.stream().forEach(System.out::println);
    }

    @Test
    public void test8() {
        String week = "2020W13";
        int wk = Integer.valueOf(week.substring(week.indexOf("W") + 1));
        Date[] dates = DateUtils.getBeginAndEndDateOfWeek(week);
        String begin = DateUtils.dateToSimpleStringYYYYMMDD(dates[0]);
        String end = DateUtils.dateToSimpleStringYYYYMMDD(dates[1]);
        log.info("wk:{},begin:{},end:{}", wk, begin, end);
    }

    @Test
    public void test9() {
//        Date d = DateUtils.stringToDate("2020-01-31", "yyyy-MM-dd");
        String e = DateUtils.getWeek(new Date());
        log.info("{}", e);
    }


    @Test
    public void test10() {
        Transaction t = new Transaction();
        Map<String,String> result = Maps.newConcurrentMap();
        CompletableFuture<String> aFuture = new CompletableFuture<>();
        CompletableFuture<String> bFuture = new CompletableFuture<>();
        CompletableFuture<String> cFuture= new CompletableFuture<>();
        t.setAFuture(aFuture);
        t.setBFuture(bFuture);
        t.setCFuture(cFuture);

        aFuture.exceptionally(e -> null).thenAccept((rst)->{if(rst != null) result.put("a",rst);});
        bFuture.exceptionally(e -> null).thenAccept((rst)->{if(rst != null) result.put("b",rst);});
        cFuture.exceptionally(e -> null).thenAccept((rst)->{if(rst != null) result.put("c",rst);});

        Thread t1 = new Thread(() -> a(t));
        Thread t2 = new Thread(() -> b(t));
        Thread t3 = new Thread(() -> c(t));
        t1.start();
        t2.start();
        t3.start();
        CompletableFuture[] futures = new CompletableFuture[]{aFuture,bFuture,cFuture};
        CompletableFuture<Void> all = CompletableFuture.allOf(futures);
        all.join();

        log.info("all end result:{}",result);

    }

    @Test
    public void test11() {
        Transaction t = new Transaction();
        List<String> result = Collections.synchronizedList(new ArrayList<>());
        CompletableFuture<String> aFuture = new CompletableFuture<>();
        CompletableFuture<String> bFuture = new CompletableFuture<>();
        CompletableFuture<String> cFuture= new CompletableFuture<>();
        t.setAFuture(aFuture);
        t.setBFuture(bFuture);
        t.setCFuture(cFuture);

        aFuture.exceptionally(e -> null).thenAccept((rst)->{if(rst != null) result.add(rst);});
        bFuture.exceptionally(e -> null).thenAccept((rst)->{if(rst != null) result.add(rst);});
        cFuture.exceptionally(e -> null).thenAccept((rst)->{if(rst != null) result.add(rst);});

        Thread t1 = new Thread(() -> a(t));
        Thread t2 = new Thread(() -> b(t));
        Thread t3 = new Thread(() -> c(t));
        t1.start();
        t2.start();
        t3.start();
        CompletableFuture[] futures = new CompletableFuture[]{aFuture,bFuture,cFuture};
        CompletableFuture.allOf(aFuture,bFuture,cFuture).join();

        log.info("all end result:{}",result);

    }

    public void a(Transaction t) {
        Random r = new Random(1);
        int num = r.nextInt(10);
        try {
            log.info("sleep:{}",num);
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.getAFuture().complete("a");
        log.info("a finish");
    }
    public void b(Transaction t){
        Random r = new Random(1);
        int num = r.nextInt(10);
        try {
            log.info("sleep:{}",num);
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.getBFuture().complete("b");
        log.info("b finish");
    }
    public void c(Transaction t){
        Random r = new Random(1);
        int num = r.nextInt(10);
        try {
            log.info("sleep:{}",num);
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.getCFuture().complete("c");
        log.info("c finish");
    }
    @Test
    public void test12(){


    }
    @Test
    public void test13(){
        Date dataTime = new Date();
        Date a = DateUtils.stringToDate("2020-07-15 11:54:58");
        Date b = DateUtils.stringToDate("2020-06-25 01:25:15");
        int days = DateUtils.compareDays(a,b);
        log.info("diff day:{}",days);
    }
    @Test
    public void test14(){
        System.out.println("a".equalsIgnoreCase(null));
    }

    @Test
    public void test15(){
        String a = "4";
        String b = "payApiQ";
        long i = ((a+b).hashCode() & 0xffff) % (31 + 1);
        System.out.println(i);
    }

    @Test
    public void test16(){
        long t = 1597210971883L;
        Date d = new Date(t);
        System.out.println(DateUtils.dateToString(d));

        System.out.println(System.currentTimeMillis());
    }
    @Test
    public void test17(){
        log.info(DateUtils.getWeek(new Date()));
        Integer t = 12;
        Integer f = 12;
        Date d = new Date(t);
        Assert.assertTrue(t==f);
        Assert.assertTrue(t.equals(f));

    }
    @Test
    public void test18(){
        int target = 0;
        int randomNumber = (int)(Math.random() * (target+1));
        while (randomNumber < target){

            randomNumber = (int)(Math.random() * (target+1));
        }
        log.info("rand:{}",randomNumber);
    }

    @Test
    public void test19(){
        List<String> nums = Arrays.asList("1","2","3");
        StringJoiner sjSactionUuid = new StringJoiner("','","('", "')");
        for (String sactionUuid:nums) {
            sjSactionUuid.add(sactionUuid);
        }
        String str = nums.stream().collect(Collectors.joining("','","('","')"));
      log.info("{}",sjSactionUuid.toString());
      log.info("{}",str);
    }
    @Test
    public void testPhone(){
        String p="13811348174";
        log.info("{}",isCellPhoneNo(p));
    }

    public static boolean isCellPhoneNo(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            return false;
        }
        if (telephone.length() != 11) {
            return false;
        }
        if(isHKMobile(telephone)){
            return true;
        }
        Pattern pattern = Pattern.compile("^(1)\\d{10}$");
        Matcher matcher = pattern.matcher(telephone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    public static boolean isHKMobile(String mobile){
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        if(mobile.startsWith("852")){
            return true;
        }
        return false;
    }
    @Test
    public void t1(){
        List<String> vodUuids = Arrays.asList("a","b","c","d");
        List<String> sub = vodUuids.stream().skip(11).limit(12).collect(Collectors.toList());
        System.out.println(sub);
      /* String uuidString = vodUuids.stream().collect(Collectors.joining("','","'","'"));
        System.out.println(uuidString);*/
    }
    @Test
    public void t2(){
        List<String> vodUuids = Arrays.asList("a","b","c");
        String vodStr = vodUuids.stream().collect(Collectors.joining("','","('", "')"));
        log.info("{}",vodStr);

        try {
            throw new RuntimeException();
        }finally {
            System.out.println("finall");
        }
    }
    @Test
    public void calculateRenewTime() {
        Date vipEndTime = DateUtils.stringToDate("2020-11-01 00:00:00");
        Date entrustTime = DateUtils.stringToDate("2020-12-01 17:20:00");

        String daySeg = DateUtils.dateToString(DateUtils.addDay(vipEndTime, -1), DateUtils.YYYY_MM_DD);
        String timeSeg = DateUtils.getTimeShort(entrustTime);
        Date date = DateUtils.stringToDate(daySeg + " " + timeSeg);
        Date renewTime = DateUtils.addHour(date, -24);
        Date now = new Date();
        if (renewTime.before(now)) {
            //若应该续费的时间早于当前时间，则需立刻进行续费
//            renewTime = DateUtils.stringToDate(DateUtils.nowOfStr(DateUtils.YYYY_MM_DD) + " " + timeSeg);
            renewTime = new Date();
        }
        System.out.println(renewTime);
//        renewTime = DateUtils.addMinute(renewTime,-1);
        //如果当前时间已经到了或者过了续期时间，则进行续费
        if (renewTime.before(now)) {
            System.out.println("renew..");
        }
    }
    @Test
    public void x1(){
        if (true && ("1".equals(1) || "2".equals("2"))
                && ("3".equals("3") || "4".equals("mch_id"))){
            log.info("1111111");
        }
//        log.info("{}", Integer.valueOf(h));
    }
    public Integer x2(){
        return null;
    }
}
