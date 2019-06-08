package phion.onlineexam.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.service.impl.ExamServiceImpl;
import phion.onlineexam.utils.DateUtil;

/**
 * 单列够造
 */
@Controller
public class TaskController {

	private static TaskController instance = new TaskController();

	private ExamService service;

	private volatile boolean success = true;

	private TaskController() {

	}

	public static TaskController getInstance() {
		return instance;
	}

	/**
	 * 开启更新考试任务
	 */
	public synchronized void updateExamBegin(ExamService service) {
		this.service = service;

		// 更新所有考试状态
		updateAllExam();

		// 查询出当天开始的考试
		Exam examLike = new Exam(StaticResources.READY_TODAY_EXAM);
		List<Exam> exams = service.queryExamWithExamInfo(examLike);
		if (exams.size() <= 0)
			return;
		Exam fisrtExam = exams.get(0);
		for (Exam e : exams) {
			if (DateUtil.getSeconds(DateUtil.toLocalDateTime(e.getStartTime()),
					DateUtil.toLocalDateTime(fisrtExam.getStartTime())) < 0) {
				fisrtExam = e;
			}
		}
		System.out.println("当天开始的考试有：" + exams);

		LocalDateTime dateTime = DateUtil.toLocalDateTime(fisrtExam.getStartTime());
		LocalTime time = dateTime.toLocalTime();

		// 关闭原来的考试,开启新的考试
		if (autoBeginExamThread != null) {
			autoBeginExamThread.interrupt();
			autoBeginExamThread = null;
		}
		autoBeginExamThread = new ExamThread(time, fisrtExam.geteId());
		autoBeginExamThread.start();
	}

	/**
	 * 开启考试
	 */
	public void startExam(int eId) {
		Exam fisrtExam = service.queryById(eId);

		// 设置考试为开启状态
		fisrtExam.setStatus(StaticResources.RUNNING_EXAM);
		// 更新
		service.updateExam(fisrtExam);
		System.out.println("考试开启成功");
	}

	/**
	 * 更新考试状态
	 * 
	 * @param args
	 */

	public void updateAllExam() {
		System.out.println("updateAllExam");
		// 查出今天的考试
		// 查询出当天开始的考试
		Exam examLike = new Exam();
		List<Exam> exams = service.queryExamWithExamInfo(examLike);
		LocalDateTime now = LocalDateTime.now();
		for (Exam e : exams) {
			Date ds = e.getStartTime();
			Date de = e.getEndTime();
			LocalDateTime dateTimeS = DateUtil.toLocalDateTime(ds);
			LocalDateTime dateTimeE = DateUtil.toLocalDateTime(de);
			if (dateTimeS.getDayOfYear() == now.getDayOfYear()) {
				e.setStatus(StaticResources.READY_TODAY_EXAM);
				System.out.println("更新为当天考试");
//				System.out.println(dateTimeE);
//				System.out.println(dateTimeS);
//				System.out.println(Duration.between(dateTimeE, now).toMinutes());
//				System.out.println(Duration.between(dateTimeS, now).toMinutes());
				if (Duration.between(dateTimeE, now).toMinutes() < 0
						&& Duration.between(dateTimeS, now).toMinutes() > 0) {
					e.setStatus(StaticResources.RUNNING_EXAM);
					System.out.println("更新为正在考试");
				}
				service.updateExam(e);
			}else if(dateTimeS.getDayOfYear() < now.getDayOfYear()){
				e.setStatus(StaticResources.COMPLETE_EXAM);
				service.updateExam(e);
			}else {
				e.setStatus(StaticResources.READY_EXAM);
				service.updateExam(e);
			}
			//System.out.println(dateTimeS);
		}

	}
	
	/**
	 * 测试入口
	 * @param args
	 */
	public static void main(String[] args) {
		LocalTime nowTime = DateUtil.getCurrentLocalTime();
		LocalTime beginTime = LocalTime.of(22, 0);
		int seconds = DateUtil.getSeconds(nowTime, beginTime);
		System.out.println(seconds);
	}
	
	/**
	 * 启动线程类
	 */

	ExamThread autoBeginExamThread;

	static final int SECOND_UNIT = 1000;

	class ExamThread extends Thread {

		private int seconds = 1;

		private int eId;

		public ExamThread(LocalTime time, int eId) {
			LocalTime nowTime = DateUtil.getCurrentLocalTime();
			LocalTime beginTime = time;
			int temp = DateUtil.getSeconds(nowTime, time);
			if (temp > 0) {
				seconds = temp;
				this.eId = eId;
			}
			System.out.println("启动考试线程已打开！");
		}

		@Override
		public void run() {
			try {
				System.out.println("休眠时间:" + seconds * SECOND_UNIT + "毫秒");
				Thread.sleep(seconds * SECOND_UNIT);
				success = true;
			} catch (InterruptedException e) {
				success = false;
				System.out.println("考试开启定时器被重置！");
			}
			if (success) {
				System.out.println("休眠时间到，开启考试,考试id:" + eId);
				// 开启考试
				startExam(eId);
			} else {
				System.out.println("线程被意外中断，不执行开启考试！");
			}
		}
	}

}
