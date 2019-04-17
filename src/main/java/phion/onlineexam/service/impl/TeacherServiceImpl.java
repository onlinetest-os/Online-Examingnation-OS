package phion.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phion.onlineexam.bean.Teacher;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.dao.TeacherMapper;
import phion.onlineexam.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;
	
	public List<Teacher> queryTeacher(Teacher Teacher) {
		List<Teacher> Teachers = teacherMapper.selectBySelective(Teacher, null);
		return Teachers;
	}

	public void addTeacher(Teacher Teacher) {
		// TODO Auto-generated method stub
		
	}

	public void addTeachersBatch(List<Teacher> Teachers) {
		// TODO Auto-generated method stub
		
	}

	public void updateTeacher(Teacher Teacher) {
		// TODO Auto-generated method stub
		
	}

	public void deleteTeacher(Integer stuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int queryTeacherCount(Teacher Teacher) {
		return queryTeacher(Teacher).size();
	}

}
