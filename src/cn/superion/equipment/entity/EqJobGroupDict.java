package cn.superion.equipment.entity;

/**
 * EqJobGroupDict entity. @author MyEclipse Persistence Tools
 */

public class EqJobGroupDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6575529520890311036L;
	private String jobCode;
	private String jobName;
	private String deptCode;
	private String director;
	private String content;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqJobGroupDict() {
	}

	/** minimal constructor */
	public EqJobGroupDict(String jobCode) {
		this.jobCode = jobCode;
	}

	/** full constructor */
	public EqJobGroupDict(String jobCode, String jobName, String deptCode,
			String director, String content, String remark,
			String phoInputCode, String fiveInputCode) {
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.deptCode = deptCode;
		this.director = director;
		this.content = content;
		this.remark = remark;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhoInputCode() {
		return this.phoInputCode;
	}

	public void setPhoInputCode(String phoInputCode) {
		this.phoInputCode = phoInputCode;
	}

	public String getFiveInputCode() {
		return this.fiveInputCode;
	}

	public void setFiveInputCode(String fiveInputCode) {
		this.fiveInputCode = fiveInputCode;
	}

}