import async.Transaction;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import entity.RankConfig;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utill.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Test01 {

    @Test
    public void test1() throws IOException {
        log.info("主线程开始...");
        try {
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                log.info("async线程执行");
                try {
                    TimeUnit.SECONDS.sleep(3);
//                    int i = 1/0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 123;
            }).thenAccept((t)->{
                log.info("anoth thread 再执行"+t);
                throw new NumberFormatException("sss");
            }).exceptionally(throwable -> {
                log.error("error thread", throwable);
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("主线程结束...");
        System.in.read();
    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date vipEndDate = sdf.parse("2020-01-31");
        Date d2 = sdf.parse("2020-02-29");
        int days = DateUtils.compareDays(vipEndDate, d2);
        System.out.println(days);
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
        String week = "2020W01";
//        2019-12-30 15:21:18
//        2020-01-05 15:21:18
        Date[] d = DateUtils.getBeginAndEndDateOfWeek(week);
        Date begin = new Date();
        Date end = new Date();

        System.out.println(DateUtils.dateToString(d[0]));
        System.out.println(DateUtils.dateToString(d[1]));
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
      Map<String ,Object> map = Maps.newHashMap();
      map.put("a",null);
        map.put("b",1);
        map.put("b",null);
      log.info("{}",map);
    }
}
