package phion.onlineexam.bean;

import java.util.Date;
import java.util.List;

public class Exam {
    private Integer eId;

    private String eName;

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
    
    
    public Exam(Integer eId) {
		super();
		this.eId = eId;
	}


	public Exam(Date startTime) {
		super();
		this.startTime = startTime;
	}



	public Exam(String status) {
		super();
		this.status = status;
	}
	public Exam (Integer eId,String paperPath,
			String paperAnwserPath) {
		super();
		this.eId = eId;
		this.paperPath = paperPath;
		this.paperAnwserPath = paperAnwserPath;
	}
	


	public Exam(Integer eId, String eName, Integer teaId, Date startTime, Date endTime, String paperPath,
			String paperAnwserPath, String status, ExamInfo examInfo, List<Student> students) {
		super();
		this.eId = eId;
		this.eName = eName;
		this.teaId = teaId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.paperPath = paperPath;
		this.paperAnwserPath = paperAnwserPath;
		this.status = status;
		this.examInfo = examInfo;
		this.students = students;
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

	public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
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

	@Override
	public String toString() {
		return "Exam [eId=" + eId + ", eName=" + eName + ", teaId=" + teaId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", paperPath=" + paperPath + ", paperAnwserPath=" + paperAnwserPath + ", status=" + status
				+ ", examInfo=" + examInfo + ", students=" + students + "]";
	}
    
}