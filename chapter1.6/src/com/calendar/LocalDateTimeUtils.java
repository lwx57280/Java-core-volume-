package calendar;

import util.DateTimeUtil;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalDateTimeUtils {

    /**
     * 日期格式yyyy-MM-dd
     */
    public static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /**
     * 构造函数
     */
    private LocalDateTimeUtils() {
        super();
    }

    /**
     * Date转LocalDateTime
     *
     * @param date
     *            Date对象
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_TIME_PATTERN);
    }

    /**
     * 按pattern格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime
     *            LocalDateTime对象
     * @param pattern
     *            要格式化的字符串
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        if (pattern == null || pattern.isEmpty()) {
            pattern = DATE_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * 获取今天的00:00:00
     *
     * @return
     */
    public static String getDayStart() {
        return getDayStart(LocalDateTime.now());
    }

    /**
     * 获取今天的23:59:59
     *
     * @return
     */
    public static String getDayEnd() {
        return getDayEnd(LocalDateTime.now());
    }

    /**
     * 获取某天的00:00:00
     *
     * @param dateTime
     * @return
     */
    public static String getDayStart(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MIN));
    }

    /**
     * 获取某天的23:59:59
     *
     * @param dateTime
     * @return
     */
    public static String getDayEnd(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MAX));
    }

    /**
     * 获取本月第一天的00:00:00
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        return getFirstDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取本月最后一天的23:59:59
     *
     * @return
     */
    public static String getLastDayOfMonth() {
        return getLastDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取某月第一天的00:00:00
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static String getFirstDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
    }

    /**
     * 获取某月最后一天的23:59:59
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static String getLastDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX));
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getDayStart());
        System.out.println(getDayEnd());

        System.out.println(getFirstDayOfMonth());
        System.out.println(getLastDayOfMonth());
        System.out.println("======================");



        LocalDateTime everyDay = LocalDateTime.now();

        ZoneId zone = ZoneId.systemDefault();

        int year = everyDay.getYear();
        int month = everyDay.getMonth().getValue();
        int day = everyDay.getDayOfMonth();
        int startYear = year - 3;

        LocalDateTime startDate = LocalDateTime.of(startYear, month, day, 00, 00, 00);
        long startTime= startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        long endTime = Timestamp.valueOf(everyDay).getTime();

        List<String> dateList = buildDateList(startTime, endTime);

        System.out.println("==========dateList============"+dateList);
    }


    private static List<String> buildDateList(long startTime, long endTime) {

        List<String> dayList = new ArrayList();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        long day = 1000 * 60 * 60 * 24;
        for (long i = startTime; i <= endTime; i += day) {
            String strTime = DateTimeUtil.convertTimeToString(i, dateTimeFormatter);
            dayList.add(strTime);
        }

        return dayList;
    }



    public static void demoList(){

        LocalDate today =LocalDate.now();
        System.out.println("今天的日期是:"+today);
        LocalDate tommorow = today.plus(1, ChronoUnit.DAYS);

        System.out.println("明天的日期是:"+tommorow);

        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        System.out.println("Your Date of birth is : " + dateOfBirth);

        LocalDate nextWeek = dateOfBirth.plus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + nextWeek);

        LocalDateTime localtDateAndTime = LocalDateTime.of(2016, 01, 10, 00, 10,00);
        LocalDate  localTime = localtDateAndTime.toLocalDate();
        System.out.println("Date before 1 localTime : " + localTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        String time = localtDateAndTime.format(formatter);
        System.out.println("Date before 1 localtDateAndTime : " + localtDateAndTime.getYear()+"/"
                +localtDateAndTime.getMonthValue()+"/"
                +localtDateAndTime.getDayOfMonth()+"/"
                +localtDateAndTime.getHour()+":"
                +localtDateAndTime.getMinute()+":"
                 +localtDateAndTime.getSecond());

        System.out.println("Date before 1 time : " + time);

        LocalDate oneYearsAfter = today.plus(1L, ChronoUnit.YEARS);// 1年后
        System.out.println("Date before 1 oneYearsAfter : " + oneYearsAfter);
    }



    public static List<String> getBetweenDate(String start, String end){
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.MILLIS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d ->d.plusDays(1)
        ).limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }

    public static List<LocalDate> dateTimeList(){
        LocalDate start = LocalDate.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1).with(TemporalAdjusters.lastDayOfMonth());
        List<LocalDate> dateList = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end))
                .collect(Collectors.toList());
        return dateList;
    }
}
