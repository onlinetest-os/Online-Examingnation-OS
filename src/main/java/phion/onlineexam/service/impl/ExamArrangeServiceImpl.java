package phion.onlineexam.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.ExamArrange;
import phion.onlineexam.dao.ExamArrangeMapper;
import phion.onlineexam.service.ExamArrangeService;

@Service
public class ExamArrangeServiceImpl implements ExamArrangeService{

	@Autowired
	ExamArrangeMapper examArrangeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void addExamArrange(ExamArrange examArrange) {
		examArrangeMapper.insert(examArrange);
	}

	@Override
	public int queryExamArrangeCount(ExamArrange examArrange) {
		return examArrangeMapper.selectByExampleSelective(examArrange, null).size();
	}

	@Override
	public void deleteExamArrangesByEId(Integer eId) {
		examArrangeMapper.deleteByEId(eId);
	}

	@Override
	public void addExamArrangeBatch(List<ExamArrange> examArranges) {
		ExamArrangeMapper mapper = sqlSession.getMapper(ExamArrangeMapper.class);
		for(ExamArrange examArrange : examArranges) {
			mapper.insert(examArrange);
		}
	}

	@Override
	public List<ExamArrange> queryExamArrange(ExamArrange examArrange) {
		return examArrangeMapper.selectByExampleSelective(examArrange, null);
	}

}
