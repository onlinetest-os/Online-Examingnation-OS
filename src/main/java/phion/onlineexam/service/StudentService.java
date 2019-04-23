package phion.onlineexam.service;

import java.util.List;

import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;

public interface StudentService {
	
	/**
	 * 查询学生
	 * @return
	 */
	public List<Student> queryStudent(Student student);
	
	/**
	 * 根据学生id查询学生
	 * @return
	 */
	public Student queryStudentById(Integer stuId);
	
	/**
	 * 根据考试id查询学生
	 * @return
	 */
	public List<Student> queryStudentByEId(Integer eId);
	
	
	/**
	 * 解除学生ip锁定
	 * @param StuId
	 * @return
	 */
	public void removeIPLockFromStudent(Integer StuId);
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	public void addStudent(Student student);
	
	
	/**
	 * 批量添加学生
	 * @param students
	 */
	public void addStudentsBatch(List<Student> students);
	
	
	/**
	 * 更新学生信息
	 * @param student
	 */
	public void updateStudent(Student student);
	
	
	/**
	 * 根据主键删除学生
	 * @param stuId
	 */
	public void deleteStudentById(Integer stuId);
	
	/**
	 * 批量删除学生
	 * @param stuId
	 */
	public void deleteStudentBatch(List<Integer> ids);
	
	
	/**
	 * 查询学生数量
	 * @return
	 */
	public int queryStudentCount(Student student);
	
}
