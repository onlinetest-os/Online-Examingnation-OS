package phion.onlineexam.bean;

import java.util.Date;

public class Exam {
    private Integer eId;

    private Integer teaId;

    private Date startTime;

    private Date endTime;

    private String paperPath;

    private String paperAnwserPath;

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
}