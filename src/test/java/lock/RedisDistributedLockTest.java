package lock;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RedisDistributedLockTest {
    Jedis jedis = new Jedis("redis.local",6379);
    ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    @Test
    public void acquire() throws InterruptedException {
            RedisDistributedLock lock = new RedisDistributedLock("test_lock",1000,1000);
            RedisDistributedLock lock2 = new RedisDistributedLock("test_lock",1000,1000);
        boolean isSuccess;
        try {
            isSuccess = lock.acquire(jedis);
            Assert.assertTrue(isSuccess);
            service.scheduleAtFixedRate((() -> {
                try {
                    System.out.println("renew");
                    lock.renew(jedis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }),10,1000,TimeUnit.MILLISECONDS);
            TimeUnit.SECONDS.sleep(5);
            isSuccess = lock2.acquire(jedis);
            Assert.assertFalse(isSuccess);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            service.shutdownNow();
            lock.release(jedis);
        }
        TimeUnit.SECONDS.sleep(15);

    }

    @Test
    public void renew() {
    }

    @Test
    public void release() {
    }
}