import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class SimpleDateFormatTest {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ExecutorService poolExecutor = Executors.newFixedThreadPool(10);

    @Test
    public void test() {
        while (true) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String dateString = simpleDateFormat.format(new Date());
                    try {
                        Date parseDate = simpleDateFormat.parse(dateString);
                        String dateString2 = simpleDateFormat.format(parseDate);
                        System.out.println(dateString.equals(dateString2));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Test
    public void test2() {
        while (true) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String starDate = "2020-07-15 11:54:58";
                    String endDate = "2020-07-16 11:54:57";
                    try {
                        Date d = simpleDateFormat.parse(starDate);
                        Date e = simpleDateFormat.parse(endDate);
                        log.info("{},{}",d,e);
//                        System.out.println(d);
                    } catch (ParseException e) {

                    }
                    /*String endDate = "2020-07-25 01:25:15";
                    String cDate = "2020-06-25 01:25:15";
                    int a = DateUtils.compareDays(starDate,endDate);
                    int b = DateUtils.compareDays(cDate,starDate);
                    if(a!=10){
                        System.out.println(starDate +"  "+endDate);
                    }*/
                }
            });
        }
    }
}