package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by admin on 2017/8/25.
 */
public class ThreadTest {
    private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("aaaa");
        final CountDownLatch latch = new CountDownLatch(1);
        final ThreadLocal threadLocal=new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return "xiezhaodong";
            }
        };

        new Thread(new Runnable() {
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object o =  threadLocal.get();
                System.out.println("get from subThread:"+Thread.currentThread()+" value:"+threadLocal.get()); //NULL

            }
        }).start();
//        Thread.currentThread().sleep(1000);
        System.out.println("get from mainThread:"+Thread.currentThread()+" value:"+threadLocal.get());
        latch.countDown();
    }

}
