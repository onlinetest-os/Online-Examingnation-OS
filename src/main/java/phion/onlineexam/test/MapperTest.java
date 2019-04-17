package phion.onlineexam.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.ExamExample;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.bean.TeacherExample;
import phion.onlineexam.dao.ExamMapper;
import phion.onlineexam.dao.StudentMapper;
import phion.onlineexam.dao.TeacherMapper;
import phion.onlineexam.service.impl.StudentServiceImpl;
import phion.onlineexam.utils.DateUtil;

/**
 * 测试dao层的工作
 * @author 15037
 * 使用Spring的项目可以使用Spring的单元测试，可以自动注入需要的组件
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	ExamMapper examMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	SqlSession sqlSession;

	/**
	 * 测试ExamMapper
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testExamMapperCrud() {
		/*	//1、创建SpringIOC容器
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2、从容器中获取mapper
		ExamMapper bean = ioc.getBean(ExamMapper.class);*/
		
		//System.out.println(examMapper);
		
		//考试插入测试
		LocalDateTime ld1 = DateUtil.getCurrentLocalDateTime().plusDays(1);
		LocalDateTime ld2 = ld1.plusHours(2);
		Date d1 = DateUtil.toDate(ld1);
		Date d2 = DateUtil.toDate(ld2);
		examMapper.insertSelective(new Exam(null, null,null, d1 , d2, null, null,StaticResources.READY_TODAY_EXAM,  null, null));
		examMapper.insertSelective(new Exam(null, null,null, d1 , d2, null, null, StaticResources.CREATING_EXAM, null, null));
		 
		
		/*//主键查询考试，带考试信息
		Exam exam = examMapper.selectByPrimaryKeyWithExamInfo(1);
		System.out.println(exam.toString());
		//主键查询所有考试，带考试信息
		List<Exam> exams = examMapper.selectByExampleWithExamInfo(null);
		for(Exam e : exams) {
			System.out.println(e);
		}*/
		
		/*//有选择的查询考试
		List<Exam> exams = examMapper.selectWithExamInfoSelective(new Exam(null, null,
				null, null, null, null, null,"creating",  null, null));
		for(Exam e : exams) {
			System.out.println(e);
		}*/
		/*//查询考试，附带学生
		Exam e = examMapper.selectByPrimaryKeyWithStudent(1);
		
		System.out.println(e.getStudents().size());*/
		
	}
	
	
	/**
	 * 测试StudentMapper
	 */
	//@Test
	public void testStudentMapperCrud() {
		/*StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		for(int i = 0 ; i < 1000 ; i++) {
			 mapper.insert(new Student(null, 1610120016+i+"", "学生"+i, 
					 "111", null, null, null));
		}
		System.out.println("批量完成！");*/
		//按条件查询学生
		/*List<Student> students = studentMapper.selectBySelective(new Student(null,null,null,"222",null,null,null), null);
		for(Student s : students) {
			System.out.println(s);
		}*/
	}
	
	/**
	 * 测试TeacherMapper
	 */
	//@Test
	public void testTeacherMapperCrud() {
		/*//插入教师
		for(int i = 0 ; i < 10 ; i++) {
			teacherMapper.insertSelective(new Teacher(null, 10000000+i+"", "教师"+i, (int)Math.random()*100000+"", null, null));
		}
		System.out.println("插入完成！");*/
		/*//按条件查询教师
		Teacher teacher = teacherMapper.selectBySelective(new Teacher(null, null, "教师"+6, "54das", null, null), null);
		System.out.println(teacher);*/
		//按条件更新教师
		//teacherMapper.updateByPrimaryKeySelective(new Teacher(1, null, "教师"+6, "ddddd", null, null));
		 
	}
}
