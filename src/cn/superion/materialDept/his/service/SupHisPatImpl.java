package cn.superion.materialDept.his.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import cn.superion.materialDept.dao.InpPatsInDeptDAO;
import cn.superion.materialDept.dao.InpPatsVisitDAO;
import cn.superion.materialDept.entity.InpPatsInDept;
import cn.superion.materialDept.entity.InpPatsVisit;
import cn.superion.materialDept.entity.PatsVisit;
import cn.superion.util.SessionUtil;
/**
 * 世一HIS病人信息服务实现
 * @author 曹国魁
 *
 */
public class SupHisPatImpl implements IHisPat {
	private Log log= LogFactory.getLog(SupHisPatImpl.class);
	private InpPatsVisitDAO inpPatsVisitDAO;
	private InpPatsInDeptDAO inpPatsInDeptDAO;
	public InpPatsInDeptDAO getInpPatsInDeptDAO() {
		return inpPatsInDeptDAO;
	}

	public void setInpPatsInDeptDAO(InpPatsInDeptDAO inpPatsInDeptDAO) {
		this.inpPatsInDeptDAO = inpPatsInDeptDAO;
	}

	public InpPatsVisitDAO getInpPatsVisitDAO() {
		return inpPatsVisitDAO;
	}

	public void setInpPatsVisitDAO(InpPatsVisitDAO inpPatsVisitDAO) {
		this.inpPatsVisitDAO = inpPatsVisitDAO;
	}

	@Override
	public PatsVisit findInPatInfo(String fstrPatientId) {
		PatsVisit pv = new PatsVisit();
		String unitsCode = SessionUtil.getUnitsCode();
		//查询住院病人记录
		InpPatsVisit ipv = inpPatsVisitDAO.findPatsVisitById(unitsCode,fstrPatientId);
		if(ipv == null){
			log.warn("病人[标识号："+fstrPatientId+"]不存在!");
			return null;
		}
		BeanUtils.copyProperties(ipv,pv);
		pv.setUnitsCode(unitsCode);
		pv.setPatientId(ipv.getInpNo());
		pv.setVisitId(Byte.valueOf("1"));
		pv.setInpNo(pv.getPatientId());
		pv.setPrepaymentsLeft(ipv.getPrepayments1() + ipv.getPrepayments2()-ipv.getTotalCharges());
		//查询在科病人记录
		InpPatsInDept ipid = inpPatsInDeptDAO.findCurrentInDept(unitsCode,fstrPatientId);
		if(ipid != null){
			pv.setDoctor(ipid.getDoctor());
			pv.setGroupCode(ipid.getGroupCode());
			pv.setDeptCode(ipid.getDeptCode());
			pv.setWardCode(ipid.getWardCode());
			pv.setDiagnoseClass(ipid.getDiagnoseClass());
			pv.setDiagnoseCode(ipid.getDiagnoseCode());
			pv.setDiagnoseName(ipid.getDiagnoseName());
		}
		return pv;
	}

	@Override
	public PatsVisit findOutPatInfo(String fstrPatientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double findPrepaymentLeft(String fstrPatientId) {
		String unitsCode = SessionUtil.getUnitsCode();
		InpPatsVisit ipv = inpPatsVisitDAO.findById(unitsCode,fstrPatientId);
		if(ipv == null){
			log.warn("病人[标识号："+fstrPatientId+"]不存在！");
			return 0d;
		}
		return ipv.getPrepayments1() + ipv.getPrepayments2()-ipv.getTotalCharges();
	}

}
