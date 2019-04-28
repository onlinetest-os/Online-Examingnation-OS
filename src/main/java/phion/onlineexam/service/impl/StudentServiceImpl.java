package phion.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.StudentExample;
import phion.onlineexam.bean.StudentExample.Criteria;
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
		studentMapper.insert(student);
		
	}

	public void addStudentsBatch(List<Student> students) {
		// TODO Auto-generated method stub
		
	}

	public void updateStudent(Student student) {
		studentMapper.updateByPrimaryKey(student);
		
	}

	public void deleteStudentById(Integer stuId) {
		studentMapper.deleteByPrimaryKey(stuId);
		
	}

	@Override
	public List<Student> queryStudentByEId(Integer eId) {
		return studentMapper.selectByEId(eId);
	}

	@Override
	public int queryStudentCount(Student student) {
		return queryStudent(student).size();
	}

	@Override
	public Student queryStudentById(Integer stuId) {
		return studentMapper.selectByPrimaryKey(stuId);
	}

	@Override
	public void deleteStudentBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andStuIdIn(ids);
		studentMapper.deleteByExample(example);
		
	}

	@Override
	public List<Student> queryStudentByEIdSelective(Integer eId, Student student) {
		return studentMapper.selectByEIdSelective(eId, student);
	}

	@Override
	public List<Student> queryStudentByEIdWithNotNullCommitinfo(Integer eId) {
		return studentMapper.selectByEIdWithNotNullCommitinfo(eId);
	}

	@Override
	public List<Student> queryStudentByEIdWithNullCommitinfo(Integer eId) {
		return studentMapper.selectByEIdWithNullCommitinfo(eId);
	}

	@Override
	public List<Student> queryStudentByEIdWithNotNullIp(Integer eId) {
		return studentMapper.selectByEIdWithNotNullIp(eId);
	}

	@Override
	public List<Student> queryStudentByEIdWithNullIp(Integer eId) {
		return studentMapper.selectByEIdWithNullIp(eId);
	}

}
