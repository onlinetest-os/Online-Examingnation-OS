package d.phion.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ATask implements IATask{
       //@Scheduled(cron="0/55 * *  * * ? ")   //每10秒执行一次    
       @Override    
       public void aTask(){    
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            //System.out.println(sdf.format(DateTime.now().toDate())+"*********A任务每10秒执行一次进入测试");    
       }    
}  