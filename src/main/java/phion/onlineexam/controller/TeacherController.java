package phion.onlineexam.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.service.TeacherService;
import phion.onlineexam.utils.DataChangeUtil;
import phion.onlineexam.utils.DateUtil;
import phion.onlineexam.utils.FileHelper;
import phion.onlineexam.utils.PathHelper;

@Controller
@RequestMapping("")
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	@Autowired
	ExamService examService;

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
	
	
	
	/**
	 * 草稿箱
	 */
	@RequestMapping("/teacher_t_draftBox")
	public String toPageDraftBox() {
		return "teacher/t_draftBox";
	}
	
	
	/**
	 * 所有考试
	 */
	@RequestMapping("/teacher_t_examListAll")
	public String toPageExamListAll(HttpSession session,Model model) {
		List<Exam> exams = examService.queryExam(null);
		List<Map<String , Object>> examsInfos = new ArrayList<Map<String , Object>>();
		//把简单的经过格式化处理的信息放到界面
		examsInfos = DataChangeUtil.getSimpleExams(exams);
		
		if(exams.size()>0) {
			//mav.addObject("exams", exams);
			model.addAttribute("examsInfos", examsInfos);
			model.addAttribute("exams", exams);
		}
		model.addAttribute("exams", exams);
		return "teacher/t_examListAll";
	}
	
	/**
	 * 我的考试
	 */
	@RequestMapping("/teacher_t_examListMine")
	public String toPageExamListMine() {
		return "teacher/t_examListMine";
	}
	
	/*
	 * <a class="btn" href="teacher_t_examEdit"><font class="font">编辑</font></a>
							<a class="btn" href="teacher_t_stuList"><font class="font">学生列表</font></a>
							
	 */
	
	/**
	 * 编辑考试
	 */
	@RequestMapping("/teacher_t_examEdit")
	public String toPageExamEdit() {
		return "teacher/t_examEdit";
	}
	
	/**
	 * 学生管理
	 */
	@RequestMapping("/teacher_t_stuList")
	public String toPageStuList(String eId,Model model) {
		//取出考试
		Exam exam = examService.selectByPrimaryKeyWithStudent
				(Integer.parseInt(eId));
		System.out.println(exam);
		List<Student> students = exam.getStudents();
		model.addAttribute("students",students);
		model.addAttribute("eName",exam.geteName());
		return "teacher/t_stuList";
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
	
	

	/**
	 * 其他事务
	 */
	@RequestMapping("/teacher_t_Others")
	public String toPageOthers() {
		return "teacher/t_Others";
	}
	
	
	
	/**
	 * 考试信息合理性检测
	 */
	@RequestMapping("/teacher_validate_exam")
	@ResponseBody
	public Msg validateExam(String startTimeStr,String endTimeStr) {
		//1、查询时间是否冲突，即是否所有考试均满足st>newSt
		//如新考试是8:00-10:00,就是合理的
		//*0:00-2:00***4:00-6:00********11:00-13:00******************
		//System.out.println(exam);
		Msg msg = new Msg();
//		LocalDateTime startTime = DateUtil.
//				getLocalDateTimeByDateString2(startTimeStr);
//		
//		LocalDateTime endTime = DateUtil.
//				getLocalDateTimeByDateString2(endTimeStr);
		String newStartTimeStr = startTimeStr+":00";
		String newEndTimeStr = endTimeStr+":00";
		int timeInterval = DateUtil.Minus(newStartTimeStr, newEndTimeStr);
		if(timeInterval<30&&timeInterval>240) {
			return Msg.fail().setMsg("考试时间有误！");
		}
		//查询出所有考试逐一比较
		/*List<Exam> exams = examService.queryExam(null);
		for(Exam e : exams) {
			String oldStartTimeStr = DateUtil.formateDate(e.getStartTime());
			String oldEndTimeStr = DateUtil.formateDate(e.getEndTime());
			int startInterval = DateUtil.Minus(newStartTimeStr, oldStartTimeStr);
			int endInterval = DateUtil.Minus(newEndTimeStr, oldEndTimeStr);
			if(!(startInterval>0&&endInterval>0||startInterval<0&&endInterval<0)) {
				return Msg.fail().setMsg("考试时间重叠,请查看考试安排！");
			}
		}*/
		//暂时搁置信息校验
		return Msg.success();
	}
	
	
	/**
	 * 保存考试
	 */
	@RequestMapping("/teacher_save_exam")
	@ResponseBody
	public Msg saveExam(MultipartFile paper,MultipartFile studentOrder,
			HttpServletRequest request) {
		System.out.println("------保存考试--------");
		// 获得原始文件名  
        String papaerName = paper.getOriginalFilename();  
        System.out.println("试卷名称:" + papaerName);
        String studentOrderName = studentOrder.getOriginalFilename();  
        System.out.println("学生名单名称:" + studentOrderName);
        
        //考试名称
        String eName = request.getParameter("eName");
        System.out.println(eName);
        //学生班级
        String stuClass = request.getParameter("stuClass");
        System.out.println(stuClass);
        
       //考试开启时间
        String startTimeString = request.getParameter("startTimeStr");
        LocalDateTime startTime = DateUtil.getLocalDateTimeByDateString2(startTimeString);
        System.out.println(startTime);
        //考试结束时间
        String endTimeString = request.getParameter("endTimeStr");
        LocalDateTime endTime = DateUtil.getLocalDateTimeByDateString2(endTimeString);   
        System.out.println(endTime);
        //教师id  
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        Integer teaId = teacher.getTeaId();
        System.out.println(teaId);
        //教师姓名
        String teaName = teacher.getTeaName();
        
        


    	//保存信息到数据库
    	Exam exam= new Exam(null,eName,teaId,DateUtil.toDate(startTime)
    			,DateUtil.toDate(endTime),null,null,
    			StaticResources.READY_EXAM,null,null);
    	examService.addExam(exam);
    	exam = examService.queryExamWithExamInfo(exam).get(0);
    	//考试id
    	Integer eId = exam.geteId();
    	//上传试卷
    	String paperPath = PathHelper.getPaperPath(eId,teaId,
    			teaName,stuClass,eName);
    	boolean result = FileHelper.upload(paper, request, paperPath);
		System.out.println("上传结果："+result);
    	String paparAnwserPath = PathHelper.getPaperAnwserPath(eId, 
    			teaId, teaName, stuClass, eName);
    			
    	//更新考试信息	
    	examService.updateExam(new Exam(eId,paperPath,paparAnwserPath));
        
    	//解析学生名单，并保存到学生表
        parseStudentOrder(studentOrder);
    
        return Msg.success().setMsg("创建考试成功！");
	}

	/**
	 * 解析文件，保存学生到数据库
	 * @param studentOrder
	 */
	public void parseStudentOrder(MultipartFile studentOrder) {
		
		
	}
	
	
}
