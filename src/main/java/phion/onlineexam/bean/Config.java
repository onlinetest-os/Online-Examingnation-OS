package phion.onlineexam.bean;

public class Config {
	
	private String haveDeletePower = null;
	
	private String spiltPageCount = null;  
	
	private String manualStartExamRange = null;
	
	private String maxUploadSize = null;
	
	private String autoStartExam = null;
	
	public Config() {
		super();
	}
	
	public Config(String haveDeletePower, String spiltPageCount, String manualStartExamRange, String maxUploadSize,
			String autoStartExam) {
		super();
		this.haveDeletePower = haveDeletePower;
		this.spiltPageCount = spiltPageCount;
		this.manualStartExamRange = manualStartExamRange;
		this.maxUploadSize = maxUploadSize;
		this.autoStartExam = autoStartExam;
	}

	
	
	/**
	 * @return the haveDeletePower
	 */
	public String getHaveDeletePower() {
		return haveDeletePower;
	}

	/**
	 * @param haveDeletePower the haveDeletePower to set
	 */
	public void setHaveDeletePower(String haveDeletePower) {
		this.haveDeletePower = haveDeletePower;
	}

	/**
	 * @return the spiltPageCount
	 */
	public String getSpiltPageCount() {
		return spiltPageCount;
	}

	/**
	 * @param spiltPageCount the spiltPageCount to set
	 */
	public void setSpiltPageCount(String spiltPageCount) {
		this.spiltPageCount = spiltPageCount;
	}

	/**
	 * @return the manualStartExamRange
	 */
	public String getManualStartExamRange() {
		return manualStartExamRange;
	}

	/**
	 * @param manualStartExamRange the manualStartExamRange to set
	 */
	public void setManualStartExamRange(String manualStartExamRange) {
		this.manualStartExamRange = manualStartExamRange;
	}

	/**
	 * @return the maxUploadSize
	 */
	public String getMaxUploadSize() {
		return maxUploadSize;
	}

	/**
	 * @param maxUploadSize the maxUploadSize to set
	 */
	public void setMaxUploadSize(String maxUploadSize) {
		this.maxUploadSize = maxUploadSize;
	}

	/**
	 * @return the autoStartExam
	 */
	public String getAutoStartExam() {
		return autoStartExam;
	}

	/**
	 * @param autoStartExam the autoStartExam to set
	 */
	public void setAutoStartExam(String autoStartExam) {
		this.autoStartExam = autoStartExam;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Config [haveDeletePower=" + haveDeletePower + ", spiltPageCount=" + spiltPageCount
				+ ", manualStartExamRange=" + manualStartExamRange + ", maxUploadSize=" + maxUploadSize
				+ ", autoStartExam=" + autoStartExam + "]";
	}
	
	
	
}
