package cn.da0ke.javakit.utils;

import java.util.Iterator;
import java.util.Objects;

public class StringUtils {
	
	public static boolean isEmpty(CharSequence s) {
		return s == null || "".equals(s);
	}
	
	public static String trimToEmpty(CharSequence s) {
		if (s == null) {
			return "";
		} else {
			StringBuilder sb;
	        //去除字符串前导空白
	        for(sb = new StringBuilder(s); sb.length() > 0 && Character.isWhitespace(sb.charAt(0)); sb.deleteCharAt(0));
	        //去除字符串后导空白
	        for(; sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1)); sb.deleteCharAt(sb.length() - 1));
	        return sb.toString();
		}
	}

	public static boolean isNotEmpty(CharSequence s) {
		return !isEmpty(s);
	}
	
	public static boolean isTrimEmpty(CharSequence s) {
		return isEmpty(trimToEmpty(s));
	}
	
	public static boolean isNotTrimEmpty(CharSequence s) {
		return isNotEmpty(trimToEmpty(s));
	}
	
	public static boolean isNotEmpty(CharSequence... s) {
		boolean fg = true;
		for(int i=0;i<s.length;i++) {
			if(isEmpty(s[i])) {
				fg = false;
				break;
			}
		}
		return fg;
	}

	
	
	/**
	 * 至少有一个为空
	 * @param s
	 * @return
	 */
	public static boolean isAnyEmpty(final CharSequence... s) {
		boolean fg = false;
		for(int i=0;i<s.length;i++) {
			if(isEmpty(s[i])) {
				fg = true;
				break;
			}
		}
		return fg;
	}
	
	 /**
     * <p>Joins the elements of the provided {@code Iterator} into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A {@code null} separator is the same as an empty String ("").</p>
     *
     * <p>See the examples here: {@link #join(Object[],String)}. </p>
     *
     * @param iterator  the {@code Iterator} of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, {@code null} if null iterator input
     */
    public static String join(final Iterator<?> iterator, final String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }
	
}
