package phion.onlineexam.service;

import phion.onlineexam.bean.ExamInfo;

public interface ExamInfoService {
	/********增**************/
	public void addExamInfo(ExamInfo examInfo);
	
	/********删**************/
	public void deleteExamInfo();
	
	/********改**************/
	public void updateExam();
	
	/********查**************/
	public ExamInfo queryExamInfo();
}
