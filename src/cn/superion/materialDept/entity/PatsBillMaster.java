package cn.superion.materialDept.entity;

/**
 * 病人费用主记录
 * @author 曹国魁
 *
 */
public class PatsBillMaster {
	private String unitsCode;
	private String patientId;
	private Byte visitId;
	private String inpNo;
	//开单科室
	private String orderedDept;
	//开单医生
	private String orderedDoctor;
	//执行科室
	private String performedDept;
	//执行医生
	private String performedDoctor;
	
	
	
	public PatsBillMaster() {
		super();
	}
	
	public PatsBillMaster(String unitsCode, String patientId) {
		super();
		this.unitsCode = unitsCode;
		this.patientId = patientId;
	}
	
	public PatsBillMaster(String unitsCode, String patientId, Byte visitId) {
		super();
		this.unitsCode = unitsCode;
		this.patientId = patientId;
		this.visitId = visitId;
	}



	public PatsBillMaster(String unitsCode, String patientId, Byte visitId,
			String inpNo, String orderedDept, String orderedDoctor,
			String performedDept, String performedDoctor) {
		super();
		this.unitsCode = unitsCode;
		this.patientId = patientId;
		this.visitId = visitId;
		this.inpNo = inpNo;
		this.orderedDept = orderedDept;
		this.orderedDoctor = orderedDoctor;
		this.performedDept = performedDept;
		this.performedDoctor = performedDoctor;
	}
	public String getUnitsCode() {
		return unitsCode;
	}
	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Byte getVisitId() {
		return visitId;
	}
	public void setVisitId(Byte visitId) {
		this.visitId = visitId;
	}
	public String getInpNo() {
		return inpNo;
	}
	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}
	public String getOrderedDept() {
		return orderedDept;
	}
	public void setOrderedDept(String orderedDept) {
		this.orderedDept = orderedDept;
	}
	public String getOrderedDoctor() {
		return orderedDoctor;
	}
	public void setOrderedDoctor(String orderedDoctor) {
		this.orderedDoctor = orderedDoctor;
	}
	public String getPerformedDept() {
		return performedDept;
	}
	public void setPerformedDept(String performedDept) {
		this.performedDept = performedDept;
	}
	public String getPerformedDoctor() {
		return performedDoctor;
	}
	public void setPerformedDoctor(String performedDoctor) {
		this.performedDoctor = performedDoctor;
	}
	
}
