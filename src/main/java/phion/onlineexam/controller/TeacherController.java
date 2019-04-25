package phion.onlineexam.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.ExamArrange;
import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.service.ExamArrangeService;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.service.StudentService;
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
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ExamArrangeService examArrangeService;

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
	public String toPageNewExam(HttpServletRequest request,Model model) {
		String type = request.getParameter("type");
		String eId = request.getParameter("eId");
		System.out.println("type:"+type);
		if(type.equals("edit")) {
			model.addAttribute("isEdit", true);
			model.addAttribute("eId",Integer.parseInt(eId));
		}else if(type.equals("new")) {
			//do nothing
		}
		return "teacher/t_newExam";
	}
	
	
	
	/**
	 * 草稿箱
	 */
	@RequestMapping("/teacher_t_draftBox")
	public String toPageDraftBox(Model model) {
		List<Exam> exams = examService.queryExamWithExamInfo(new Exam(StaticResources.CREATING_EXAM));
		model.addAttribute("exams",exams);
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
	public String toPageExamListMine(HttpSession session,Model model) {
		//1、查询该账号老师创建的考试
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		List<Exam> exams = examService.queryExamWithExamInfo(new Exam(null,teacher.getTeaId(),null));
		model.addAttribute("exams", exams);
		List<Map<String, Object>> examsInfos = DataChangeUtil.getSimpleExams(exams);
		model.addAttribute("examsInfos", examsInfos);
		return "teacher/t_examListMine";
	}
	
	/*
	 * <a class="btn" href="teacher_t_examEdit"><font class="font">编辑</font></a>
							<a class="btn" href="teacher_t_stuList"><font class="font">学生列表</font></a>
							
	 */
	

	
	/**
	 * 学生管理
	 */
	@RequestMapping("/teacher_t_stuList")
	public String toPageStuList(String eId,Model model) {
		//取出考试
		/*Exam exam = examService.selectByPrimaryKeyWithStudent
				(Integer.parseInt(eId));
		System.out.println(exam);
		List<Student> students = exam.getStudents();
		model.addAttribute("students",students);
		model.addAttribute("eName",exam.geteName());*/
		model.addAttribute("eId",eId);
		return "teacher/t_stuList";
	}
	/**
	 * 查询学生
	 * 此处计划直接获取考试所携带的学生
	 * 但由于创建考试功能尚未完善
	 * 暂时采用从学生表查询
	 */
	@RequestMapping("/teacher_get_students")
	@ResponseBody
	public Msg getStudents(@RequestParam(value="pn",defaultValue="1")Integer pn,
			Integer eId) {
		System.out.println("访问查询学生");
		//Exam exam = examService.selectByPrimaryKeyWithStudent(eId);
		Map<String, Object> map = new HashMap<>();
		//这不是一个分页查询
		//引入Pagehelper
		//在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的查询就是一个分页查询
		List<Student> students = studentService.queryStudentByEId(eId);
		for(Student student:students) {
			System.out.println(student);
		}
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<Student> page = new PageInfo<Student>(students,5); 
		System.out.println("总页码："+page.getPages());
		System.out.println("总记录数："+page.getTotal());
		return Msg.success().add("pageInfo",page);
	}
	
	/**
	 * 校验学号
	 */
	@RequestMapping("/teacher_check_student")
	@ResponseBody
	public Msg checkStudent(@RequestParam(value="stuNumber",defaultValue="000")String stuNumber) {
		int count = studentService.queryStudentCount(new Student(stuNumber,null,null,null));
		if(count>0) return Msg.fail().add("va_msg", "该学号学生已存在！");
		return Msg.success();
	}
	
	/**
	 * teacher_save_student
	 * 保存学生
	 */
	@RequestMapping("/teacher_save_student")
	@ResponseBody
	public Msg saveStudent(@Valid Student student,BindingResult result,Integer eId) {
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			studentService.addStudent(student);
			int stuId = studentService.queryStudent(student).get(0).getStuId();
			examArrangeService.addExamArrange(new ExamArrange(null,stuId,eId));
			return Msg.success();
		}
	}
	
	/**
	 * 根据id查询单个学生
	 */
	@RequestMapping("/teacher_get_student")
	@ResponseBody
	public Msg getStudent(@RequestParam(value="stuId",defaultValue="-1")Integer stuId) {
		//根据id查询单个学生
		Student student = studentService.queryStudentById(stuId);
		System.out.println("student:"+student);
		return Msg.success().add("student", student);
	}
	
	/**
	 * 更新学生信息
	 */
	@RequestMapping("/teacher_update_student")
	@ResponseBody
	public Msg updateStudent(Student student) {
		System.out.println("将要更新的员工数据："+student);
		studentService.updateStudent(student);
		return Msg.success();
	}
	
	/**
	 * 删除学生
	 */
	@RequestMapping("/teacher_delete_student")
	@ResponseBody
	public Msg updateStudent(@RequestParam(value="stuId",defaultValue="-1")Integer stuId) {
		System.out.println("要删除的id是："+stuId);
		studentService.deleteStudentById(stuId);;
		return Msg.success().setMsg("删除成功！");
	}
	
	/**
	 * 批量删除学生
	 */
	@RequestMapping("/teacher_delete_students")
	@ResponseBody
	public Msg updateStudents(@RequestParam(value="del_idstr",defaultValue="")String del_idstr) {
		System.out.println("要删除的id有："+del_idstr);
		if(del_idstr.length()==0) return Msg.fail().setMsg("没有任何需要删除的学生被选中！");
		
		List<Integer> del_ids = new ArrayList<Integer>();
		String[] str_ids = del_idstr.split("-");
		//组装id的集合
		for (String string : str_ids) {
			del_ids.add(Integer.parseInt(string));
		}
		studentService.deleteStudentBatch(del_ids);
		return Msg.success().setMsg("删除成功！");
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
	
	/**
	 * teacher_release_student_ip
	 * 
	 */
	@RequestMapping("/teacher_release_student_ip")
	@ResponseBody
	public Msg releaseIP(@RequestParam(value="stuId",defaultValue="-1")Integer stuId) {
		if(stuId==-1) return Msg.fail();
		Student student = studentService.queryStudentById(stuId);
		student.setIp(null);
		studentService.updateStudent(student);
		return Msg.success().setMsg("ip已解绑！");
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
        
        String isEdit = request.getParameter("isEdit");
        System.out.println("isEdit:"+isEdit);

    	//保存信息到数据库
    	Exam exam= new Exam(null,eName,teaId,DateUtil.toDate(startTime)
    			,DateUtil.toDate(endTime),null,null,
    			StaticResources.READY_EXAM,null,null);
    	if(!isEdit.equals("true")) {
    		examService.addExam(exam);
    		exam = examService.queryExamWithExamInfo(exam).get(0);
    	}else {
    		String eIdStr = request.getParameter("eId");
    		Integer eId = Integer.parseInt(eIdStr);
    		System.out.println(eId);
    		exam.seteId(eId);
    		examService.updateExam(exam);
    	}
    	//考试id,创建考试时要先插入数据库才会自动生成id
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
