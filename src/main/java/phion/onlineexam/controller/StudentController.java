package phion.onlineexam.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.util.SheetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.ExamArrange;
import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.service.ExamArrangeService;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.service.StudentService;
import phion.onlineexam.service.TeacherService;
import phion.onlineexam.utils.DataChangeUtil;
import phion.onlineexam.utils.DateUtil;
import phion.onlineexam.utils.FileHelper;
import phion.onlineexam.utils.IPHelper;
import phion.onlineexam.utils.PathHelper;

@Controller
@RequestMapping("")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	ExamService examService;
	
	@Autowired
	ExamArrangeService examArrangeService;
	
	

	/**
	 * 学生登录
	 * @return
	 */
	@RequestMapping("/student_login")
	@ResponseBody
	public Msg login(@RequestParam(value="stuNumber",defaultValue="0")String stuNumber,
			String stuName,HttpServletRequest request){
		System.out.println(request.getRequestURI());
		System.out.println(stuNumber+"---"+stuName);
		System.out.println("StudentController被访问");
		//只要学生在数据库纯在即可登录
		Student studentLike = new Student(null,stuNumber,stuName,null,null,null,null);
		List<Student> students = studentService.queryStudent(studentLike);
		//校验信息
		if(students.size()<=0) return Msg.fail().setMsg("姓名错误或学号错误!");
		Student student = students.get(0); 
		System.out.println(student);
		//如果ip不为空，则比较当前ip
		if(student.getIp()!=null&&!student.getIp().equals("")) {
			String ip = IPHelper.getRemoteHost(request);
			if(!ip.equals(student.getIp())) 
				return Msg.fail().setMsg("ip已锁定，请联系教师！");
		} 
		
		//设置ip
		student.setIp(IPHelper.getRemoteHost(request));
		//ip绑定
		studentService.updateStudent(student);
		
		HttpSession session = request.getSession();
		System.out.println(student);
		
		//声明登录类型为学生，方便拦截器判断
		session.setAttribute("role", "student");
		session.setAttribute("student", student);
		return Msg.success().add("student", student);
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
		List<Exam> exams = examService.queryExamWithExamInfo(new Exam(StaticResources.READY_TODAY_EXAM));
		List<Exam> nowExams = examService.queryExamWithExamInfo(new Exam(StaticResources.RUNNING_EXAM));
		List<Map<String , Object>> examsInfos = new ArrayList<Map<String , Object>>();
		
		//把简单的经过格式化处理的信息放到界面
		examsInfos = DataChangeUtil.getSimpleExams(exams);
		
		//加入正在进行的考试
		examsInfos.addAll(DataChangeUtil.getSimpleExams(nowExams));
		
		
		ModelAndView mav = new ModelAndView();
		if(examsInfos.size()>0) {
			//mav.addObject("exams", exams);
			mav.addObject("examsInfos", examsInfos);
			session.setAttribute("exams", exams);
		}
		mav.setViewName("student/Student_main");
//		mav.addObject("student", student);
		return mav;
	}
	
	/**如果考试id和学生id在考试安排表中有记录，
	 * 学生即可查看考试详情
	 * 如果
	 * 访问考试详情
	 */
	@RequestMapping("/student_begin_exam")
	public ModelAndView toExamBegin(Integer eId,Integer stuId) {
		System.out.println("访问考试详情！");
		ModelAndView mav = new ModelAndView();
		//查询已经开始的考试
		Exam exam = examService.queryById(eId);
		//System.out.println(exam);
		if(!exam.getStatus().equals(StaticResources.RUNNING_EXAM)) {
			mav.setViewName("student/s_examBegin");
			return mav;
		}
		
		int count = examArrangeService.queryExamArrangeCount(new ExamArrange(null,stuId,eId));
		
		if(count>0) {
			System.out.println(exam);
			mav.addObject("exam",exam);
			Date date = exam.getStartTime();
			LocalTime startTime = DateUtil.toLocalDateTime(date).toLocalTime();
			date = exam.getEndTime();
			LocalTime endTime = DateUtil.toLocalDateTime(date).toLocalTime();
			mav.addObject("startTime", startTime);
			mav.addObject("endTime", endTime);
		}
		mav.setViewName("student/s_examBegin");
		return mav;
	}
	
	/**
	 * student_s_anwserUpload
	 * 上传考试答案頁面
	 * 
	 */
	@RequestMapping("/student_s_anwserUpload")
	public String toPageAnwserUpload(Integer eId,Integer stuId,Model model) {
		Student student = studentService.queryStudentById(stuId);
		model.addAttribute("student",student);
		model.addAttribute("eId",eId);
		System.out.println("eId:"+eId);
		return "student/s_anwserUpload";
	}

	/**
	 * student_uploadAnwser
	 * 上传考试答案
	 */
	@RequestMapping("/student_uploadAnwser")
	@ResponseBody
	public Msg upload(@RequestParam("file")MultipartFile file,HttpServletRequest request,int stuId) {
		if(file==null) return Msg.fail().setMsg("没有任何文件上传！");
		System.out.println("上传文件名："+file.getOriginalFilename());
		MultipartFile anwserFile = file;
		String fileName = file.getOriginalFilename();
		
        //学生班级
        //String stuClass = request.getParameter("stuClass");
        //System.out.println(stuClass);
        
        //学号
        String stuNumber = request.getParameter("stuNumber");
        System.out.println(stuNumber);
        //学生姓名
        String stuName = request.getParameter("stuName");
        System.out.println(stuName);
        //考试Id
        Integer eId = Integer.parseInt(request.getParameter("eId"));
        System.out.println(eId);
        Exam exam = examService.queryById(eId);
        System.out.println(exam);
        //(exam==null) return Msg.fail();
        //考试名称
        //String eName = exam.geteName();
        //System.out.println(eName);
        
        //教师id  
        //Integer teaId = exam.getTeaId();
        //System.out.println(teaId);
        //教师姓名
       // String teaName = teacherService.queryTeacherById(teaId).getTeaName();
        
		//获取考生文件路径
		String studentAnwserPath = PathHelper.getStudentPaperAnwserPath(stuNumber, stuName);
		//获取考试答案路径
		//String anwserPath = PathHelper.getPaperAnwserPath(eId, teaId, teaName, stuClass, eName);
		String papaerAnwserPath = exam.getPaperAnwserPath();
		
		System.out.println(studentAnwserPath);
		System.out.println(papaerAnwserPath);
		
		
		//上传路径
		String path = new StringBuilder().append(papaerAnwserPath)
										.append(studentAnwserPath)
										.toString();
		
		FileHelper.upload(anwserFile, request, path,null);
		Student s = studentService.queryStudentById(stuId);
		s.setCommitinfo(LocalTime.now()+" "+fileName);
		studentService.updateStudent(s);
		return Msg.success().setMsg("上传成功！");
	}
	
	/**
	 * student_s_paperDownload
	 * 下载试卷
	 * 此处应有学生信息及考试信息校验，判断是否能下载试卷
	 */
	@RequestMapping("/student_s_paperDownload")
	public void download(Integer eId,HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("StudentControllor:进入文件下载！");
		Exam exam = examService.selectByPrimaryKey(eId);
		String paperPath = exam.getPaperPath();
		String fileName = StaticResources.NEW_FILE_NAME;
		
		try {
			Msg msg = FileHelper.download(request, response, paperPath,fileName);
			if(msg.getCode()==StaticResources.FAIL_CODE) {
				response.getWriter().println(FileHelper.getUTF8String("试卷还未上传，稍后请重试！"));
			}
			//response.getWriter().println("hello world!");
		} catch (Exception e) {
			System.out.println("StudentControllor:下载出错！");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * student_s_anwserRecord
	 * 查看提交记录
	 */
	@RequestMapping("/student_s_anwserRecord")
	public String anwserRecord(Model model,int stuId) {
		Student s = studentService.queryStudentById(stuId);
		if(s.getCommitinfo()!=null) {
			String[] commitInfo = s.getCommitinfo().split(" ");
			model.addAttribute("time",commitInfo[0]);
			model.addAttribute("fileName",commitInfo[1]);
		}
		return "student/s_anwserRecord";
	}
	
}







