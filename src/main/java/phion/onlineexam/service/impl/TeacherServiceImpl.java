package phion.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import phion.onlineexam.bean.Teacher;
import phion.onlineexam.bean.TeacherExample;
import phion.onlineexam.bean.TeacherExample.Criteria;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.dao.TeacherMapper;
import phion.onlineexam.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;
	
	public List<Teacher> queryTeacher(Teacher teacher) {
		List<Teacher> Teachers = teacherMapper.selectBySelective(teacher, null);
		return Teachers;
	}

	public void addTeacher(Teacher teacher) {
		teacherMapper.insert(teacher);
	}

	public void addTeachersBatch(List<Teacher> teachers) {
		// TODO Auto-generated method stub
		
	}

	public void updateTeacher(Teacher teacher) {
		teacherMapper.updateByPrimaryKey(teacher);
	}
	
	public void updateTeacherSelective(Teacher teacher) {
		teacherMapper.updateByPrimaryKeySelective(teacher);
	}

	public void deleteTeacher(Integer teaId) {
		teacherMapper.deleteByPrimaryKey(teaId);
	}
	

	@Override
	public int queryTeacherCount(Teacher Teacher) {
		return queryTeacher(Teacher).size();
	}

	@Override
	public Teacher queryTeacherById(Integer teaId) {
		return teacherMapper.selectByPrimaryKey(teaId);
	}

	@Override
	public void deleteTeacherBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andTeaIdIn(ids);
		teacherMapper.deleteByExample(example);
	}

}
