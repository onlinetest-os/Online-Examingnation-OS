package phion.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.Student;
import phion.onlineexam.dao.StudentMapper;
import phion.onlineexam.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentMapper studentMapper;

	public List<Student> queryStudent(Student student) {
		List<Student> students = studentMapper.selectBySelective(student, null);
		return students;
	}

	public void removeIPLockFromStudent(Integer StuId) {
		// TODO Auto-generated method stub
		
	}

	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	public void addStudentsBatch(List<Student> students) {
		// TODO Auto-generated method stub
		
	}

	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	public void deleteStudent(Integer stuId) {
		// TODO Auto-generated method stub
		
	}

}
