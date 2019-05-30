package d.phion.test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.controller.TaskController;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.utils.DateUtil;

@Component
public class MyFirstTask {
	 
	//0 0 0 1/1 * ? * 每天晚上0点执行一次
	
	@Autowired
	ExamService service;
	/**
	 * 1、	将所有开始时间为当天的考试状态修改为ready_today
		2、	执行更新开启考试的操作。
	 */
	//@Scheduled(cron="0/5 * * * * ?") // 间隔5秒执行
	@Scheduled(cron="0 0 0 1/1 * ? *") //每天晚上0点执行一次
	public void taskCycle() {
        //查出今天的考试
		//查询出当天开始的考试
		Exam examLike = new Exam();
		List<Exam> exams = service.queryExamWithExamInfo(examLike);
		LocalDateTime now = LocalDateTime.now();
		for(Exam e :exams) {
			Date d = e.getStartTime();
			LocalDateTime dateTime = DateUtil.toLocalDateTime(d);
			if(dateTime.getDayOfYear() == now.getDayOfYear()) {
				e.setStatus(StaticResources.READY_TODAY_EXAM);
				service.updateExam(e);
			}
		}
		
		//更新自动开启考试
		openExam();
    }
	
	/**
	 * 开启考试
	 */
	public void openExam() {
		TaskController.getInstance().updateExamBegin(service);
	}

}
