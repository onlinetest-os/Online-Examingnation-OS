package phion.onlineexam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import phion.onlineexam.bean.StaticResources;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.Teacher;

/**
 * 登录拦截器，返回false则不在继续访问其他拦截器
 * @author 15037
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//////System.out.println("调用LoginInterceptor-preHandle");
		HttpSession session = request.getSession();
		// 从session中获取登录类型
		String role = (String) session.getAttribute("role");
		// 如果没有类型信息，则重定向到登录界面
		if (role == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		//获取访问路径
		String uri = request.getRequestURI();
		//////System.out.println("uri:"+uri);
		//////System.out.println(role);
		
		//类型截取
		String[] strs = uri.split("/");
		/*
		 * 第二个即为访问路径类型，有两种
		 *第一种：/ExamOnline/student_main
		 *第二种：/ExamOnline/student/login.jsp
		 *我们需要得到"student"用来判断type
		 */
		String type = strs[2];
		//如果路径为第一种，需要将其切割
		if(type.contains("_")) {
			type = type.split("_")[0];
		}
		////System.out.println("type:"+type);
		//每一种用户只能访问自己对应的界面
		switch(type) {
			case "student": {
				////System.out.println("case student");
				//如果是学生（role）访问学生相关界面（type）
				if(role.equals(type)) {
					return checkStudentInfo(request, response);
				}
				//String resStr = JsonUtil.toJson(new Msg().fail().setMsg(new String("没有访问权限！".getBytes(),"utf8")));
				//response.getWriter().append(resStr);
				//response.getWriter().append(new String("没有访问权限！".getBytes(),"UTF-8"));
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return false;
			}
			case "teacher": {
				////System.out.println("case teacher");
				//如果是教师（role）访问教师相关界面（type）
				if(role.equals(type)) {
					return checkTeacherInfo(request, response);
				}
//				String resStr = JsonUtil.toJson(new Msg().fail().setMsg(new String("没有访问权限！".getBytes(),"utf8")));
//				response.getWriter().append(resStr);
				//response.getWriter().append(new String("没有访问权限！".getBytes(),"UTF-8"));
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return false;
			}
			case "admin": {
				////System.out.println("case admin");
				//如果是教师（role）访问教师相关界面（type）

				////System.out.println(role.equals(type));
				if(role.equals(type)) {
					return checkAdminInfo(request, response);
				}
//				String resStr = JsonUtil.toJson(new Msg().fail().setMsg(new String("没有访问权限！".getBytes(),"utf8")));
//				response.getWriter().append(resStr);
				response.getWriter().append(new String("没有访问权限！".getBytes(),"UTF-8"));
				return false;
			}
			default:break;
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		////System.out.println("调用LoginInterceptor-postHandle");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		////System.out.println("调用LoginInterceptor-afterCompletion");
		// TODO Auto-generated method stub

	}

	/**
	 * 处理学生相关的访问
	 * 
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean checkStudentInfo(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
	
		Student student = (Student) session.getAttribute("student");
		if(student==null){
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		
		String stuNumber = student.getStuNumber();
		String stuName = student.getStuName();
		if (stuNumber == null || stuName == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		return true;
	}
	

	/**
	 * 处理教师相关的访问
	 * 
	 * @param session
	 * @param response
	 * @return
	 */
	public boolean checkTeacherInfo(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if(teacher==null){
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		String teaNumber = teacher.getTeaNumber();
		String teaPassword = teacher.getTeaPassword();
		if (teaNumber == null || teaPassword == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		return true;
	}

	/**
	 * 处理管理员相关的访问
	 * 
	 * @param session
	 * @param response
	 * @return
	 */
	public boolean checkAdminInfo(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if(teacher==null){
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		String teaNumber = teacher.getTeaNumber();
		String teaPassword = teacher.getTeaPassword();
		Integer isAdmin = teacher.getIsAdmin();
		//System.out.println("isAdmin="+isAdmin+"---isAdmin!=StaticResources.admin="+isAdmin!=StaticResources.admin+"");
		//当教师是管理员是，才可以访问管理员用例
		if (teaNumber == null || teaPassword == null|| isAdmin!=StaticResources.admin) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		return true;
	}
	
}
