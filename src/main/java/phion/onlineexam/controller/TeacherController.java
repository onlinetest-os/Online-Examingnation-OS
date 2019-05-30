package phion.onlineexam.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.transform.sax.SAXTransformerFactory;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
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
import phion.onlineexam.bean.ExamInfo;
import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.dao.StudentMapper;
import phion.onlineexam.service.ExamArrangeService;
import phion.onlineexam.service.ExamInfoService;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.service.StudentService;
import phion.onlineexam.service.TeacherService;
import phion.onlineexam.utils.DataChangeUtil;
import phion.onlineexam.utils.DateUtil;
import phion.onlineexam.utils.ExcelHelper;
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
	
	@Autowired
	ExamInfoService examInfoService;

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
	 * 开启考试
	 */
	public void openExam() {
		TaskController.getInstance().updateExamBegin(examService);
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
			Integer eId,HttpServletRequest request) {
		System.out.println("访问查询学生");
		//Exam exam = examService.selectByPrimaryKeyWithStudent(eId);
		Map<String, Object> map = new HashMap<>();
		//这不是一个分页查询
		//引入Pagehelper
		//在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn,5);
		
		String type = request.getParameter("type");
		if(type==null) {
			//默认为查询全部
			type = "all";
		}
		List<Student> students =null;
		
		//startPage后面紧跟的查询就是一个分页查询
		//此处判断查询类型
		switch(type) {
			case "notSubmited":
				System.out.println("notSubmited:"+type);
				students = studentService.queryStudentByEIdWithNullCommitinfo(eId);
				break;
			case "submited":
				System.out.println("submited:"+type);
				students = studentService.queryStudentByEIdWithNotNullCommitinfo(eId);
				break;
			case "online":
				System.out.println("online:"+type);
				students = studentService.queryStudentByEIdWithNotNullIp(eId);
				break;
			case "outline":
				System.out.println("outline:"+type);
				students = studentService.queryStudentByEIdWithNullIp(eId);
				break;
			default:
				students = studentService.queryStudentByEId(eId);
		}
		System.out.println("type:"+type);
		System.out.println("eId:"+eId);
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
	 *教师可在考试期间查看考试情况，
	 *包括应考试学生数，
	 *已登录学生数和未登录学生数，及详细情况。
	 *已提交和未提交学生数及详细情况。
	 */
	@RequestMapping("/teacher_t_viewExam")
	public String toPageViewExam(Integer teaId,Model model) {
		//传入的信息
		Map<String ,Object> result = new HashMap<>();
		
		//考试Id
		Integer eId;
		
		//查询当前考试
		List<Exam> exams = examService.queryExamWithExamInfo
				(new Exam(null,teaId,StaticResources.RUNNING_EXAM));
		if(exams.size()<=0) {
			result.put("allNum", "");
			model.addAllAttributes(result);
			return "teacher/t_viewExam";
		}
		eId = exams.get(0).geteId();
		
		//学生总数
		int allNum = 0;
		//在线人数
		int onlineNum = 0;
		//提交人数
		int submitNum = 0;
		
		//本场考试全体考生
		Exam exam = examService.selectByPrimaryKeyWithStudent(eId);
		List<Student> students = exam.getStudents();
		
		//考试信息
		ExamInfo info = examInfoService.queryExamInfoByeId(eId);
		allNum = info.getAllNumber();
		
		//如果考试信息表未记录，则更新考试信息表
		if(allNum == 0) {
			allNum = students.size();
			//更新
			info.setAllNumber(allNum);
			examInfoService.updateExamBySelective(info);
		}
		
		//查询在线人数与提交人数
		for(Student s:students) {
			if(s.getIp()!=null)
				onlineNum++;
			if(s.getCommitinfo()!=null)
				submitNum++;
		}
		
		result.put("allNum", allNum);
		result.put("onlineNum", onlineNum);
		result.put("submitNum", submitNum);
		result.put("eId", eId);
		//返回给视图
		model.addAllAttributes(result);
		
		return "teacher/t_viewExam";
	}
	/**
	 * teacher_start_exam
	 * 开始考试
	 */
	@RequestMapping("teacher_start_exam")
	@ResponseBody
	public Msg startExam(Integer eId) {
		Exam exam = examService.queryById(eId);
		
		//设置状态为开始考试
		exam.setStatus(StaticResources.RUNNING_EXAM);
		examService.updateExam(exam);
		
		return Msg.success().setMsg("考试开始成功！");
	}
	
	
	/**
	 * teacher_end_exam
	 * 结束考试
	 */
	@RequestMapping("teacher_end_exam")
	@ResponseBody
	public Msg endExam(Integer eId) {
		Exam exam = examService.queryById(eId);
		
		//设置状态为考完考试
		exam.setStatus(StaticResources.COMPLETE_EXAM);
		examService.updateExam(exam);
		
		return Msg.success().setMsg("考试结束成功！");
	}
	
	/**
	 * teacher_showLoginOrder
	 * 查看登录名单
	 */
	@RequestMapping("teacher_showLoginOrder")
	public String toPageShowLoginOrder(Integer eId,Model model) {
		model.addAttribute("eId",eId );
		System.out.println(eId);
		return "teacher/t_showLoginOrder";
	}
	
	
	/**
	 * teacher_showLoginOrder
	 * 查看提交名单
	 */
	@RequestMapping("teacher_showSubmitOrder")
	public String toPageShowSubmitOrder(Integer eId,Model model) {
		model.addAttribute("eId",eId );
		System.out.println(eId);
		return "teacher/t_showSubmitOrder";
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
	public String toPageAnswerDownload(HttpSession session,Model model) {
		System.out.println("下载答案页面");
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		int teaId = teacher.getTeaId();
		Exam parse = new Exam(null,teaId,StaticResources.COMPLETE_EXAM);
		List<Exam> exams = examService.queryExamWithExamInfo(parse);
		System.out.println("教师id"+teaId);
		System.out.println(exams);
		model.addAttribute("exams", exams);
		return "teacher/t_answerDownload";
	}
	
	/**
	 * 下载学生提交的答案
	 */
	@RequestMapping("/teacher_t_getAnwsers")
	public void getAnwsers(int eId,HttpServletResponse response,
			HttpServletRequest request) {
		Exam exam = examService.queryById(eId);
		String folderPath = exam.getPaperAnwserPath();
		try {
			FileHelper.downloadZip(request, response, folderPath);
		} catch (IOException e) {
			System.out.println("下载学生答案失败！");
			e.printStackTrace();
		}
		System.out.println("下载学生答案成功！");
	}
	
	
	/**
	 *导出信息页面
	 */
	@RequestMapping("/teacher_t_examInfoExport")
	public String toPageExamInfoExport() {
		return "teacher/t_examInfoExport";	
	}
	
	
	/**
	 * 清理考试
	 * 答案下载完成后（is_download为true），
	 * 教师如果有权限，可以清理考试，
	 * 清理包括答案与试卷，答案与试卷。
	 */
	@RequestMapping("teacher_clear_exam")
	@ResponseBody
	public Msg clearExam(HttpServletRequest request,Integer eId) {
		System.out.println("清理考试中...");
		Exam exam = examService.queryExamWithExamInfoByEId(eId);
		//1、判断是否已下载 
		int isDownload = StaticResources.IS_NOT_DOWNLOAD;
		ExamInfo info = null;
		if(exam.getExamInfo()!=null) {
			info = exam.getExamInfo();
			System.out.println(eId);
			System.out.println(info);
			isDownload = info.getIsDownload();
			if(isDownload==StaticResources.IS_NOT_DOWNLOAD)
				return Msg.fail().setMsg("考试还未下载，不能清理！");
		}
		//2、判断是否有权限清理
		//读取配置文件
		boolean havePower = Boolean.TRUE;
		if(!havePower) return Msg.fail().setMsg("没有权限删除，请联系管理员！");
		
		//3、清理答案与试卷
		String paperAnwserPath = exam.getPaperAnwserPath();
		String paperPath = exam.getPaperPath();
		System.out.println("答案路径："+paperAnwserPath);
		System.out.println("试卷路径："+paperPath);
		
		FileHelper.deleteFile(request, paperAnwserPath);
		FileHelper.deleteFile(request, paperPath);
		
		//4、清理考试安排表，学生表
		deleteStudentsWithEId(eId);
		
		return Msg.success().setMsg("清理完成！");
	}
	
	
	/**
	 * 删除考试
	 */
	
	

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
	 * 1、保存考试，包括考试表的信息，
	 * 2、同时插入学生信息到学生表与考试安排表
	 * 3、创建于考试表对应的考试信息表记录
	 */
	@RequestMapping("/teacher_save_exam")
	@ResponseBody
	public Msg saveExam(MultipartFile paper,MultipartFile studentOrder,
			HttpServletRequest request) {
		System.out.println("------保存考试--------");
		// 获得原始文件名  
        String paperName = paper.getOriginalFilename();  
        System.out.println("试卷名称:" + paperName);
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

    	if(!isEdit.equals("true")) {
    		System.out.println(StaticResources.TEACHERLOG+"新建考试！");
    		//新建考试
        	Exam exam= new Exam(null,eName,teaId,DateUtil.toDate(startTime)
        			,DateUtil.toDate(endTime),null,null,
        			StaticResources.READY_EXAM,null,null);
    		examService.addExam(exam);
    		exam = examService.queryExamWithExamInfo(exam).get(0);
    		
    		//考试id,创建考试时要先插入数据库才会自动生成id
        	Integer eId = exam.geteId();
        	
        	//如果有试卷
        	if(!paperName.equals("")) {
        		
        		//上传试卷
            	String paperPath = PathHelper.getPaperPath(eId,teaId,
            			teaName,stuClass,eName);
            	boolean result = FileHelper.upload(paper, request, paperPath,StaticResources.NEW_FILE_NAME);
        		System.out.println("上传结果："+result);
            	String paparAnwserPath = PathHelper.getPaperAnwserPath(eId, 
            			teaId, teaName, stuClass, eName);
        		//更新考试信息	
            	examService.updateExam(new Exam(eId,paperPath,paparAnwserPath));
        	}	
        	
        	ExamInfo info = examInfoService.queryExamInfoByeId(eId);
        	if(info==null) {
        		//生成考试安排表记录
        		examInfoService.addExamInfo(new ExamInfo(null,eId));
        		System.out.println(StaticResources.TEACHERLOG+"生成考试info！");
        	}else {
        		System.out.println(StaticResources.TEACHERLOG+info);
        	}
        	
        	//如果有学生名单
        	if(!studentOrderName.equals("")) {
        		//解析学生名单，并保存到学生表，考试安排表
                parseStudentOrder(studentOrder,eId);
        	}
        	
        	//更新启动考试
       	 	openExam();
        	return Msg.success().setMsg("创建考试成功！");
    	}else {
    		System.out.println(StaticResources.TEACHERLOG+"更新考试！");
    		String eIdStr = request.getParameter("eId");
    		Integer eId = Integer.parseInt(eIdStr);
    		System.out.println(eId);
    		
    		//更新考试
    		Exam exam = examService.queryById(eId);
    		eName = exam.geteName();
    		
    		if(!paperName.equals("")) {
    			//删除原来的试卷
    			
    			
    			//上传试卷
            	String paperPath = PathHelper.getPaperPath(eId,teaId,
            			teaName,stuClass,eName);
            	boolean result = FileHelper.upload(paper, request, paperPath,StaticResources.NEW_FILE_NAME);
        		System.out.println("上传结果："+result);
            	String paparAnwserPath = PathHelper.getPaperAnwserPath(eId, 
            			teaId, teaName, stuClass, eName);
    			
        		//更新试卷	
            	examService.updateExam(new Exam(eId,paperPath,paparAnwserPath));

    		}
    		
    		ExamInfo info = examInfoService.queryExamInfoByeId(eId);
        	if(info==null) {
        		//生成考试信息表记录
        		examInfoService.addExamInfo(new ExamInfo(null,eId));
        		System.out.println(StaticResources.TEACHERLOG+"生成考试info！");
        	}else {
        		System.out.println(StaticResources.TEACHERLOG+info);
        	}
    			
    		//如果有学生名单
    		if(!studentOrderName.equals("")) {
    			//删除本场考试原来的学生信息
    			deleteStudentsWithEId(eId);
    			
        		//解析学生名单，并保存到学生表，考试安排表
                parseStudentOrder(studentOrder,eId);
                
        	}
    		//更新启动考试
       	 	openExam();
    		return Msg.success().setMsg("更新考试成功！");
    	}
    	
         
        
	}

	/**
	 * 根据考试id删除所有学生及考试安排表的信息
	 */
	private void deleteStudentsWithEId(int eId) {
		//删除学生表学生
		List<Student> students = studentService.queryStudentByEId(eId);
		List<Integer> ids = new ArrayList<>();
		for(Student s :students) ids.add(s.getStuId());
		if(ids.size()>0) {
			studentService.deleteStudentBatch(ids);
			System.out.println("删除之前的学生成功！");
		}		
		//删除考试表信息
		examArrangeService.deleteExamArrangesByEId(eId);
		System.out.println("删除考试表信息成功！");
		
	}

	/**
	 * 解析文件，保存学生到数据库
	 * @param studentOrder
	 */
	public void parseStudentOrder(MultipartFile studentOrder,int eId) {
		List<Student> students = new ArrayList<Student>();
		
		//读入excel,读取学生信息
		try {
			students = ExcelHelper.getDataAsStudent(FileHelper.M2F(studentOrder));
		} catch (IOException e) {
			System.out.println(StaticResources.TEACHERLOG+"读取学生excel出错！");
			e.printStackTrace();
		}
		
		if(students.size() == 0) return;
		
		List<Integer> stuIds = new ArrayList<>();
		
		//插入学生表
		studentService.addStudentsBatch(students);
		//插入之后学生才有id
		for(Student s: students) {
			stuIds.add(studentService.queryStudent(s).get(0).getStuId());
		} 
		
		//更新考试安排表
		List<ExamArrange> examArranges = new ArrayList<>();
		for(Integer stuId: stuIds)
			examArranges.add(new ExamArrange(null,stuId,eId));
		examArrangeService.addExamArrangeBatch(examArranges);
		
	}
	
}
