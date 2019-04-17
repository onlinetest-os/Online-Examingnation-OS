package phion.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.dao.ExamMapper;
import phion.onlineexam.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService{

	@Autowired
	ExamMapper examMapper;
	
	public List<Exam> queryExam(Exam exam) {
		List<Exam> exams = examMapper.selectWithExamInfoSelective(exam);
		return exams;
	}

	public void addExam(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	public void addExamsBatch(List<Exam> exams) {
		// TODO Auto-generated method stub
		
	}

	public void updateExam(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	public void deleteExam(Integer eId) {
		// TODO Auto-generated method stub
		
	}
}
