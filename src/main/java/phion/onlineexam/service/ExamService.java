package phion.onlineexam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.Student;
import phion.onlineexam.dao.ExamMapper;

public interface ExamService {

	
	public List<Exam> queryExam(Exam exam);
	

	public void addExam(Exam exam);
	

	public void addExamsBatch(List<Exam> exams);
	

	public void updateExam(Exam exam);
	

	public void deleteExam(Integer eId);
	
}
