package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
	/**
     * 时间戳转换成字符串格式
     * @param millis 时间戳，毫秒
     * @param pattern 日期时间格式，比如：yyyy-MM-dd HH:mm:ss
     * @return 日期时间字符串
     */
    public static String millis2String(final long millis, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(new Date(millis));
    }
}
