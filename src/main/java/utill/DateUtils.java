package utill;

import enums.BaseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 合并了当前各工程的DateUtils
 *
 * @author zh
 */
public class DateUtils {
    public static final long DAY = 24 * 60 * 60 * 1000L;
    public static final long HOUR = 60 * 60 * 1000L;
    public static final long MINUTE = 60 * 60 * 1000L;

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static String DEADLINE_STR = " 23:59:59";
    public static String BEGIN_LINE_STR = " 00:00:00";

    public static String dateToSimpleStringYYYYMMDD(Date time) {
        return dateToString(time, YYYYMMDD);
    }

    public static Date dateToISODate(Date dateStr) {
        Date parse = null;
        try {
            // 解析字符串时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            parse = format.parse(format.format(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static Date getDeadLine(Date time) {
        return DateUtils.stringToDate(dateToString(time, YYYY_MM_DD) + DEADLINE_STR);
    }

    public static Date getBeginLine(Date time) {
        return DateUtils.stringToDate(dateToString(time, YYYY_MM_DD) + BEGIN_LINE_STR);
    }

    public static Long dateToLong(Date time) {
        if (null == time) {
            return 0L;
        }
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(YYYYMMDD);
        String string = formatter.format(time);
        return Long.parseLong(string);
    }

    public static Long to8BitDay(Long num14Bit) {
        Date date = DateUtils.num14BitToDate(num14Bit);
        return DateUtils.timeFor8BitNum(date);
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

    public static Long timeFor8BitNum(Date date) {
        return Long.parseLong(dateToString(date, YYYYMMDD));
    }

    public static Date num8BitToDate(Long num8Bit) {
        return stringToDate(num8Bit.toString(), YYYYMMDD);
    }

    public static Date num14BitToDate(Long num14Bit) {
        return stringToDate(num14Bit.toString(), YYYYMMDDHHMMSS);
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

    public static Long nowFor12BitNum() {
        return Long.parseLong(dateToString(new Date(), "yyyyMMddHHmm"));
    }

    public static Long nowFor10BitNum() {
        return Long.parseLong(dateToString(new Date(), "yyyyMMddHH"));
    }

    public static String nowOfStr(String format) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(format);
        return formatter.format(new Date());
    }

    public static Date endOfNow() {
        String str = nowOfStr(YYYY_MM_DD) + " 23:59:59:59";
        return DateUtils.stringToDate(str);
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);

    }

    public static String getDateToString(long time, String format) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(d);
    }

    public static Date addDate(Date curDate, int num, DateUnit dateUnit) {
        switch (dateUnit) {
            case DAY:
                return addDay(curDate, num);
            case MONTH:
                return addMonth(curDate, num);
            default:
                return curDate;
        }
    }

    /**
     * 获取某日期n个月后的天数
     *
     * @return
     */
    public static int daysAfterMonth(Date srcDate, int month) {
        Assert.isTrue(month >= 0, "month must big then 0");
        Date end = addMonth(srcDate, month);
        return compareDays(srcDate, end);
    }

    public static enum DateUnit implements BaseEnum {
        /**
         * 数据库保存的是，枚举顺序整型，并非设置的值，不要轻易改变枚举顺序
         */
        DAY("日", 0),
        MONTH("月", 1),
        YEAR("年", 2);
        private String name;
        private Integer value;

        private DateUnit(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public static String getName(int value) {
            for (DateUnit c : DateUnit.values()) {
                if (c.getValue() == value) {
                    return c.getName();
                }
            }
            return null;
        }

        public static DateUnit getEnum(int value) {
            for (DateUnit c : DateUnit.values()) {
                if (c.getValue() == value) {
                    return c;
                }
            }
            return null;
        }

        public static DateUnit getEnum(String name) {
            for (DateUnit c : DateUnit.values()) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public Map<Integer, String> toMap() {
            LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
            for (DateUnit c : DateUnit.values()) {
                map.put(c.getValue(), c.getName());
            }
            return map;
        }

        @Override
        public String toName() {
            return this.name == null ? this.name() : this.name;
        }

        @Override
        public Integer toValue() {
            return this.value == null ? this.ordinal() : this.value;
        }

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
     * 把秒转换成XX小时XX分钟
     * 如4600秒转换成1小时16分钟
     * 600秒转换成10分钟
     *
     * @param second
     * @return
     */
    public static String secondToHHMM(int second) {
        if (second == 0) {
            return "0分钟";
        }

        if (second < 60) {
            return "1分钟";
        }
        int h = second / 3600;
        int m = (second / 60) % 60;
        String val = h > 0 ? h + "小时" : "";
        val = m > 0 ? val + m + "分钟" : val;
        return val;
    }

    /**
     * T+N天
     *
     * @param date
     * @param day  天数
     * @return
     */
    public static Date addNDay(Date date, int day) {
        Long milliseconds = date.getTime();
        date = new Date(milliseconds + ((1 + day) * 24 * 3600 * 1000L));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            return formatter.parse(strDate + " 00:00:00");
        } catch (ParseException e) {
            return null;
        }
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
     * 将当前传入的时间 基础上 增加 num 分
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

    public static int compareHours(Date startTime, Date endTime) {
        if (endTime.getTime() > startTime.getTime()) {
            Long leftTime = endTime.getTime() - startTime.getTime();
            return (int) Math.ceil(leftTime / (1000 * 60 * 60d));
        }
        return 0;

    }

    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static Date parse(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 计算时间差(有负数)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int compareDays1(Date startTime, Date endTime) {
        long startTimeTime = startTime.getTime();
        long endTimeTime = endTime.getTime();
        if (startTimeTime == endTimeTime) {
            return 0;
        }
        int i = (int) ((endTimeTime - startTimeTime) / (1000 * 3600 * 24));
        if (i == 0) {
            if (endTime.before(startTime)) {
                return i - 1;
            }
            return i + 1;
        }
        return i;
    }

    /**
     * @Title: getDayEndTime
     * @Description: (获取某个日期的结束时间)
     * @author zzj
     */
    public static Date getDayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getDayBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Date(calendar.getTimeInMillis());
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

    public static int compareDays(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return compareDays(sdf.parse(startTime), sdf.parse(endTime));
        } catch (ParseException e) {
            return 0;
        }
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

    public static Date stringToDate(String lotteryDeadline) {
        return stringToDate(lotteryDeadline, YYYY_MM_DD_HH_MM_SS);
    }


    public static int getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long seconds = (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        return seconds.intValue();
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static long getThisWeekMondayBegin(Long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTimeInMillis();
    }

    public static long getDayOfWeekEnd(long monday, int addDay) {
        return monday + (60 * 60 * 24 * (addDay + 1) - 1) * 1000;
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
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

    public static String getDay(Date date) {
        return DateUtils.dateToString(date, YYYYMMDD);
    }

    public static Long getDay(Long time) {
        String timeStr = String.valueOf(time);
        timeStr = timeStr.substring(0, 8);
        String dateString = timeStr + "000000";
        return Long.parseLong(dateString);
    }

    public static String getMonth(Date date) {
        return DateUtils.dateToString(date, YYYY_MM);
    }

    /**
     * @return
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
     * @return java.util.List<>
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
     * @return
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
     * @return
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

    public static Date[] getCycleDate(Date date, int cycleType, int offsets) {
        String dateStart;
        String dateEnd;
        switch (cycleType) {
            case CycleType.HOUR:
                Date hour = DateUtils.addHour(date, offsets);
                dateStart = DateUtils.dateToString(hour, "yyyyMMddHH") + "0000";
                dateEnd = DateUtils.dateToString(hour, "yyyyMMddHH") + "5959";
                break;
            case CycleType.DAY:
                Date day = DateUtils.addDay(date, offsets);
                dateStart = DateUtils.dateToString(day, "yyyyMMdd") + "000000";
                dateEnd = DateUtils.dateToString(day, "yyyyMMdd") + "235959";
                break;
            case CycleType.WEEK:
                Date weekDate = DateUtils.addWeek(date, offsets);
                String week = DateUtils.getWeek(weekDate);
                Date[] dateW = DateUtils.getBeginAndEndDateOfWeek(week);
                dateStart = DateUtils.dateToSimpleStringYYYYMMDD(dateW[0]) + "000000";
                dateEnd = DateUtils.dateToSimpleStringYYYYMMDD(dateW[1]) + "235959";
                break;
            case CycleType.MONTH:
                Date monthDate = DateUtils.addMonth(date, offsets);
                Date[] dateM = DateUtils.getBeginAndEndDateOfMonth(monthDate);
                dateStart = DateUtils.dateToSimpleStringYYYYMMDD(dateM[0]) + "000000";
                dateEnd = DateUtils.dateToSimpleStringYYYYMMDD(dateM[1]) + "235959";
                break;
            default:
                return null;
        }
        return new Date[]{DateUtils.stringToDate(dateStart, YYYYMMDDHHMMSS), DateUtils.stringToDate(dateEnd, YYYYMMDDHHMMSS)};
    }

    public static Date[] getBeginAndEndDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        Date begin = calendar.getTime();
        //获取当前月最后一天
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end = calendar.getTime();
        return new Date[]{begin, end};
    }

    /**
     * 获取当月实际天数
     *
     * @param date
     * @return
     */
    public static int getMonthActualDayNum(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getMonthEndDay(Date date) {
        //获取当前月最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
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

    public static boolean timeInRange(Date begin, Date end, Date target) {
        long targetLong = target.getTime();
        return (begin.getTime() <= targetLong) && (end.getTime() >= targetLong);
    }

    /**
     * 获取秒时间戳
     *
     * @param date
     * @return
     */
    public static long getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Long.parseLong(timestamp);
    }

    public static String changeDateTimeToGmtTime(Date time) {
        DateFormat sf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        if (time != null) {
            sf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return sf.format(time);
        }
        return null;
    }

    public static long getTodayLeftSeconds() {
        Date now = new Date();
        Date end = getDayEndTime(now);
        return (end.getTime() - now.getTime()) / 1000;
    }

    public static String dateToStringYYYYMMDD(Date time) {
        if (null == time) {
            return null;
        }
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyyMMdd");
        String string = formatter.format(time);

        return string;
    }
}
