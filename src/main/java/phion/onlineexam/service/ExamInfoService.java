package phion.onlineexam.service;

import phion.onlineexam.bean.ExamInfo;

public interface ExamInfoService {
	/********增**************/
	public void addExamInfo(ExamInfo examInfo);
	
	/********删**************/
	public void deleteExamInfoById(Integer inId);
	
	/********改**************/
	public void updateExamBySelective(ExamInfo examInfo);
	
	/********查**************/
	public ExamInfo queryExamInfoByinId(Integer inId);
	
	public ExamInfo queryExamInfoByeId(Integer eId);
	
}
