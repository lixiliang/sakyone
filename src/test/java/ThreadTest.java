import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lixiliang
 * @describe
 * @date 2021/7/5
 */
@Slf4j
public class ThreadTest {
    static List<String> list = Arrays.asList("1", "2");
    ExecutorService executorService = Executors.newFixedThreadPool(20);
    static String str = "";
    private  static int MY_INT = 0;
    @Test
    public void t01() throws Exception {
        for (int i = 0; i < 4; i++) {
            executorService.submit(() -> {
                int local = MY_INT;
                while (local <5) {
//                     log.info("{},main str:{}");
                    if (local !=MY_INT) {
                        log.info("listened change MY_INT:{}", MY_INT);
                        local = MY_INT;
                    }
//                    synchronized (this){};
                    log.info("");
                   /* try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }

            });
        }
        AtomicInteger ai = new AtomicInteger(0);
        executorService.submit(() -> {
            int local = MY_INT;
            int i = ai.getAndIncrement();
            while (MY_INT <5) {
                MY_INT = ++local;
//                log.info("change list:{}",list);
                log.info("update MY_INT:{}", MY_INT);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        System.out.println(System.in.read());
    }
}
