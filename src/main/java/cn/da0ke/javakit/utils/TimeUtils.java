package cn.da0ke.javakit.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
	
	private static final long SECOND = 1000;
	private static final long MINUTE = SECOND * 60;
	private static final long HOUR = MINUTE * 60;
	private static final long DAY = HOUR * 24;
	
	/**
	 * Date转换为字符串
	 * @param date 日期
	 * @param pattern 字符串格式
	 * @return
	 */
	public static String date2String(Date date, String pattern) {
    	DateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
    	return format.format(date);
    }
	
	/**
	 * 字符串转换为Date
	 * @param s 日期字符串
	 * @param pattern 字符串格式
	 * @return Date
	 * @throws ParseException
	 */
	public static Date string2date(String s, String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
		return format.parse(s);
	}
	
	/**
     * 时间戳转换成字符串格式
     * @param millis 时间戳，毫秒
     * @param pattern 日期时间格式，比如：yyyy-MM-dd HH:mm:ss
     * @return 日期时间字符串
     */
    public static String millis2String(final long millis, String pattern) {
        return date2String(new Date(millis), pattern);
    }
    
    public static Calendar getCalendarAfterMonth(int month) {
		Calendar c = Calendar.getInstance();  
        c.setTime(new Date());
        c.add(Calendar.MONTH, month);
        
        return c;
	}
	
	public static Calendar getCalendarAfterDay(int day) {
		Calendar c = Calendar.getInstance();  
        c.setTime(new Date());
        c.add(Calendar.DATE, day);
        
        return c;
	}
	
	
	public static boolean isToday(Date date) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}
   
	
	/**
	 * 是否在n天内，以毫秒值计算
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param days 相差天数
	 * @return boolean
	 */
    public static boolean isBetweenDays(Date beginDate, Date endDate, int days) {
    	long d1 = beginDate.getTime();
    	long d2 = endDate.getTime();
    	
    	return (d2 - d1) / DAY <= days;
    }
    
    /**
     * 相差天数，以毫秒值计算
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return long
     */
    public static long countDays(Date beginDate, Date endDate) {
    	long d1 = beginDate.getTime();
    	long d2 = endDate.getTime();
    	
    	return (d2 - d1) / DAY;
    }
    
}
