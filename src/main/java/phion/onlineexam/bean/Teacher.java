package phion.onlineexam.bean;

import java.util.List;

public class Teacher {
    private Integer teaId;

    private String teaNumber;

    private String teaName;

    private String teaPassword;

    private Integer isAdmin;
    
    private List<Exam> exams;
    
    
    public Teacher() {
    	super();
    }

    public Teacher(Integer teaId, String teaNumber, String teaName, String teaPassword, Integer isAdmin,
			List<Exam> exams) {
		super();
		this.teaId = teaId;
		this.teaNumber = teaNumber;
		this.teaName = teaName;
		this.teaPassword = teaPassword;
		this.isAdmin = isAdmin;
		this.exams = exams;
	}

	public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaNumber() {
        return teaNumber;
    }

    public void setTeaNumber(String teaNumber) {
        this.teaNumber = teaNumber == null ? null : teaNumber.trim();
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword == null ? null : teaPassword.trim();
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	@Override
	public String toString() {
		return "Teacher [teaId=" + teaId + ", teaNumber=" + teaNumber + ", teaName=" + teaName + ", teaPassword="
				+ teaPassword + ", isAdmin=" + isAdmin + ", exams=" + exams + "]";
	}
}