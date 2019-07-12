package cn.da0ke.javakit.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
	
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
   
    
    
    
}
