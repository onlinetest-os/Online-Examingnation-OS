package phion.onlineexam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.service.TeacherService;

@Controller
@RequestMapping("")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	/**
	 * 教师登录
	 * @return
	 */
	@RequestMapping("/teacher_login")
	@ResponseBody
	public Msg login(@RequestParam(value="teaNumber",defaultValue="0")String teaNumber,
			String teaPassword,HttpServletRequest request){
//		if(teaNumber==null||teaPassword==null) return Msg.fail();
		System.out.println(teaNumber+"---"+teaPassword);
		System.out.println("TeacherController被访问");
		
		Teacher teacherLike = new Teacher(null,teaNumber,null,teaPassword,null,null);
		List<Teacher> teachers = teacherService.queryTeacher(teacherLike);
		if(teachers.size()<=0) return Msg.fail();
		
		HttpSession session = request.getSession();
		System.out.println(teachers.get(0));
		//声明登录类型为教师，方便拦截器判断
		session.setAttribute("role", "teacher");
		session.setAttribute("teacher", teachers.get(0));
		return Msg.success().add("teacher", teachers.get(0));
	}
	
	/**
	 * 按条件查询教师
	 * @return
	 */
	@RequestMapping("/teacher_query")
	@ResponseBody
	public Msg query(Teacher teacher) {
		List<Teacher> teachers = teacherService.queryTeacher(teacher);
		if(teachers.size()<=0) return Msg.fail();
		return Msg.success().add("teachers", teachers);
	}
	
	/**
	 * 访问主页(考前）
	 * @return
	 */
	@RequestMapping("/teacher_main")
	public ModelAndView toMainPage(HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		System.out.println(teacher);
//		if(teacher==null||teacher.getTeaId()==null) return new ModelAndView("redirect:/");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teacher/Teacher_main");
//		mav.addObject("teacher", teacher);
		return mav;
	}
	
	/**
	 * 教师注销登录
	 */
	@RequestMapping("/teacher_cancel")
	public ModelAndView cancelLogin(HttpSession session) {
		ModelAndView mav =new ModelAndView();
		session.removeAttribute("role");
		session.removeAttribute("teacher");
		mav.setViewName("redirect:/index.jsp");
		return mav;
	}
	
	/**
	 * 新建考试
	 */
	@RequestMapping("/teacher_t_newExam")
	public String toPageNewExam() {
		return "teacher/t_newExam";
	}

	
	/*
	 *<li><a href="teacher_t_viewExam" target="main_right">查看考试</a></li>
	<li><a href="teacher_t_addStudent" target="main_right">添加学生</a></li>
	<li><a href="teacher_t_alertManage" target="main_right">通知管理</a></li>
	<li><a href="teacher_t_IPRelease" target="main_right">IP解绑</a></li>
	 */
	
	/**
	 * 考中
	 */
	@RequestMapping("/teacher_t_kaozhong")
	public String toPagekaozhong() {
		return "teacher/t_kaozhong";
	}
	
	/**
	 *查看考试 页面
	 */
	@RequestMapping("/teacher_t_viewExam")
	public String toPageViewExam() {
		return "teacher/t_viewExam";
	}
	
	/**
	 *添加学生页面
	 */
	@RequestMapping("/teacher_t_addStudent")
	public String toPageAddStudent() {
		return "teacher/t_addStudent";
	}
	/**
	 *通知管理页面
	 */
	@RequestMapping("/teacher_t_alertManage")
	public String toPageAlertManage() {
		return "teacher/t_alertManage";
	}
	
	/**
	 *IP解绑页面
	 */
	@RequestMapping("/teacher_t_IPRelease")
	public String toPageIPRelease() {
		return "teacher/t_IPRelease";
	}
	/*
	 * <li><a href="teacher_t_answerDownload" target="main_right">下载答案</a></li>
		<li><a href="teacher_t_examInfoExport" target="main_right">导出信息</a></li>
		<li><a href="teacher_t_examClean" target="main_right">清理考试</a></li>

	 */
	
	/**
	 * 考后
	 */
	@RequestMapping("/teacher_t_kaohou")
	public String toPagekaohou() {
		return "teacher/t_kaohou";
	}
	
	/**
	 *下载答案页面
	 */
	@RequestMapping("/teacher_t_answerDownload")
	public String toPageAnswerDownload() {
		return "teacher/t_answerDownload";
	}
	
	/**
	 *导出信息页面
	 */
	@RequestMapping("/teacher_t_examInfoExport")
	public String toPageExamInfoExport() {
		return "teacher/t_examInfoExport";	
	}
	
	
	
}
