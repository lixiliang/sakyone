package date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lixiliang
 * @describe
 * @date 2021/9/1
 */
public class DateTest {
    public static Date addDay(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.DAY_OF_YEAR, num);
        return c.getTime();
    }
    public static Date addDay2(Date curDate, int num) {
        if(null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+num);
        return c.getTime();
    }
    public static Date addMonth(Date curDate, int num) {
        if(null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH)+num);
        return c.getTime();
    }
    public static Date addMonth2(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.MONTH, num);
        return c.getTime();
    }

    public static void main(String[] args) {
        Date now = new Date();
        for (int i = 1; i < 1325; i++) {
            long a = addDay(now,i).getTime();
            long b = addDay2(now,i).getTime();
            if(a!=b){
                System.out.println("error,"+i);
            }

        }

    }
}
