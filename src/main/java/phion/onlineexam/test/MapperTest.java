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
import phion.onlineexam.bean.ExamArrange;
import phion.onlineexam.bean.ExamExample;
import phion.onlineexam.bean.ExamInfo;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.bean.TeacherExample;
import phion.onlineexam.dao.ExamArrangeMapper;
import phion.onlineexam.dao.ExamInfoMapper;
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

	@Autowired
	ExamInfoMapper examInfoMapper;
	

	@Autowired
	ExamArrangeMapper examArrangeMapper;
	/**
	 * 测试ExamMapper
	 */
	@SuppressWarnings("deprecation")
	//@Test
	public void testExamMapperCrud() {
		/*	//1、创建SpringIOC容器
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2、从容器中获取mapper
		ExamMapper bean = ioc.getBean(ExamMapper.class);*/
		
		//System.out.println(examMapper);
		
		//考试插入测试
//		LocalDateTime ld1 = DateUtil.getCurrentLocalDateTime().plusDays(1);
//		LocalDateTime ld2 = ld1.plusHours(2);
//		Date d1 = DateUtil.toDate(ld1);
//		Date d2 = DateUtil.toDate(ld2);
//		examMapper.insertSelective(new Exam(null, null,null, d1 , d2, null, null,StaticResources.READY_TODAY_EXAM,  null, null));
//		examMapper.insertSelective(new Exam(null, null,null, d1 , d2, null, null, StaticResources.CREATING_EXAM, null, null));
		 
		
		//主键查询考试，带考试信息
		/*Exam exam = examMapper.selectByPrimaryKeyWithExamInfo(1);
		System.out.println(exam.toString());*/
		//主键查询所有考试，带考试信息
		List<Exam> exams = examMapper.selectByExampleWithExamInfo(null);
		for(Exam e : exams) {
			System.out.println(e);
		}
		
		/*//有选择的查询考试
		List<Exam> exams = examMapper.selectWithExamInfoSelective(new Exam(null, null,
				null, null, null, null, null,"creating",  null, null));
		for(Exam e : exams) {
			System.out.println(e);
		}*/
		/*//查询考试，附带学生
		Exam e = examMapper.selectByPrimaryKeyWithStudent(1);
		
		System.out.println(e.getStudents().size());*/
		/*
		List<Exam> exams = examMapper.selectByExample(null);
		for(Exam e : exams) {
			Date startDate = e.getStartTime();
			LocalDateTime localDate = DateUtil.toLocalDateTime(startDate);
			System.out.println(localDate);
		}*/
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
		/*List<Student> students = studentMapper.selectBySelective(new Student(null,null,null,null,null,"",null), null);
		for(Student s : students) {
			System.out.println(s);
		}*/
		//按考试id查询学生
		/*List<Student> students = studentMapper.selectByEId(1);
		for(Student s : students) {
			System.out.println(s);
		}*/
		
		//按考试id有选择的查询学生
		List<Student> students = studentMapper.selectByEIdWithNotNullCommitinfo(1);
		for(Student s : students) {
			System.out.println(s);
		}
	}
	
	/**
	 * 测试TeacherMapper
	 */
	//@Test
	public void testTeacherMapperCrud() {
		//插入教师
		for(int i = 10 ; i < 70 ; i++) {
			teacherMapper.insertSelective(new Teacher(null, 10000000+i+"", "教师"+i, "111", null, null));
		}
		System.out.println("插入完成！");
		//按条件查询教师
//		List<Teacher> teachers = teacherMapper.selectBySelective(new Teacher(), null);
//		for(Teacher teacher:teachers) {
//			System.out.println(teacher);
//		}
		
		//按条件更新教师
		//teacherMapper.updateByPrimaryKeySelective(new Teacher(1, null, "教师"+6, "ddddd", null, null));
		 
	}
	
	/**
	 * 测试ExamArrangeMapper
	 */
	 @Test
	 public void testExamArrangeMapperCrud() {
		//插入考试安排到考试2
		/*ExamArrangeMapper mapper = sqlSession.getMapper(ExamArrangeMapper.class);
		for(int i = 100 ; i < 200 ; i++) {
			 mapper.insert(new ExamArrange(null,10+i,2));
		}
		System.out.println("批量完成！");*/
		
		//查询考试安排
	/*	List<ExamArrange> arranges = examArrangeMapper.selectByExample(null);
		 for(ExamArrange arrange:arranges) {
			 System.out.println(arrange);
		 }*/
		
		/*List<ExamArrange> arranges = examArrangeMapper.selectByExampleSelective(new ExamArrange(null,1,1), null);
		 for(ExamArrange arrange:arranges) {
			 System.out.println(arrange);
		 }*/
		 
		//测试根据考试id删除
		 examArrangeMapper.deleteByEId(3);
		 System.out.println("删除完成！");
	 }
	
	/**
	 * 测试考试信息表的操作
	 */
	//@Test
	public void examInfoMapperTest() {
		//补全所有考试的考试信息
		ExamInfoMapper mapper = sqlSession.getMapper(ExamInfoMapper.class);
		/*List<Exam> exams = examMapper.selectByExample(null);
		for(Exam e:exams) {
			int eId = e.geteId();
			ExamInfo info = mapper.selectByExamID(eId);
			if(info==null) {
				mapper.insert(new ExamInfo(null,eId));
			}
		}
		System.out.println("插入完成！");*/
		/*ExamInfo info  = mapper.selectByExamID(19);
		System.out.println(info);*/

	}
	
	
	
	/**
	 * 执行一些数据库操作
	 */
	//@Test
	public void  mytest() {
		/*Exam exam = examMapper.selectByPrimaryKey(1);
		exam.setStatus(StaticResources.RUNNING_EXAM);
		examMapper.updateByPrimaryKey(exam);*/
		
	/*	List<Exam> nowExams = examMapper.selectWithExamInfoSelective
				(new Exam(StaticResources.RUNNING_EXAM));
		for(Exam exam:nowExams) {
			System.out.println(exam);
		}*/
		
		System.out.println(examInfoMapper.selectByExamID(1));
	}
}
