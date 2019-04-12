package phion.onlineexam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.Student;
import phion.onlineexam.dao.ExamMapper;

public interface ExamService {
	
	/**
	 * 考生总数
	 * @param exam
	 * @return
	 */
	int getStudentsCount(Integer eId);
	
	
	/**
	 * 考生信息
	 * @param exam
	 * @return
	 */
	List<Student> getStudents(Integer eId);
	
	
	/**
	 * 在线考生信息
	 * @param exam
	 * @return
	 */
	List<Student> getOnlineStudents(Integer eId);
	
	
	/**
	 * 在线考生总数
	 * @param eId
	 * @return
	 */
	int getOnlineStudentsCount(Integer eId);
	
	
	/**
	 * 不在线考生信息
	 * @param exam
	 * @return
	 */
	List<Student> getOfflineStudents(Integer eId);
	
	
	/**
	 * 不在线考生总数
	 * @param eId
	 * @return
	 */
	int getOfflineStudentsCount(Integer eId);
	
	
	/**
	 * 已经提交答案的学生信息
	 * @param eId
	 * @return
	 */
	List<Student> getHaveSentAnwserStudents(Integer eId);
	
	/**
	 * 已经提交答案的学生总数
	 * @param eId
	 * @return
	 */
	int getHaveSentAnwserStudentsCount(Integer eId);
	
	/**
	 * 未提交答案的学生信息
	 * @param eId
	 * @return
	 */
	List<Student> getHaveNotSentAnwserStudents(Integer eId);
	
	/**
	 * 未提交答案的学生总数
	 * @param eId
	 * @return
	 */
	int getHaveNotSentAnwserStudentsCount(Integer eId);
	
	
}
