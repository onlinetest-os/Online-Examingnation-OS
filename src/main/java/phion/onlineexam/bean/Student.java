package phion.onlineexam.bean;

public class Student {
    private Integer stuId;

    private String stuNumber;

    private String stuName;

    private String stuPassword;

    private String ip;

    private String commitinfo;

    private String stuClass;
    
    
    public Student() {
    	super();
		// TODO Auto-generated constructor stub
	}
    
    public Student(String stuNumber, String stuName, String stuClass) {
		super();
		this.stuNumber = stuNumber;
		this.stuName = stuName;
		this.stuClass = stuClass;
	}

    

    public Student(String stuNumber, String stuName, String ip, String stuClass) {
		super();
		this.stuNumber = stuNumber;
		this.stuName = stuName;
		this.ip = ip;
		this.stuClass = stuClass;
	}


	public Student(Integer stuId, String stuNumber, String stuName, String stuPassword, String ip, String commitinfo,
			String stuClass) {
		super();
		this.stuId = stuId;
		this.stuNumber = stuNumber;
		this.stuName = stuName;
		this.stuPassword = stuPassword;
		this.ip = ip;
		this.commitinfo = commitinfo;
		this.stuClass = stuClass;
	}

	public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber == null ? null : stuNumber.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword == null ? null : stuPassword.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCommitinfo() {
        return commitinfo;
    }

    public void setCommitinfo(String commitinfo) {
        this.commitinfo = commitinfo == null ? null : commitinfo.trim();
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass == null ? null : stuClass.trim();
    }


	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuNumber=" + stuNumber + ", stuName=" + stuName + ", stuPassword="
				+ stuPassword + ", ip=" + ip + ", commitinfo=" + commitinfo + ", stuClass=" + stuClass + "]";
	}
    
}