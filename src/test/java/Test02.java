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
import utill.ListUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
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
       List<String> a = Lists.newArrayList("e0a5d0a6df8340f39c2317e11abd286d",
               "c9036b7433164453bbc14a0dae11b011",
               "0b06f566750849df8503e1d8c1cb6d84",
               "808c52f1d2654f76b2d6ee7a18044101",
               "4c15c410f791434dae2097a71424c588",
               "32e6b81aa4d1472e8a6c39b20420dc6c",
               "c6f3ff15dee64631851824a4ad0597d6",
               "60a7f9f252d04ebe840ff46ae648d896",
               "09831cbd46ed47c6b6a2f002f15a637e",
               "e0358a30664341b59d76f2d1739788f9",
               "5026f07fbe7a4c5f986ae6fb30133343",
               "ac1ffdd4f71844d59b6f91411cd4669d",
               "df748ced675742c9b4b5d8c40e1f32e9",
               "e40410f68fec4e2c91d664229ea1df7a",
               "0c8de25bb0b945f69aa7e6bca9d3f433",
               "be37ca99db0241c5846d0cc5e00a72b9",
               "fa71646a4de943019036b9b24b278c90",
               "8bdd04856efb45f482e13e2c3ee9d4a1",
               "aa19f7ccc670477dafd4431c4d66f060",
               "6ed1993e6c6b40d48b607e65a259de70",
               "0f7fad1498d4459e97b31a176a22f30d",
               "4605ebfb8e14427592e36a02786362a6",
               "43ad9f2ae5ab4b048ddd9308769fe52c",
               "aafc005cf65c4685b1f23584dbfdb97c",
               "18116e13479c49fbace556cf01222349",
               "c451a917758c4a4493fd6a5962e53e19",
               "e24f970d55354a0a82db5a80f30942e7",
               "035f2f3e96e148379a907c2bc8bfe0e6",
               "3d6be463beb143ff8b77a9d8cc6f3a53",
               "28ae99066c094a0f99effa4d0f47ea47",
               "d873c20937f04df094bb69ff770d5bec",
               "78f92a7a6eb3415bbd38b6bd2425c575",
               "cf482dfb544f43a3bb306cd63b747ce3",
               "84bce718f05442639b3426b48986adb9",
               "f6910a4e317a4d249a10d51d59e60b0e",
               "0b04472d66334ca0b907cb045115d0b8",
               "c6cf5584576e4aea92c82d5ddd14fd02",
               "2f629a0fe1bd46c1bb3f03ac1bdfaff3",
               "2c3caa4b2c334709814e94b8365668c6",
               "7f75a154f7ea4707a150568956a60ffe",
               "1612a5dbb2924a6ca87cc76a77a0df72",
               "c57915c9b5d1449588e76fb93abec4e1",
               "f2ff5210a7ed47ebae3e7d96b57a2c5f",
               "8068dddc455948b1883540f85e9d1fcf",
               "5895cb1f6d3d4413b7c909872e15a12e",
               "b572ab4f12b9448997879c89bfc2ca48",
               "96cdb1f2fc6741dfbd8c1f7b02460334",
               "45873d9ee4c1457b804ee9ba20cf6c57",
               "d725e8fbbc7445dd9c30caf26997d583",
               "cb85d795745e4ab3ace8ca3b5e4042c3",
               "1d241daed5f54ba8af97936a8fb04bea"
               );
        List<String> b = Lists.newArrayList("df748ced675742c9b4b5d8c40e1f32e9",
                "aa19f7ccc670477dafd4431c4d66f060",
                "60a7f9f252d04ebe840ff46ae648d896",
                "bb3bbce48cae464d8e1ef8bc1c2e4700",
                "43c01ef47bc44eb2aa8c01a5b9f75de5"
                );
        log.info("{}", ListUtils.subList(b,a));
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
        Date current = DateUtils.stringToDate("2022-03-01 23:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date purchaseDay = DateUtils.stringToDate("2022-02-28 12:22:32",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date vipEndDate = DateUtils.stringToDate("2022-03-01 23:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date d1 = getDayNum(3,true,purchaseDay,vipEndDate,current);
        Date d2 = getDayNum(3,false,purchaseDay,vipEndDate,current);
        Assert.assertEquals(DateUtils.stringToDate("2022-06-01 23:59:59"),d1);
        Assert.assertEquals(DateUtils.stringToDate("2022-06-01 23:59:59"),d2);
        //临界 diff < 3 则修
        Date current_2 = DateUtils.stringToDate("2022-05-03 10:00:00",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date purchaseDay_2 = DateUtils.stringToDate("2022-04-30 13:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date vipEndDate_2 = DateUtils.stringToDate("2021-02-27 23:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date d1_2 = getDayNum(1,true,purchaseDay_2,vipEndDate_2,current_2);
        Date d2_2 = getDayNum(1,false,purchaseDay_2,vipEndDate_2,current_2);
        Assert.assertEquals(DateUtils.stringToDate("2022-06-03 23:59:59"),d1_2);
        Assert.assertEquals(DateUtils.stringToDate("2022-06-03 23:59:59"),d2_2);

    }

    @Test
    public void test100() {
        Date current = DateUtils.stringToDate("2022-05-05 12:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date vipEndDate = DateUtils.stringToDate("2026-06-17 23:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date purchaseDay = DateUtils.stringToDate("2022-05-05 12:22:32",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date d1 = getDayNum2(1,true,purchaseDay,vipEndDate,current);
        Date d2 = getDayNum2(2,false,purchaseDay,vipEndDate,current);
        Assert.assertEquals(DateUtils.stringToDate("2026-07-18 23:59:59"),d1);
//        Assert.assertEquals(DateUtils.stringToDate("2022-05-01 23:59:59"),d2);

        Date current_2 = DateUtils.stringToDate("2022-05-03 10:00:00",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date vipEndDate_2 = DateUtils.stringToDate("2021-02-27 23:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date purchaseDay_2 = DateUtils.stringToDate("2022-03-02 13:59:59",DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date d1_2 = getDayNum2(1,true,purchaseDay_2,vipEndDate_2,current_2);
        Date d2_2 = getDayNum2(1,false,purchaseDay_2,vipEndDate_2,current_2);
        Assert.assertEquals(DateUtils.stringToDate("2022-06-03 23:59:59"),d1_2);
        Assert.assertEquals(DateUtils.stringToDate("2022-06-03 23:59:59"),d2_2);

    }

    private Date getDayNum2(int total,boolean amend,Date purchaseDay,Date vipEndDate,Date current){
        if(vipEndDate == null || vipEndDate.before(current)){
            vipEndDate = current;
        }
        Date vipDeadLineDay = DateUtils.addMonth(vipEndDate,total);
        Date result = vipDeadLineDay;
        if(amend){
            /**
             * 若需修正vip时间的逻辑
             * 修正规则如下：
             * 则按照购买时间所在月份顺序往后累计n个自然月的天数
             */
            LocalDate localDate = purchaseDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate();
            int days = localDate.lengthOfMonth();
            for (int i = 1; i < total; i++) {
                days = days + localDate.plusMonths(i).lengthOfMonth();
            }
            result = DateUtils.addDay(vipEndDate,days);
        }
        return DateUtils.getDeadLine(result);
    }


    private Date getDayNum(int total,boolean amend,Date purchaseDay,Date vipEndDate,Date current){
        if(vipEndDate == null || vipEndDate.before(current)){
            vipEndDate = current;
        }
        Date vipDeadLineDay = DateUtils.addMonth(vipEndDate,total);
        Date result = vipDeadLineDay;
        if(amend){
            /**
             * 若需修正vip时间的逻辑
             * 修正规则如下：
             * 购买时间若为某个月的最后一天，则当次发放的有会员有效期需要发放至目标自然月的最后一天且若调整的天数 > 3天则不调，
             * （如，支付时间为2022年2月28日 12:00:00，发放会员为【1个自然月】，
             * 由于2月份为28天小于3月份31天，则当次发放的会员有效期应该发放至2022年3月31日而非2022年3月28日
             */
            Date endDayOfMonth = DateUtils.getMonthEndDay(purchaseDay);
            //若为月份的最后一天
            if(purchaseDay.compareTo(endDayOfMonth)== 0){
                //设置vip为到期月份的最后一天
                Date amendDay = DateUtils.getMonthEndDay(vipDeadLineDay);
                if(DateUtils.compareDays(vipDeadLineDay,amendDay) <= 3){
                    result = amendDay;
                }
            }
        }
        return DateUtils.getDeadLine(result);
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
