package phion.onlineexam.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.internal.util.StringUtility;

public class Exam {


	private Integer eId;

	private Integer teaId;

	private Date startTime;

	private Date endTime;

	private String paperPath;

	private String paperAnwserPath;

	private String status;

	private ExamInfo examInfo;
	
	private List<Student> students;

	public Exam() {
		super();
	}

	public Exam(Integer eId, Integer teaId, Date startTime, Date endTime, String paperPath, String paperAnwserPath,
			String status, ExamInfo examInfo) {
		super();
		this.eId = eId;
		this.teaId = teaId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.paperPath = paperPath;
		this.paperAnwserPath = paperAnwserPath;
		this.status = status;
		this.examInfo = examInfo;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public Integer getTeaId() {
		return teaId;
	}

	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPaperPath() {
		return paperPath;
	}

	public void setPaperPath(String paperPath) {
		this.paperPath = paperPath == null ? null : paperPath.trim();
	}

	public String getPaperAnwserPath() {
		return paperAnwserPath;
	}

	public void setPaperAnwserPath(String paperAnwserPath) {
		this.paperAnwserPath = paperAnwserPath == null ? null : paperAnwserPath.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public ExamInfo getExamInfo() {
		return examInfo;
	}

	public void setExamInfo(ExamInfo examInfo) {
		this.examInfo = examInfo;
	}
	
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	/**
	 * 应考试学生数
	 * @return
	 */
	public int getStudentsCount() {
		if(students!=null) return students.size();
		else return 0;
	}
	
	/**
	 * 已登录学生
	 * @return
	 */
	public List<Student> getOnlineStudents() {
		List<Student> onlineStudents = new ArrayList<Student>();
		if(students!=null) {
			for(Student s : students) {
				onlineStudents.add(s);
			}
		}
		return onlineStudents;
	}
	

	@Override
	public String toString() {
		return "Exam [eId=" + eId + ", teaId=" + teaId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", paperPath=" + paperPath + ", paperAnwserPath=" + paperAnwserPath + ", status=" + status
				+ ", examInfo=" + examInfo + ", students=" + students + "]";
	}

}