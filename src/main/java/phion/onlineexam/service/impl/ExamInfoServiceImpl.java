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
	public void deleteExamInfoById(Integer inId) {
		examInfoMapper.deleteByPrimaryKey(inId);
	}

	@Override
	public void updateExamBySelective(ExamInfo examInfo) {
		examInfoMapper.updateByPrimaryKeySelective(examInfo);
	}
	
	@Override
	public ExamInfo queryExamInfoByinId(Integer inId) {
		return examInfoMapper.selectByPrimaryKey(inId);
	}

	@Override
	public ExamInfo queryExamInfoByeId(Integer eId) {
		return examInfoMapper.selectByExamID(eId);
	}

}
