package phion.onlineexam.utils;

import java.io.File;

public class PathHelper {
	
	/**试卷存储路径获取
	 * 根据考试Id,教师id，教师姓名，班级号，考试名称得到试卷存储路径
	 * 试卷位置:paper/eId_teaId_teaName_stuClass_eName/
	 * @param eId
	 * @param tName
	 * @return
	 */
	public static String getPaperPath(Integer eId,Integer teaId,
			String teaName,String stuClass,String eName){
		StringBuilder sbPath = new StringBuilder();
		sbPath.append("paper").append(File.separator)
				.append(eId).append("_")
				.append(teaId).append("_")
				.append(teaName).append("_")
				.append(stuClass).append("_")
				.append(eName).append(File.separator);
		return sbPath.toString();
	}
	
	/**答案存储路径获取
	 * 根据考试Id,教师id，教师姓名，班级号，考试名称得到答案存储路径
	 * 答案总路径格式：answer/eId_teaId_teaName_stuClass_eName/
	 * @param eId
	 * @param tName
	 * @return
	 */
	public static String getPaperAnwserPath(Integer eId,Integer teaId,
			String teaName,String stuClass,String eName) {
		StringBuilder sbPath = new StringBuilder();
		sbPath.append("anwser").append(File.separator)
				.append(eId).append("_")
				.append(teaId).append("_")
				.append(teaName).append("_")
				.append(stuClass).append("_")
				.append(eName).append(File.separator);
		return sbPath.toString();
	}
	
	/**
	 *根据学号和学生姓名得到答案存储路径
	 * answer/eId_teaId_teaName_stuClass_eName/stuNumber_stuName/
	 * @param eId
	 * @param tName
	 * @return
	 */
	public static String getStudentPaperAnwserPath(String stuNumebr
			,String stuName) {
		StringBuilder sbPath = new StringBuilder();
		sbPath.append(stuNumebr).append("_")
				.append(stuName).append(File.separator);
				
		return sbPath.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getPaperPath(1, 1, "peter", 1+"", "数据结构"));
		System.out.println(getPaperAnwserPath(1, 1, "peter", 1+"", "数据结构"));
		System.out.println(getStudentPaperAnwserPath("1610120016", "蕾欧娜"));
	}
}
