package phion.onlineexam.service;

import java.util.List;


import phion.onlineexam.bean.Teacher;

public interface TeacherService {
	
	/**
	 * 查询教师
	 * @return
	 */
	public List<Teacher> queryTeacher(Teacher Teacher);
	
	/**
	 * 查询教师数量
	 * @return
	 */
	public int queryTeacherCount(Teacher Teacher);
	
	/**
	 * 添加教师
	 * @param Teacher
	 * @return
	 */
	public void addTeacher(Teacher Teacher);
	
	
	/**
	 * 批量添加教师
	 * @param Teachers
	 */
	public void addTeachersBatch(List<Teacher> Teachers);
	
	
	/**
	 * 更新教师信息
	 * @param Teacher
	 */
	public void updateTeacher(Teacher Teacher);
	
	 
	/**
	 * 根据主键删除教师
	 * @param stuId
	 */
	public void deleteTeacher(Integer stuId);
	
	
	
}
