package cn.superion.materialAcct.daily.entity;

import java.util.List;

import cn.superion.material.entity.MaterialRdsAcctMaster;
import cn.superion.material.entity.MaterialRdsDetail;

/**
 * 出库报告单
 * @author 芮玉红
 *
 */
public class DeliverReportAcct {
	private String patientId;
	private MaterialRdsAcctMaster master;
	private List<MaterialRdsDetail> rdsDetails;
	
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public List<MaterialRdsDetail> getRdsDetails() {
		return rdsDetails;
	}
	public void setRdsDetails(List<MaterialRdsDetail> rdsDetails) {
		this.rdsDetails = rdsDetails;
	}
	public MaterialRdsAcctMaster getMaster() {
		return master;
	}
	public void setMaster(MaterialRdsAcctMaster master) {
		this.master = master;
	}
	
	
}
