package phion.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.ExamExample;
import phion.onlineexam.dao.ExamMapper;
import phion.onlineexam.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService{

	@Autowired
	ExamMapper examMapper;
	
	public List<Exam> queryExam(ExamExample examExample) {
		List<Exam> exams = examMapper.selectByExample(examExample);
		return exams;
	}
	
	public List<Exam> queryExamWithExamInfo(Exam exam) {
		List<Exam> exams = examMapper.selectWithExamInfoSelective(exam);
		return exams;
	}

	public void addExam(Exam exam) {
		examMapper.insert(exam);
		
	}

	public void addExamsBatch(List<Exam> exams) {
		// TODO Auto-generated method stub
		
	}

	public void updateExam(Exam exam) {
		examMapper.updateByPrimaryKeySelective(exam);
	}

	public void deleteExam(Integer eId) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * 主键查询考试，附带学生
	 */
	@Override
	public Exam selectByPrimaryKeyWithStudent(Integer eId) {
		return examMapper.selectByPrimaryKeyWithStudent(eId);
	}

	/**
	 * 主键查询考试
	 */
	@Override
	public Exam selectByPrimaryKey(Integer eId) {
		return examMapper.selectByPrimaryKey(eId);
	}

	@Override
	public Exam queryById(Integer eId) {
		return selectByPrimaryKey(eId);
	}
}
