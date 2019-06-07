package phion.onlineexam.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.StaticResources;

/**
 * 将对象信息处理成前台需要的
 * @author 15037
 *
 */
public class DataChangeUtil {
	
	private DataChangeUtil() {};
	
	public static List<Map<String,Object>> getSimpleExams(List<Exam> exams) {
		if(exams==null||exams.size()<0) return null;
		List<Map<String,Object>> examsInfos = new ArrayList<Map<String,Object>>();
		for(Exam e:exams) {
			LocalTime startTimeLocal = DateUtil.toLocalDateTime(e.getStartTime()).toLocalTime();
			LocalTime endTimeLocal = DateUtil.toLocalDateTime(e.getEndTime()).toLocalTime();
			Map map = new HashMap<>();
			map.put("startTime", startTimeLocal);
			map.put("endTime",endTimeLocal );
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
	
	public static String getLocalStatus(String status) {
		switch(status) {
		case StaticResources.RUNNING_EXAM:return "正在考试";
		case StaticResources.COMPLETE_EXAM:return "已完成";
		case StaticResources.COMPLETE_AND_CLEAN_EXAM:return "已删除";
		case StaticResources.CREATING_EXAM:return "创建中";
		case StaticResources.READY_EXAM:return "即将考试";
		case StaticResources.READY_TODAY_EXAM:return "今天开考";
		default: return "error";
		}
	}
}
