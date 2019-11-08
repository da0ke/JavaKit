package cn.da0ke.javakit.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 相对时间转换工具类
 * @author da0ke
 *
 */
public class RelativeTimeUtils {

	private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    
    private static String format(Date startDate, Date endDate) {
    	long start = startDate.getTime();
    	long end = endDate.getTime();
    	
        long delta = end - start;
        
        // x秒前
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        
        // x分钟前
        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        
        // x小时前
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        
        // 时分秒清零
        end = getMillisAfterClear(end, Calendar.MILLISECOND, Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR_OF_DAY);
        start = getMillisAfterClear(start, Calendar.MILLISECOND, Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR_OF_DAY);
        delta = end - start;
        
        // 昨天
        if (delta < 2L * ONE_DAY) {
            return "昨天";
        }
        
        // 前天
        if (delta < 3L * ONE_DAY) {
        	return "前天";
        }
        
        // x天前
        if(isSameMonth(start, end) || delta < 30L * ONE_DAY) {
        	long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }

        // yyyy-MM-dd
        return TimeUtils.date2String(startDate, "yyyy-MM-dd");
    }
    
    
 
    public static String format(Date date) {
    	return format(date, new Date());
    }
 
    private static long toSeconds(long date) {
        return date / 1000L;
    }
 
    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }
 
    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }
 
    private static long toDays(long date) {
        return toHours(date) / 24L;
    }
    
    private static long getMillisAfterClear(long date, int... fields) {
        Calendar calendar =  Calendar.getInstance();
        calendar.setTimeInMillis(date);
        
        for (int f : fields) {
        	calendar.set(f, 0);
        }
        
        return calendar.getTimeInMillis();
    }
    
    private static boolean isSameMonth(long start, long end) {
    	Calendar c1 =  Calendar.getInstance();
        c1.setTimeInMillis(start);
        
        Calendar c2 =  Calendar.getInstance();
        c2.setTimeInMillis(end);
        
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }
    

}
