package phion.onlineexam.utils;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ConfigHelper {
	
	public static Map getConfig(HttpServletRequest request) {

		//..\assets\config.xml
		StringBuilder configPath = new StringBuilder();
		//String userPath = System.getProperty("user.dir");
		String folderPath = request.getSession()
				.getServletContext()
				.getRealPath(File.separator);
		
		configPath.append(folderPath)
					.append("assets").append(File.separator)
					.append("config.xml");
		System.out.println(configPath.toString());
		return XMLHelper.xmlToMap(configPath.toString());
	}
	
	public static void main(String[] args) {
		//System.out.println(getConfig());
	}
}
