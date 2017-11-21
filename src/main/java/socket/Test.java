package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by admin on 2017/11/17.
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        System.out.println("rrrrrrrr");
                        Thread.sleep(1000*60*5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        Thread.sleep(1000*60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.setName("t2");
        t2.start();
//        Thread.sleep(1000*30*10);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.read();
        System.out.println("end");
    }
}
