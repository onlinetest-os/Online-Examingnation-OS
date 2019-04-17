package phion.onlineexam.utils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class DateUtil {
	private DateUtil() {};
	
	/**
     * 返回当前的日期
     * @return
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }
    
    /**
     * 返回当前时间
     * @return
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }
    
    /**
     * 返回当前日期时间
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }
    
    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的日期时间LocalDate
     */
    public static String formateLocalDate(LocalDateTime localDateTime) {
    	DateTimeFormatter formatter =  
				 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	return formatter.format(localDateTime);
    }
    
    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的日期时间Date
     */
    public static String formateDate(Date date) {
    	DateTimeFormatter formatter =  
				 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat format2 = new SimpleDateFormat("MM月dd号 HH:mm");
    	return format2.format(date);
    }
    
    /**
     * 解析时间字符串，返回LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeByDateString(String dateString) {
    	DateTimeFormatter formatter =  
				 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	return LocalDateTime.parse(dateString, formatter);
    }
	
    public static Date toDate(LocalDateTime localDateTime) {
    	ZoneId zoneId = ZoneId.systemDefault(); 
    	ZonedDateTime zdt = localDateTime.atZone(zoneId); 
    	Date date = (Date) Date.from(zdt.toInstant());
    	return date;
    }
    
    public static LocalDateTime toLocalDateTime(Date date) {
    	 Instant instant = date.toInstant();
         ZoneId zoneId = ZoneId.systemDefault();
         LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
    	return localDateTime;
    }
    
	
	/**
	 * 测试工具类
	 * @param args
	 */
	public static void main(String[] args) {
		DateTimeFormatter formatter =  
				 DateTimeFormatter.ofPattern("MM月dd号 HH:mm");
//		String dateString = formateDate(getCurrentLocalDateTime());
//		System.out.println(dateString);
//		LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
//		System.out.println(localDateTime);
//		System.out.println(toDate(localDateTime));
//		System.out.println(toLocalDateTime(toDate(localDateTime)));
	}
}
