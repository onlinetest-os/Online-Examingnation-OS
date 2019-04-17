package phion.onlineexam.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phion.onlineexam.bean.Exam;

public class DataChangeUtil {
	
	private DataChangeUtil() {};
	
	public static List<Map<String,Object>> getSimpleExams(List<Exam> exams) {
		if(exams==null||exams.size()<0) return null;
		List<Map<String,Object>> examsInfos = new ArrayList<Map<String,Object>>();
		for(Exam e:exams) {
			Map map = new HashMap<>();
			map.put("startTime", DateUtil.formateDate(e.getStartTime()));
			map.put("endTime", DateUtil.formateDate(e.getEndTime()));
			map.put("eName", e.geteName());
			map.put("status", e.getStatus());
			map.put("eId", e.geteId());
			map.put("teaId", e.geteId());
			examsInfos.add(map);
		}
		return examsInfos;
	}
	public static List<Map<String,Object>> getComplexExams(List<Exam> exams) {
		if(exams==null||exams.size()<0) return null;
		List<Map<String,Object>> examsInfos = new ArrayList<Map<String,Object>>();
		for(Exam e:exams) {
			Map map = new HashMap<>();
			map.put("startTime", DateUtil.formateDate(e.getStartTime()));
			map.put("endTime", DateUtil.formateDate(e.getEndTime()));
			map.put("eName", e.geteName());
			map.put("status", e.getStatus());
			map.put("eId", e.geteId());
			map.put("teaId", e.geteId());
			map.put("paperAnwserPath", e.getPaperAnwserPath());
			map.put("paperPath", e.getPaperPath());
			map.put("students", e.getStudents());
			map.put("examInfo", e.getExamInfo());
			examsInfos.add(map);
		}
		return examsInfos;
	}
}
