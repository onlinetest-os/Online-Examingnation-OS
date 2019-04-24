package phion.onlineexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.ExamArrange;
import phion.onlineexam.dao.ExamArrangeMapper;
import phion.onlineexam.service.ExamArrangeService;

@Service
public class ExamArrangeServiceImpl implements ExamArrangeService{

	@Autowired
	ExamArrangeMapper examArrangeMapper;
	
	@Override
	public void addExamArrange(ExamArrange examArrange) {
		examArrangeMapper.insert(examArrange);
	}

	@Override
	public int queryExamArrangeCount(ExamArrange examArrange) {
		return examArrangeMapper.selectByExampleSelective(examArrange, null).size();
	}

}
