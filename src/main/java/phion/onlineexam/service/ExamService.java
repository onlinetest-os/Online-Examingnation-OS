package phion.onlineexam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.ExamExample;
import phion.onlineexam.bean.Student;
import phion.onlineexam.dao.ExamMapper;

public interface ExamService {

	/**
	 * 复杂查询
	 */
	public List<Exam> queryExam(ExamExample examExample);
	
	/**
	 * 有选择的查询,附带考试信息
	 * @param exam
	 * @return
	 */
	public List<Exam> queryExamWithExamInfo(Exam exam);
	
	/**
	 * 用考试Id，做有选择的查询,附带考试信息
	 * @param exam
	 * @return
	 */
	public Exam queryExamWithExamInfoByEId(Integer eId);
	

	/**
	 * 根据主键查询考试，并带有学生信息 
	 * @param eId
	 * @return
	 */
	public Exam selectByPrimaryKeyWithStudent(Integer eId);
	
	/**
	 * 根据主键查询考试
	 * @param eId
	 * @return
	 */
	public Exam selectByPrimaryKey(Integer eId);
	
	/**
	 * 根据主键查询考试 query
	 * @param eId
	 * @return
	 */
	public Exam queryById(Integer eId);
	
	
	public void addExam(Exam exam);
	

	public void addExamsBatch(List<Exam> exams);
	

	public void updateExam(Exam exam);
	

	public void deleteExam(Integer eId);
	
}
