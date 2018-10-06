package utils;

import com.alibaba.fastjson.JSONObject;

public class JSONUtils {
	
	/**
	 * 取得String类型数据，默认值：""
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getStringValue(JSONObject json, String key) {
		String result;
		if (json.containsKey(key)) {
			result = json.getString(key);
			if (result == null) {
				result = "";
			}
		} else {
			result = "";
		}
		return result;
	}

	/**
	 * 取得int类型数据，默认值：0
	 * @param json
	 * @param key
	 * @return
	 */
	public static int getIntValue(JSONObject json, String key) {
		return json.getIntValue(key);
	}

	/**
	 * 取得long类型数据，默认值：0
	 * @param json
	 * @param key
	 * @return
	 */
	public static long getLongValue(JSONObject json, String key) {
		return json.getLongValue(key);
	}

	/**
	 * 取得double类型数据，默认值：0
	 * @param json
	 * @param key
	 * @return
	 */
	public static double getDoubleValue(JSONObject json, String key) {
		return json.getDoubleValue(key);
	}

	/**
	 * 取得boolean类型数据，默认值：false
	 * @param json
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(JSONObject json, String key) {
		return json.getBooleanValue(key);
	}
	
}
