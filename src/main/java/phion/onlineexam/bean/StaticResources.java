package phion.onlineexam.bean;

public class StaticResources {
	
	private StaticResources(){
		//私有化构造器
	}
	
	//Msg成功的状态码
	public static final int SUCCESS_CODE = 100;
	
	//Msg失败的状态码
	public static final int FAIL_CODE = 200;
	
	//Msg成功提示信息
	public static final String SUCCESS_MSG = "处理成功！";
	
	//Msg失败提示信息
	public static final String FAIL_MSG = "处理失败！";
	
	//is_admin属性,表示此对象为管理员
	public static int admin = 1;
	
	//is_admin属性,表示此对象为教师
	public static int teacher = 0;
	
	//考试状态为草稿
	public static String CREATING_EXAM = "creating";
	
	//考试状态为正在考试
	public static String RUNNING_EXAM = "running";

	//考试状态为已考完
	public static String COMPLETE_EXAM = "complete";

	//考试状态为即将开始
	public static String READY_EXAM = "ready";

	//考试状态为今天即将开始
	public static String READY_TODAY_EXAM = "reday_today";

	//考试状态为今天即将开始
	public static String COMPLETE_AND_CLEAN_EXAM = "complete_and_clean";
	
	//默认管理员账号
	public static String ADMIN_NUMBER = "admin";
	
	//默认管理员密码
	public static String ADMIN_PASSWORD = "admin";
}
