package d.phion.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class BTask implements IBTask{
	   //@Scheduled(cron="0/56 * *  * * ? ")   //每5秒执行一次    
	   @Override 
	   public void bTask(){   
		     DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        // System.out.println(sdf.format(DateTime.now().toDate())+"*********B任务每5秒执行一次进入测试");    
	   }    
}
