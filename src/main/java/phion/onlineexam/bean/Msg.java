package phion.onlineexam.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于封装返回的数据
 * @author 15037
 *
 */
public class Msg {
	
	//状态码   100-成功    200-失败
	private int code;
	
	//提示信息
	private String msg;
	
	//用户要返回给浏览器的数据
	private Map<String, Object> extend = new HashMap<String, Object>();
	
	/**
	 * 处理成功数据头
	 * @return
	 */
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(StaticResources.SUCCESS_CODE);
		result.setMsg(StaticResources.SUCCESS_MSG);
		return result;
	}
	
	/**
	 * 处理失败数据头
	 * @return
	 */
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(StaticResources.FAIL_CODE);
		result.setMsg(StaticResources.FAIL_MSG);
		return result;
	}
	
	/**
	 * 用于添加返回信息
	 * @param key
	 * @param value
	 * @return
	 */
	public Msg add(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public Msg setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Msg setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
}
