import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static ExecutorService pool = Executors.newFixedThreadPool(10, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            AtomicInteger mCount = new AtomicInteger(1);
            return new Thread(r, "asycn-thread" + mCount.getAndIncrement());
        }
    });
//    static DBTest dbTest = new DBTest();

    public static void main(String[] args) throws InterruptedException {
        pool.submit((Runnable) () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("async task doing..");
            throw new RuntimeException();
        });
        new Thread(() -> {
            System.out.println("my-thread doing..");
            throw new RuntimeException();
        },"my-thread").start();
//        TimeUnit.SECONDS.sleep(3);
        System.out.println("main doing..");

       /* try {

            System.out.println(doth());
        } catch (Exception e) {
            System.out.println("xxxxxxx"+e);
        }*/
    }

    public static int doth() {
        int re = 1;
        try {
            re = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return re;
    }

    private static void dosth() throws InterruptedException, IOException {
        long i = 0;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(input);
//        System.in.read();
        while (!Thread.currentThread().isInterrupted()) {
//            dbTest.select(3);
//            i++;
            System.out.println(Thread.currentThread().getName() + " hello" + i);
            Thread.sleep(1000);
        }
    }

    public static void doing(Parent parent) {
        parent.doing();
    }
}


class Parent {
    public void doing() {
        System.out.println("parent doing");
    }
}

class Son extends Parent {
    public void doing() {
        System.out.println("Son doing");
    }
}
