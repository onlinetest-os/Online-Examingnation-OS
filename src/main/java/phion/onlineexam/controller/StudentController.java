package phion.onlineexam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.service.StudentService;
import phion.onlineexam.utils.DataChangeUtil;
import phion.onlineexam.utils.DateUtil;

@Controller
@RequestMapping("")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	ExamService examService;

	/**
	 * 学生登录
	 * @return
	 */
	@RequestMapping("/student_login")
	@ResponseBody
	public Msg login(@RequestParam(value="stuNumber",defaultValue="0")String stuNumber,
			String stuPassword,HttpServletRequest request){
		System.out.println(request.getRequestURI());
		System.out.println(stuNumber+"---"+stuPassword);
		System.out.println("StudentController被访问");
		Student studentLike = new Student(null,stuNumber,null,stuPassword,null,null,null);
		List<Student> students = studentService.queryStudent(studentLike);
		if(students.size()<=0) return Msg.fail();
		HttpSession session = request.getSession();
		System.out.println(students.get(0));
		//声明登录类型为学生，方便拦截器判断
		session.setAttribute("role", "student");
		session.setAttribute("student", students.get(0));
		return Msg.success().add("student", students.get(0));
	}
	
	/**
	 * 按条件查询学生
	 * @return
	 */
	@RequestMapping("/student_query")
	@ResponseBody
	public Msg query(Student student) {
		List<Student> students = studentService.queryStudent(student);
		if(students.size()<=0) return Msg.fail();
		return Msg.success().add("students", students);
	}
	
	/**
	 * 访问主页
	 * @return
	 */
	@RequestMapping("/student_main")
	public ModelAndView toMainPage(HttpSession session) {
//		Student student = (Student) session.getAttribute("student");
//		System.out.println(student);
//		if(student==null||student.getStuId()==null) return new ModelAndView("redirect:/");
		List<Exam> exams = examService.queryExam(new Exam(StaticResources.READY_TODAY_EXAM));
		List<Map<String , Object>> examsInfos = new ArrayList<Map<String , Object>>();
		//把简单的经过格式化处理的信息放到界面
		examsInfos = DataChangeUtil.getSimpleExams(exams);
		
		ModelAndView mav = new ModelAndView();
		if(exams.size()>0) {
			//mav.addObject("exams", exams);
			mav.addObject("examsInfos", examsInfos);
			session.setAttribute("exams", exams);
		}
		mav.setViewName("student/Student_main");
//		mav.addObject("student", student);
		return mav;
	}
	
	/**
	 * 访问考试详情
	 */
	@RequestMapping("/student_toExamDetail")
	public ModelAndView toExamDetail(HttpSession session,HttpServletRequest request) {
		//取出考试
		List<Exam> exams = (List<Exam>) session.getAttribute("exams");
		System.out.println(exams.size());
		if(exams!=null&&exams.size()>0) {
			Exam exam = exams.get(Integer.parseInt(request.getParameter("index")));
			ModelAndView mav = new ModelAndView();
			mav.addObject(exams);
			mav.setViewName("student/s_examPage");
			System.out.println(exam);
			return mav;
		}
		return null;
	}
}
