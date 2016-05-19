package cn.superion.cssd.entity;


import java.util.Date;

/**
 * CssdPersonArrange entity. @author MyEclipse Persistence Tools
 */

public class CssdPersonArrange implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 583270612738684713L;
	private String autoId;
	private String unitsCode;
	private String personId;
	private String deptCode;
	private Date workDate;
	private String arrangeCode;
	private String arrangeName;
	private String startWorkTime;
	private String endWorkTime;
	private String operator;
	private Date operateDate;

	// Constructors

	/** default constructor */
	public CssdPersonArrange() {
	}

	/** minimal constructor */
	public CssdPersonArrange(String autoId, String unitsCode, String personId) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.personId = personId;
	}

	/** full constructor */
	public CssdPersonArrange(String autoId, String unitsCode, String personId,
			String deptCode, Date workDate, String arrangeCode,
			String arrangeName, String startWorkTime, String endWorkTime,
			String operator, Date operateDate) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.personId = personId;
		this.deptCode = deptCode;
		this.workDate = workDate;
		this.arrangeCode = arrangeCode;
		this.arrangeName = arrangeName;
		this.startWorkTime = startWorkTime;
		this.endWorkTime = endWorkTime;
		this.operator = operator;
		this.operateDate = operateDate;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Date getWorkDate() {
		return this.workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getArrangeCode() {
		return this.arrangeCode;
	}

	public void setArrangeCode(String arrangeCode) {
		this.arrangeCode = arrangeCode;
	}

	public String getArrangeName() {
		return this.arrangeName;
	}

	public void setArrangeName(String arrangeName) {
		this.arrangeName = arrangeName;
	}

	public String getStartWorkTime() {
		return this.startWorkTime;
	}

	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public String getEndWorkTime() {
		return this.endWorkTime;
	}

	public void setEndWorkTime(String endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

}