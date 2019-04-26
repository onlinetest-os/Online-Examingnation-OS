package phion.onlineexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.ExamInfo;
import phion.onlineexam.dao.ExamInfoMapper;
import phion.onlineexam.service.ExamInfoService;

@Service
public class ExamInfoServiceImpl implements ExamInfoService{

	@Autowired
	ExamInfoMapper examInfoMapper;
	
	@Override
	public void addExamInfo(ExamInfo examInfo) {
		examInfoMapper.insert(examInfo);
	}

	@Override
	public void deleteExamInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExamInfo queryExamInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
