package phion.onlineexam.bean;

public class ExamInfo {
    private Integer inId;

    private Integer eId;

    private Integer allNumber;

    private Integer isDownload;

    private String info;

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
        return isDownload;
    }

    public void setIsDownload(Integer isDownload) {
        this.isDownload = isDownload;
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