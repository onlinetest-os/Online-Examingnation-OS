package phion.onlineexam.service;

import phion.onlineexam.bean.ExamArrange;

public interface ExamArrangeService {
	
	/**
	 * 添加考试安排
	 */
	public void addExamArrange(ExamArrange examArrange);
	
	
	/**
	 * 查找考试安排记录
	 */
	public int queryExamArrangeCount(ExamArrange examArrange);
	
}
