package spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 2017/11/20.
 */
public class Bootstrap {
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/spring/applicationContext.xml");
            context.start();

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("系统异常退出"+e);
        }
    }
}
