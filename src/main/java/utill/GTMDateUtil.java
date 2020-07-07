package utill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GTMDateUtil {

    /**
     * GTM转中国时间
     *
     * @param GTMDate
     * @return
     */
    public static Date GTMToChina(String GTMDate) {
        if(null == GTMDate) return null;

        Date CNDate;

        SimpleDateFormat GTMFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US );
        GTMFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));  // 设置伦敦时区

        SimpleDateFormat CNFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA );
        CNFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置中国时区

        try {
            CNDate = CNFormat.parse(CNFormat.format(GTMFormat.parse(GTMDate)));
        } catch (ParseException e) {
            return null;
        }

        return CNDate;
    }

    public static String GTMStr2ChinaStr(String GTMDateStr) {
        SimpleDateFormat CNFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA );
        CNFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置中国时区
        try {
            return CNFormat.format(GTMToChina(GTMDateStr));
        } catch (Exception e) {
            return null;
        }
    }

    public static int compareDays(Date startTime, Date endTime) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(startTime);
        calendar2.setTime(endTime);
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        if(year1 > year2) {
            int tempyear = year1;
            int tempday = day1;
            day1 = day2;
            day2 = tempday;
            year1 = year2;
            year2 = tempyear;
        }
        if (year1 == year2) {
            return day2 - day1;
        } else {
            int DayCount = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    DayCount += 366;
                }else {
                    DayCount += 365;
                }
            }
            return DayCount+(day2-day1);
        }
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat CNFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA );
        Date d2 = CNFormat.parse("2020-03-09 00:00:00");
        Date d1 =CNFormat.parse("2020-03-08 23:59:59");

        System.out.println(compareDays(d1,d2));
    }
}
