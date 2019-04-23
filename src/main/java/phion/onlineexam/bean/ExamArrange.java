package phion.onlineexam.bean;

public class ExamArrange {
    private Integer arraId;

    private Integer stuId;

    private Integer eId;
    

    public ExamArrange() {
		super();
	}

	public ExamArrange(Integer arraId, Integer stuId, Integer eId) {
		super();
		this.arraId = arraId;
		this.stuId = stuId;
		this.eId = eId;
	}

	public Integer getArraId() {
        return arraId;
    }

    public void setArraId(Integer arraId) {
        this.arraId = arraId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

	@Override
	public String toString() {
		return "ExamArrange [arraId=" + arraId + ", stuId=" + stuId + ", eId=" + eId + "]";
	}
    
    
}