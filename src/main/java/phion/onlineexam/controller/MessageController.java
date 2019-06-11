package phion.onlineexam.controller;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import phion.onlineexam.bean.Msg;

@Controller
@RequestMapping("")
public class MessageController {

	//保持可见性
	public static  volatile List<ExamMsg> msgQueue = new ArrayList<ExamMsg>();

	public static  volatile int version = 0;

	static {
		msgQueue.add(new ExamMsg(LocalTime.now(), "这是第一条信息！"));
	}
	
	/**
	 * 返回所有通知
	 * @return the msgQueue
	 */
	@ResponseBody
	@RequestMapping("message_getMsgQueue")
	public Msg getMsgQueue() {
		return Msg.success()
				.add("msgQueue", msgQueue)
				.add("version", version);
	}
	
	
	/**
	 * 返回最新5条通知
	 * @return the msgQueue
	 */
	@ResponseBody
	@RequestMapping("message_getNewest")
	public Msg getNewestMsgQueue(int version) {
		//System.out.println(version);
		//return Msg.fail();
		//System.out.println("this version:"+this.version+ "\t version"+version);
		if(this.version == version) return Msg.fail();
		//return Msg.success().add("msgQueu", "nothing");
		List res = msgQueue.subList(msgQueue.size()-5>0?msgQueue.size()-5:0, 
				msgQueue.size());
		return Msg.success().add("msgQueue", res)
				.add("version", this.version)
				.add("length",res.size());
	}
	
	
	/**
	 * @param msgQueue the msgQueue to set
	 * 保护并发修改
	 */
	public static synchronized void setMsgQueue(List<ExamMsg> msg) {
		msgQueue = msg;
		version++;
	}

	/**
	 * 增加一条记录
	 */
	public static synchronized void addMsg(ExamMsg msg) {
		msgQueue.add(msg);
		System.out.println(msg.msg);
		version++;
	}
	
	public static synchronized void delete(int index) {
		msgQueue.remove(index);
		version++;
	}
	
	public static synchronized void clear() {
		msgQueue.clear();
		version++;
	}
	
}

class ExamMsg implements Serializable{
	public LocalTime time;
	public String msg;
	public ExamMsg(LocalTime time,String msg) {
		this.time = time;
		this.msg = msg;
	}
}
