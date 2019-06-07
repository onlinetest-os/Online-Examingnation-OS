package phion.onlineexam.utils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
     * 解析时间字符串yyyy-MM-dd HH:mm:ss，返回LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeByDateString(String dateString) {
    	DateTimeFormatter formatter =  
				 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	System.out.println(dateString);
    	return LocalDateTime.parse(dateString, formatter);
    }
    /**
     * 解析时间字符串yyyy-MM-ddTHH:mm，返回LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeByDateString2(String dateStringWithT) {
    	String dateString = dateStringWithT.replace('T', ' ');
    	DateTimeFormatter formatter =  
				 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	return LocalDateTime.parse(dateString, formatter);
    }
    
    /**
     * 解析时间字符串yyyy-MM-ddTHH:mm:ss，返回LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeByDateString4(String dateStringWithT) {
    	String dateString = dateStringWithT.replace('T', ' ');
    	DateTimeFormatter formatter =  
				 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	return LocalDateTime.parse(dateString, formatter);
    }
    
    
    
    /**
     * 解析时间字符串yyyy-MM-ddTHH:mm:ss，返回LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeByDateString3(String dateStringWithT) {
    	String dateString = dateStringWithT.replace('T', ' ');
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
		String dateString1 = "2019-04-17T21:37:17";
		String dateString2 = "2019-04-17T22:37:17";
		String dateString3 = "2019-04-18T21:37:17";
		String date1 = dateString1.replace('T', ' ');
		String date2 = dateString1.replace('T', ' ');
		String date3 = dateString1.replace('T', ' ');
		Duration duration= getDuration(dateString1, dateString2);
		System.out.println(duration.toMinutes());
	}
	/**
	 * 返回格式为yyyy-MM-ddTHH:mm的字符串的LocalDateTime的duration
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public static Duration getDuration(String dateStr1,String dateStr2) {
		LocalDateTime date1 = getLocalDateTimeByDateString4(dateStr1);
		LocalDateTime date2 = getLocalDateTimeByDateString4(dateStr2);
		return Duration.between(date1, date2);
	}
	
	public static int Minus(String dateStr1,String dateStr2) {
		Duration duration = getDuration(dateStr1, dateStr2);
		return (int) duration.toMinutes();
	}

	
	/**
	 * localtime 时间差
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public static int getSeconds(LocalTime t1 ,LocalTime t2) {
		Duration duration = Duration.between(t1, t2);
		return (int) duration.getSeconds();
	}
	
	public static int getSeconds(LocalDateTime t1 ,LocalDateTime t2) {
		Duration duration = Duration.between(t1, t2);
		return (int) duration.getSeconds();
	}

	
	
}
