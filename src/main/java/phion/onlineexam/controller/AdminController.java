package phion.onlineexam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
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

import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.service.TeacherService;

@RequestMapping("")
@Controller
public class AdminController {

	@Autowired
	TeacherService teacherService;

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
	public Msg getTeachers(@RequestParam(value="pn",defaultValue="1")Integer pn) {
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
	
}
