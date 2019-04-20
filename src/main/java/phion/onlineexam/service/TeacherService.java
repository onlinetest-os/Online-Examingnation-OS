package phion.onlineexam.service;

import java.util.List;


import phion.onlineexam.bean.Teacher;

public interface TeacherService {
	
	/**
	 * 查询教师
	 * @return
	 */
	public List<Teacher> queryTeacher(Teacher teacher);
	
	/**
	 * 查询教师
	 * @return
	 */
	public Teacher queryTeacherById(Integer teaId);
	
	/**
	 * 查询教师数量
	 * @return
	 */
	public int queryTeacherCount(Teacher teacher);
	
	/**
	 * 添加教师
	 * @param Teacher
	 * @return
	 */
	public void addTeacher(Teacher teacher);
	
	
	/**
	 * 批量添加教师
	 * @param Teachers
	 */
	public void addTeachersBatch(List<Teacher> teachers);
	
	
	/**
	 * 更新教师信息
	 * @param Teacher
	 */
	public void updateTeacher(Teacher teacher);
	
	 
	/**
	 * 根据主键删除教师
	 * @param stuId
	 */
	public void deleteTeacher(Integer stuId);
	
	
	
}
