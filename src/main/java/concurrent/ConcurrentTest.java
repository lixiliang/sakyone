package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentTest {

    private synchronized static void dosth(){
        try {
            String thread = Thread.currentThread().getName();
            System.out.println("dosth begin."+thread);
            TimeUnit.SECONDS.sleep(2);
            System.out.println("dosth end."+thread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       ExecutorService service =  Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.submit(() -> dosth());
        }
        service.shutdown();
    }
}
