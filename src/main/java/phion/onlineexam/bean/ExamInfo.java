package phion.onlineexam.bean;

public class ExamInfo {
    private Integer inId;

    private Integer eId;

    private Integer allNumber;

    private Integer isDownload;

    private String info;
    

    public ExamInfo() {
		super();
	}
    

	public ExamInfo(Integer inId, Integer eId) {
		super();
		this.inId = inId;
		this.eId = eId;
	}

	public ExamInfo(Integer inId, Integer eId, Integer allNumber, Integer isDownload, String info) {
		super();
		this.inId = inId;
		this.eId = eId;
		this.allNumber = allNumber;
		this.isDownload = isDownload;
		this.info = info;
	}

	public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(Integer allNumber) {
        this.allNumber = allNumber;
    }

    public Integer getIsDownload() {
        return isDownload==null?0:isDownload;
    }

    public void setIsDownload(Integer isDownload) {
        this.isDownload = isDownload==null?0:isDownload;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

	@Override
	public String toString() {
		return "ExamInfo [inId=" + inId + ", eId=" + eId + ", allNumber=" + allNumber + ", isDownload=" + isDownload
				+ ", info=" + info + "]";
	}
}