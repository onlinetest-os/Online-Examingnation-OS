package phion.onlineexam.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	
	private JsonUtil() {};
	
	public static String toJson(Object o) {
		return JSON.toJSONString(o);
	}
}
