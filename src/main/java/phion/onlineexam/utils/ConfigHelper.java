package phion.onlineexam.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigHelper {
	
	public static Map getConfig() {

		//E:\Maven\projects\OnlineExam\src\main\resources\config.xml
		StringBuilder configPath = new StringBuilder();
		String userPath = System.getProperty("user.dir");
		configPath.append(userPath).append(File.separator)
					.append("src").append(File.separator)
					.append("main").append(File.separator)
					.append("resources").append(File.separator)
					.append("config.xml");
		
		return XMLHelper.xmlToMap(configPath.toString());
	}
	
	public static void main(String[] args) {
		System.out.println(getConfig().get("autoStartExam"));
	}
}
