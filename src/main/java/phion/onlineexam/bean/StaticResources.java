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
	public static final int admin = 1;
	
	//is_admin属性,表示此对象为教师
	public static final int teacher = 0;
	
	//考试状态为草稿
	public static final String CREATING_EXAM = "creating";
	
	//考试状态为正在考试
	public static final String RUNNING_EXAM = "running";

	//考试状态为已考完
	public static final String COMPLETE_EXAM = "complete";

	//考试状态为即将开始
	public static final String READY_EXAM = "ready";

	//考试状态为今天即将开始
	public static final String READY_TODAY_EXAM = "reday_today";

	//考试状态为今天即将开始
	public static final String COMPLETE_AND_CLEAN_EXAM = "complete_and_clean";
	
	//默认管理员账号
	public static final String ADMIN_NUMBER = "admin";
	
	//默认管理员密码
	public static final String ADMIN_PASSWORD = "admin";
	
	//考试已下载
	public static final int IS_DOWNLOAD = 1;
	
	//考试未下载
	public static final int IS_NOT_DOWNLOAD = 0;
	
	//教师日志开头
	public static final String TEACHERLOG = "TeacherControllor:";
	
	//试卷名
	public static final String NEW_FILE_NAME = "testPaper.txt";
	
	//系统定时任务的表达式
	public static final String SYSTEM_DAILY_TASK_CRON = "0 0 0 1/1 * ?";
	
	//测试用cron
	public static final String TEST_SYSTEM_DAILY_TASK_CRON = "0/5 * * * * ?";
	
	/**
	 *  配置文件key
	 */
	
	//是否自动开启考试
	public static final String AUTO_START_EXAM = "autoStartExam";
	
	//最大上传文件大小
	public static final String MAX_UPLOAD_SIZE = "maxUploadSize";
	
	//手动开启考试的范围
	public static final String MANUAL_START_EXAM_RANGE = "manualStartExamRange";
	
}
