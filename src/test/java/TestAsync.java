import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestAsync {

    @Test
    public void test1 () {
        List<String> testList = Lists.newArrayList();
        testList.add("cf1");
        testList.add("cf2");
        long start = System.currentTimeMillis();
        CompletableFuture[] cfArr = testList.stream().
                map(t -> CompletableFuture
                        .supplyAsync(() -> pause(t))
                        .whenComplete((result, th) -> {
                            System.out.println("hello" + result);
                        })).toArray(CompletableFuture[]::new);
        // 开始等待所有任务执行完成
        System.out.println("start block");
        CompletableFuture.allOf(cfArr).join();
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test2 () {
        List<String> testList = Lists.newArrayList();
        testList.add("cf1");
//        testList.add("cf2");
        long start = System.currentTimeMillis();
        try {
            CompletableFuture[] cfArr = testList.stream().
                    map(t -> CompletableFuture
                            .supplyAsync(() -> runException())
                            .exceptionally( (ex) ->{
                                log.error("error,",ex);
                                return null;
                            })
                            .whenComplete((result, th) -> {
                                System.out.println("hello" + result);
                            })).toArray(CompletableFuture[]::new);
            // 开始等待所有任务执行完成
            System.out.println("start block");
            CompletableFuture.allOf(cfArr).join();
            System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            System.out.println("try...");
        }
    }
    public static String runException(){
        throw new RuntimeException("error");
    }
    public static String pause (String name) {
        try {
            Random r = new Random(1);
            int num = r.nextInt(10);
            TimeUnit.SECONDS.sleep(num);
            log.info("finish");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

}
