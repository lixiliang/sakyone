package utill;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static final long DAY = 24 * 60 * 60 * 1000;
    public static final long HOUR = 60 * 60 * 1000;
    public static final long MINUTE = 60 * 60 * 1000;

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";


    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static String DEADLINE_STR = " 23:59:59";

    private static SimpleDateFormat sf = null;

    public static String dateToSimpleStringYYYYMMDD(Date time) {
        return dateToString(time, YYYYMMDD);
    }

    public static Date getDeadLine(Date time) {
        return DateUtils.stringToDate(dateToString(time, YYYY_MM_DD) + DEADLINE_STR);
    }


    public static String dateToSimpleString(Date time) {
        if (null == time) {
            return null;
        }
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String string = formatter.format(time);

        return string;
    }

    public static String dateToString(Date time) {
        if (null == time) {
            return null;
        }
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = formatter.format(time);

        return string;
    }
    public static Long timeFor8BitNum(Date date) {
        return Long.parseLong(dateToString(date, YYYYMMDD));
    }
    public static Date num8BitToDate(Long num8Bit) {
        return stringToDate(num8Bit.toString(),YYYYMMDD);
    }
    public static Date num14BitToDate(Long num14Bit) {
        return stringToDate(num14Bit.toString(),YYYYMMDDHHMMSS);
    }
    public static Long timeFor14BitNum(Date date) {
        return Long.parseLong(dateToString(date, YYYYMMDDHHMMSS));
    }
    public static Long nowFor8BitNum() {
        return Long.parseLong(dateToString(new Date(), YYYYMMDD));
    }

    public static Long nowFor14BitNum() {
        return Long.parseLong(dateToString(new Date(), YYYYMMDDHHMMSS));
    }

    public static String dateToString(Date time, String format) {
        if (null == time) {
            return null;
        }
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(format);
        String string = formatter.format(time);
        return string;
    }

    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);

    }

    public static String getDateToString(long time, String format) {
        Date d = new Date(time);
        sf = new SimpleDateFormat(format);
        return sf.format(d);

    }

    /**
     * 将当前传入的时间 基础上 增加 num个月份
     *
     * @param curDate
     * @param num
     * @return
     * @author feifei
     */
    public static Date addMonth(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.MONTH, num);
        return c.getTime();
    }

    /**
     * 将当前传入的时间 基础上 增加 num个星期
     *
     * @param curDate
     * @param num
     * @return
     * @author feifei
     */
    public static Date addWeek(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.WEEK_OF_YEAR, num);
        return c.getTime();
    }

    /**
     * 将当前传入的时间 基础上 增加 num天
     *
     * @param curDate
     * @param num
     * @return
     * @author feifei
     */
    public static Date addDay(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.DAY_OF_YEAR, num);
        return c.getTime();
    }

    public static Date addSecond(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.SECOND, num);
        return c.getTime();
    }

    public static Date addHour(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.HOUR_OF_DAY, num);
        return c.getTime();
    }

    /**
     * 将当前传入的时间 基础上 增加 num 秒
     *
     * @param curDate
     * @param num
     * @return
     * @author feifei
     */
    public static Date addMinute(Date curDate, int num) {
        if (null == curDate) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.MINUTE, num);
        return c.getTime();
    }

    public static Date stringToDate(String lotteryDeadline, String format) {
        if (StringUtils.isBlank(lotteryDeadline)) {
            return null;
        }
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(format);
        Date date;
        try {
            date = formatter.parse(lotteryDeadline);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }

    public static Date stringToDate(String lotteryDeadline) {
        if (StringUtils.isBlank(lotteryDeadline)) {
            return null;
        }
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = formatter.parse(lotteryDeadline);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }

    /**
     * @param @param date1
     * @param @param date2 设定文件
     * @return void 返回类型
     * @throws
     * @Title: compareDays
     * @Description: (获取相差天数)
     * @author zzj
     */
    public static int compareDays(Date startTime, Date endTime) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(startTime);
        calendar2.setTime(endTime);
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        if (year1 > year2) {
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
                } else {
                    DayCount += 365;
                }
            }
            return DayCount + (day2 - day1);
        }
    }

    /**
     * @param @param dBegin
     * @param @param dEnd
     * @throws
     * @Title: getDistanceDates
     * @Description: (获取两个日期之间的日期) 递增
     * @author zzj
     */
    public static List<String> getDistanceDatesAsc(String format, Date dBegin, Date dEnd) {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sd = new SimpleDateFormat(format);
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        while (dEnd.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }

    /**
     * 返回周对应的开始日期和结束日期
     *
     * @param week 格式 yyyyWww 7位 如 2020W01 代表2020年第一周
     * @return
     */
    public static Date[] getBeginAndEndDateOfWeek(String week) {
        Date begin;
        Date end;
        try {
            int year = Integer.valueOf(week.substring(0, 4));
            int weekNo = Integer.valueOf(week.substring(5));
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.WEEK_OF_YEAR, weekNo);
            begin = cal.getTime();
            cal.add(Calendar.DAY_OF_WEEK, 6);//周日
            end = cal.getTime();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid format, right format: yyyyWww");
        }
        return new Date[]{begin, end};
    }

    /**
     * 返回日期所在的周格式 7位 如 2020W01 代表2020年第一周
     * yyyyWww
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        DateResultInfo ds = getWeekInt(date);
        return ds.getYear().toString() + "W" + String.format("%02d", ds.getWeek());
    }

    /**
     * @return com.tvbc.common.utils.DateResultInfo
     * @Description 获取日期的年、月、自然周
     * @methodName getWeekInt
     * @Author ja
     * @Date 18:02 2019-12-11
     * @Param [time]
     **/
    public static DateResultInfo getWeekInt(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setTime(date);
        //由于Calendar在跨年周的时候的第几周时，Calendar会根据每周最后一天所在年份去计算第几周
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int week = cal.get(Calendar.WEEK_OF_YEAR);// 属于当年第几周
        if (week == 1 && month == 12) {
            month = 1;
            year++;
        }
        return new DateResultInfo(year, month, week, date, 0l);
    }


    /**
     * @return java.util.List<java.util.Date>
     * @Description 获取两个日期字符串之间的日期集合
     * @methodName getBetweenDate
     * @Author ja
     * @Date 17:57 2019-12-11
     * @Param [startDate, endDate]
     **/
    public static List<Date> getBetweenDate(Date startDate, Date endDate) {
        // 声明保存日期集合
        List<Date> list = new ArrayList<>();
        //用Calendar 进行日期比较判断
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);

        String startFormat = sdf.format(startDate);
        String endFormat = sdf.format(endDate);
        if (startFormat.equals(endFormat)) {
            list.add(startDate);
            list.add(endDate);
            return list;
        }
        while (startDate.getTime() <= endDate.getTime()) {

            // 把日期添加到集合
            list.add(startDate);
            // 设置日期
            calendar.setTime(startDate);
            //把日期增加一天
            calendar.add(Calendar.DATE, 1);
            startDate = calendar.getTime();
            String whileStartFormat = sdf.format(startDate);
            String whileEndFormat = sdf.format(endDate);
            if (!whileStartFormat.equals(whileEndFormat)) {
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                startDate = calendar.getTime();
            } else {
                startDate = endDate;
            }
        }
        return list;
    }

    /**
     * @return java.util.List<com.tvbc.common.utils.DateResultInfo>
     * @Description 获取两个日期字符串之间的日期集合
     * @methodName getDateResultInfoBetweenDate
     * @Author ja
     * @Date 18:40 2019-12-11
     * @Param [startDate, endDate]
     **/
    public static List<DateResultInfo> getDateResultInfoBetweenDate(Date startDate, Date endDate) {
        List<DateResultInfo> dateResultInfos = new ArrayList<>();
        List<Date> dateList = getBetweenDate(startDate, endDate);
        for (int i = 0; i < dateList.size() && dateList.size() > 1; i++) {
            Date date = dateList.get(i);
            DateResultInfo dateResultInfo = getWeekInt(date);
            if (i < dateList.size() - 1) {
                Date nextDay = null;
                nextDay = dateList.get(i + 1);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nextDay);
                if (i + 1 < dateList.size() - 1) {
                    calendar.set(Calendar.MILLISECOND, 0);
                    nextDay = calendar.getTime();
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    nextDay = calendar.getTime();
                }
                calendar.setTime(date);
                calendar.set(Calendar.MILLISECOND, 0);
                date = calendar.getTime();
                long time = (nextDay.getTime() - date.getTime()) / 1000;
                dateResultInfo.setTime(time);
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date day = calendar.getTime();
                long time = (date.getTime() - day.getTime()) / 1000;
                dateResultInfo.setTime(time);
            }
            dateResultInfos.add(dateResultInfo);
        }
        return dateResultInfos;
    }

    /**
     * @return java.util.List<com.tvbc.common.utils.DateResultInfo>
     * @Description 统计跨周时长
     * @methodName statisticsOfCrossWeekDuration
     * @Author ja
     * @Date 10:48 2019-12-12
     * @Param [startDate, endDate]
     **/
    public static List<DateResultInfo> statisticsOfCrossWeekDuration(Date startDate, Date endDate) {
        List<DateResultInfo> statisticsDateResultInfos = new ArrayList<>();
        List<DateResultInfo> dateResultInfoBetweenDate = getDateResultInfoBetweenDate(startDate, endDate);
        for (DateResultInfo d : dateResultInfoBetweenDate) {
            boolean isExist = false;
            for (DateResultInfo sd : statisticsDateResultInfos) {
                String dFormat = d.getYear() + "W" + d.getWeek();
                String sdFormat = sd.getYear() + "W" + sd.getWeek();
                if (dFormat.equals(sdFormat)) {
                    sd.setTime(sd.getTime() + d.getTime());
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                d.setDate(null);
                statisticsDateResultInfos.add(d);
            }
        }
        return statisticsDateResultInfos;
    }

    /**
     * @return java.util.List<com.tvbc.common.utils.DateResultInfo>
     * @Description 统计跨周时长(最后周)
     * @methodName statisticsOfCrossLastWeek
     * @Author ja
     * @Date 10:48 2019-12-12
     * @Param [startDate, endDate]
     **/
    public static DateResultInfo statisticsOfCrossLastWeek(Date startDate, Date endDate) {
        List<DateResultInfo> dateResultInfos = statisticsOfCrossWeekDuration(startDate, endDate);
        if (dateResultInfos != null && dateResultInfos.size() > 0) {
            return dateResultInfos.get(dateResultInfos.size() - 1);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        SimpleDateFormat sdf2 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
//        Date de = getDeadLine(new Date());
//
//        List<DateResultInfo> betweenDate = DateUtils.getDateResultInfoBetweenDate(new Date(), sdf2.parse("2020-01-01 " + " 11:22:00"));
//        List<DateResultInfo> statistics = DateUtils.statisticsOfCrossWeekDuration(new Date(), sdf2.parse("2020-01-01 " + " 11:22:00"));

        System.out.println(stringToDate("2020-09-18", YYYY_MM_DD));
    }

    public static String getCycleSuffix(Date date, int cycleType) {
        String suffix;
        switch (cycleType) {
            case CycleType.HOUR:
                suffix = DateUtils.dateToString(date, "yyyyMMddHH");
                break;
            case CycleType.DAY:
                suffix = DateUtils.dateToString(date, "yyyyMMdd");
                break;
            case CycleType.WEEK:
                suffix = DateUtils.getWeek(date);
                break;
            case CycleType.MONTH:
                suffix = DateUtils.dateToString(date, "yyyyMM");
                break;
            default:
                return StringUtils.EMPTY;
        }
        return suffix;
    }
}
