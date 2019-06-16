package phion.onlineexam.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import phion.onlineexam.bean.Config;
import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.ExamInfo;
import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.service.ExamArrangeService;
import phion.onlineexam.service.ExamService;
import phion.onlineexam.service.StudentService;
import phion.onlineexam.service.TeacherService;
import phion.onlineexam.utils.ConfigHelper;
import phion.onlineexam.utils.DataChangeUtil;
import phion.onlineexam.utils.FileHelper;

@RequestMapping("")
@Controller
public class AdminController {

	@Autowired
	TeacherService teacherService;
	
	@Autowired
	ExamService examService;

	@Autowired
	StudentService studentService;
	
	@Autowired
	ExamArrangeService examArrangeService;
	/**
	 * 管理员登录
	 * @return
	 */
	@RequestMapping("/admin_login")
	@ResponseBody
	public Msg login(@RequestParam(value="adminNumber",defaultValue="0")String adminNumber,
			String adminPassword,HttpServletRequest request){
//		if(adminNumber==null||adminPassword==null) return Msg.fail();
		System.out.println(adminNumber+"---"+adminPassword);
		System.out.println("TeacherController被访问");
		
		Teacher teacherLike = new Teacher(null,adminNumber,null,adminPassword,1,null);
		//System.out.println(teacherLike);
		List<Teacher> teachers = teacherService.queryTeacher(teacherLike);
		if(teachers.size()<=0) {
			//检查是否使用默认账号登录
			if(adminNumber.equals(StaticResources.ADMIN_NUMBER)
					&&adminPassword.equals(StaticResources.ADMIN_PASSWORD)) {
				if(exitAdmin()) return Msg.fail().setMsg("管理员已存在");
				HttpSession session = request.getSession();
				session.setAttribute("role", "admin");
				session.setAttribute("teacher", teacherLike);
				return Msg.success().add("teacher", teacherLike);
			}
			return Msg.fail();
		}
		
		HttpSession session = request.getSession();
		System.out.println(teachers.get(0));
		//声明登录类型为教师，方便拦截器判断
		session.setAttribute("role", "admin");
		session.setAttribute("teacher", teachers.get(0));
		return Msg.success().add("teacher", teachers.get(0));
	}
	
	
	/**
	 * 访问主页
	 * @return
	 */
	@RequestMapping("/admin_main")
	public ModelAndView toMainPage(HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		System.out.println(teacher);
//		if(teacher==null||teacher.getTeaId()==null) return new ModelAndView("redirect:/");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/Admin_main");
//		mav.addObject("teacher", teacher);
		return mav;
	}
	
	/**
	 * 管理员注销登录
	 */
	@RequestMapping("/admin_cancel")
	public ModelAndView cancelLogin(HttpSession session) {
		ModelAndView mav =new ModelAndView();
		session.removeAttribute("role");
		session.removeAttribute("teacher");
		mav.setViewName("redirect:/index.jsp");
		return mav;
	}
	
	/**
	 * 判断管理员是否已存在
	 */
	public boolean exitAdmin(){
		Teacher teacherLike = new Teacher(null,null,null,null,1,null);
		int count = teacherService.queryTeacherCount(teacherLike);
		return count>0?true:false;
	}
	
	/**
	 * 账户管理
	 */
	@RequestMapping("/admin_manage_account")
	public ModelAndView manageAccount() {
		ModelAndView mav =new ModelAndView();
		mav.setViewName("admin/a_accManage");
		return mav;
	}
	
	/**
	 * 查询教师
	 */
	@RequestMapping("/admin_get_teachers")
	@ResponseBody
	public Msg getTeachers(@RequestParam(value="pn",defaultValue="1")Integer pn
			,HttpServletRequest request) {
		System.out.println("访问查询教师");
		Map<String, Object> map = new HashMap<>();
		//这不是一个分页查询
		//引入Pagehelper
		//在查询之前只需要调用,传入页码，以及每页的大小
		
		
		Map config = ConfigHelper.getConfig(request);
		System.out.println(config);
		
		int spiltPageCount = config.get(StaticResources.SPILIT_PAGE_COUNT)==null?5:
			Integer.parseInt((String) config.get(StaticResources.SPILIT_PAGE_COUNT));
		//int spiltPageCount = 5;
		System.out.println("分页数："+spiltPageCount);
		
		PageHelper.startPage(pn,spiltPageCount);
		//startPage后面紧跟的查询就是一个分页查询
		List<Teacher> teachers = teacherService.queryTeacher(new Teacher());
		for(Teacher teacher:teachers) {
			System.out.println(teacher);
		}
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		
		PageInfo<Teacher> page = new PageInfo<Teacher>(teachers,spiltPageCount); 
		System.out.println("总页码："+page.getPages());
		System.out.println("总记录数："+page.getTotal());
		return Msg.success().add("pageInfo",page);
	}
	
	//分页查询教师
	public Map setTeachers(Integer pn) {
		System.out.println("访问查询教师");
		Map<String, Object> map = new HashMap<>();
		//这不是一个分页查询
		//引入Pagehelper
		//在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的查询就是一个分页查询
		List<Teacher> teachers = teacherService.queryTeacher(new Teacher());
		for(Teacher teacher:teachers) {
			System.out.println(teacher);
		}
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<Teacher> page = new PageInfo<Teacher>(teachers,5); 
		map.put("pageInfo",page);
		return map;
	}
	
	/**
	 * 根据工号检查教师是否合理
	 */
	@RequestMapping("/admin_check_teacher")
	@ResponseBody
	public Msg checkTeacher(@RequestParam(value="teaNumber",defaultValue="000")String teaNumber) {
		int count = teacherService.queryTeacherCount(new Teacher(null,teaNumber,null,null,null,null));
		if(count>0) return Msg.fail().add("va_msg", "该工号教师已存在！");
		return Msg.success();
	}
	
	/**
	 * 保存教师
	 */
	@RequestMapping("/admin_save_teacher")
	@ResponseBody
	public Msg saveTeacher(@Valid Teacher teacher,BindingResult result) {
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
			teacherService.addTeacher(teacher);
			return Msg.success();
		}
	}
	
	/**
	 * 根据id查询单个老师
	 */
	@RequestMapping("/admin_get_teacher")
	@ResponseBody
	public Msg getTeacher(@RequestParam(value="teaId",defaultValue="-1")Integer teaId) {
		//根据id查询单个老师
		Teacher teacher = teacherService.queryTeacherById(teaId);
		System.out.println("teacher:"+teacher);
		return Msg.success().add("teacher", teacher);
	}
	
	/**
	 * 更新教师信息
	 */
	@RequestMapping("/admin_update_teacher")
	@ResponseBody
	public Msg updateTeacher(Teacher teacher) {
		teacher.setTeaPassword(null);
		System.out.println("将要更新的员工数据："+teacher);
		teacherService.updateTeacherSelective(teacher);
		return Msg.success();
	}
	
	/**
	 * 删除教师
	 */
	@RequestMapping("/admin_delete_teacher")
	@ResponseBody
	public Msg updateTeacher(@RequestParam(value="teaId",defaultValue="-1")Integer teaId) {
		System.out.println("要删除的id是："+teaId);
		teacherService.deleteTeacher(teaId);;
		return Msg.success().setMsg("删除成功！");
	}
	
	/**
	 * 批量删除教师
	 */
	@RequestMapping("/admin_delete_teachers")
	@ResponseBody
	public Msg updateTeachers(@RequestParam(value="del_idstr",defaultValue="")String del_idstr) {
		System.out.println("要删除的id有："+del_idstr);
		if(del_idstr.length()==0) return Msg.fail().setMsg("没有任何需要删除的教师被选中！");
		
		List<Integer> del_ids = new ArrayList<Integer>();
		String[] str_ids = del_idstr.split("-");
		//组装id的集合
		for (String string : str_ids) {
			del_ids.add(Integer.parseInt(string));
		}
		teacherService.deleteTeacherBatch(del_ids);
		return Msg.success().setMsg("删除成功！");
	}
	
	/**
	 * admin_a_examClean
	 * 考试清理页面
	 */
	@RequestMapping("/admin_a_examClean")
	public String toPageCleanExam(Model model) {
		List<Exam> exams = examService.queryExamWithExamInfo(
				new Exam(StaticResources.COMPLETE_EXAM));
		
		List<Map<String, Object>> examsInfos = new ArrayList<Map<String , Object>>();

		//把简单的经过格式化处理的信息放到界面
		examsInfos = DataChangeUtil.getSimpleExams(exams);
		if(exams.size()>0) {
			model.addAttribute("examsInfos",examsInfos);
		}
		return "admin/a_examClean";
	}
	
	/**
	 * 清理考试
	 * 答案下载完成后（is_download为true），
	 * 清理包括答案与试卷，答案与试卷。
	 */
	@RequestMapping("admin_clean_exam")
	@ResponseBody
	public Msg clearExam(HttpServletRequest request,Integer eId) {
		System.out.println("管理员清理考试中...");
		Exam exam = examService.queryExamWithExamInfoByEId(eId);
		//1、判断是否已下载 
		//int isDownload = StaticResources.IS_NOT_DOWNLOAD;
		ExamInfo info = null;
		int isDownload = 0;
		if(exam!=null) {
			System.out.println(exam);
			info = exam.getExamInfo();
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
	 * 根据考试id删除所有学生及考试安排表的信息
	 */
	private void deleteStudentsWithEId(int eId) {
		Exam e = examService.selectByPrimaryKeyWithStudent(eId);
		if(e!=null) {
			//删除学生表学生
			List<Student> students = e.getStudents();
			List<Integer> ids = new ArrayList<>();
			for(Student s :students) ids.add(s.getStuId());
			if(ids.size()>0) {
				studentService.deleteStudentBatch(ids);
				System.out.println("删除之前的学生成功！");
			}	
		}
		//删除考试表信息
		examArrangeService.deleteExamArrangesByEId(eId);
		System.out.println("删除考试表信息成功！");
	}
	
	
	/**
	 * admin_a_config
	 * 全局配置信息
	 */
	@RequestMapping("/admin_a_config")
	public String toPageConfig(Model model,HttpServletRequest request) {
		Map configs = ConfigHelper.getConfig(request);
		model.addAllAttributes(configs);
		return "admin/a_config";
	}
	
	/**
	 * admin_save_configs
	 */
	@RequestMapping("admin_save_configs")
	@ResponseBody
	public Msg adminSaveConfigs(HttpServletRequest request,Config config) {
		System.out.println(config);
		Map<String,String> configMap = new HashMap<>();
		if(config.getAutoStartExam()!=null) 
			configMap.put(StaticResources.AUTO_START_EXAM,"true");
		else
			configMap.put(StaticResources.AUTO_START_EXAM,"false");
		
		if(config.getHaveDeletePower()!=null) 
			configMap.put(StaticResources.HAVE_DELETE_POWER,"true");
		else
			configMap.put(StaticResources.HAVE_DELETE_POWER,"false");
		
		if(config.getManualStartExamRange()!=null) 
			configMap.put(StaticResources.MANUAL_START_EXAM_RANGE,config.getManualStartExamRange());
		
		if(config.getMaxUploadSize()!=null) 
			configMap.put(StaticResources.MAX_UPLOAD_SIZE,config.getMaxUploadSize());
		
		if(config.getSpiltPageCount()!=null) 
			configMap.put(StaticResources.SPILIT_PAGE_COUNT,config.getSpiltPageCount());
		try {
			ConfigHelper.setConfig(request, configMap);
		} catch (IOException e) {
			e.printStackTrace();
			return Msg.fail().setMsg("修改设置失敗！");
		}
		return Msg.success().setMsg("修改设置成功！");
	}
	
}
