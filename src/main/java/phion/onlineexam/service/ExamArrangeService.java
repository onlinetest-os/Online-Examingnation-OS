package phion.onlineexam.service;

import java.util.List;

import phion.onlineexam.bean.ExamArrange;

public interface ExamArrangeService {
	
	/**
	 * 添加考试安排
	 */
	public void addExamArrange(ExamArrange examArrange);
	
	/**
	 * 批量添加考试安排
	 */
	public void addExamArrangeBatch(List<ExamArrange> examArranges);
	
	
	/**
	 * 查找考试安排记录
	 */
	public int queryExamArrangeCount(ExamArrange examArrange);
	
	
	/**
	 * 查找考试安排记录
	 */
	public List<ExamArrange> queryExamArrange(ExamArrange examArrange);
	
	
	/**
	 * 根据考试id删除考试安排
	 */
	public void deleteExamArrangesByEId(Integer eId);
}
