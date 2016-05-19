package cn.superion.cssd.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.cssd.dao.CssdRetrieveDetailDAO;
import cn.superion.cssd.dao.CssdWashDetailDAO;
import cn.superion.cssd.dao.CssdWashMasterDAO;
import cn.superion.cssd.entity.CssdRetrieveDetail;
import cn.superion.cssd.entity.CssdWashDetail;
import cn.superion.cssd.entity.CssdWashMaster;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 物品清洗消毒服务实现
 * 
 * @author 曹国魁
 * 
 */
public class WashImpl implements IWash {
	private static final String PARA_CODE_AUTO_CHK = "0202";
	private CssdRetrieveDetailDAO cssdRetrieveDetailDAO; 
	private CssdWashMasterDAO cssdWashMasterDAO;
	private CssdWashDetailDAO cssdWashDetailDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	private CdSysParamDAO cdSysParamDAO;

	public CssdRetrieveDetailDAO getCssdRetrieveDetailDAO() {
		return cssdRetrieveDetailDAO;
	}

	public void setCssdRetrieveDetailDAO(CssdRetrieveDetailDAO cssdRetrieveDetailDAO) {
		this.cssdRetrieveDetailDAO = cssdRetrieveDetailDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public CssdWashMasterDAO getCssdWashMasterDAO() {
		return cssdWashMasterDAO;
	}

	public void setCssdWashMasterDAO(CssdWashMasterDAO cssdWashMasterDAO) {
		this.cssdWashMasterDAO = cssdWashMasterDAO;
	}

	public CssdWashDetailDAO getCssdWashDetailDAO() {
		return cssdWashDetailDAO;
	}

	public void setCssdWashDetailDAO(CssdWashDetailDAO cssdWashDetailDAO) {
		this.cssdWashDetailDAO = cssdWashDetailDAO;
	}

	public ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	@Override
	public ReObject deleteById(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的物品清洗消毒单据");
		CssdWashMaster master = cssdWashMasterDAO.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("物品清洗消毒单不存在！");
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("物品清洗消毒记录已审核，不能删除！");
		}
		cssdRetrieveDetailDAO.cleanWashSignByWashMasterAutoId(fstrAutoId);
		cssdWashDetailDAO.delByMainAutoId(fstrAutoId);
		cssdWashMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前物品清洗消毒的详细信息记录");
		CssdWashMaster master = cssdWashMasterDAO.findById(fstrAutoId);
		List<CssdWashDetail> details = null;
		if (master != null) {
			details = cssdWashDetailDAO.findByMainAutoId(fstrAutoId);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findMasterIdListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物品清洗消毒列表");
		List<Object> data = cssdWashMasterDAO.findAutoIdsByCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(CssdWashMaster fmaster, List<CssdWashDetail> flstDetail) {
		ReObject ro = new ReObject("保存当前物品清洗消毒信息");
		if (fmaster == null)
			throw new RuntimeException("物品清洗消毒主记录不能为空！");
		if (flstDetail == null || flstDetail.isEmpty())
			throw new RuntimeException("物品清洗消毒明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String appCode = user.getAppCode();
		Date curDate = new Date();
		// 是否自动审核
		boolean isAutoChk = cdSysParamDAO.findByParaCode(unitsCode, appCode,
				PARA_CODE_AUTO_CHK, "0").equals("1");
		String autoId = fmaster.getAutoId();
		if (autoId == null || "".equals(autoId)) {
			// 新增
			String billNo = fmaster.getBillNo();
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(deptCommMaterialServiceImpl
						.getNextBillNo(RdConstant.OTHERS));
			} else {
				// 新增时，校验手工输入的流水号在一个单位中唯一性
				if (!cssdWashMasterDAO.checkBillNoUnique(unitsCode, billNo)) {
					throw new RuntimeException("手工输入的单据编号[" + billNo + "]在单位["
							+ unitsCode + "]下有重复");
				}
			}
			if (fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			if(isAutoChk){
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			}else{
				fmaster.setCurrentStatus("0");
			}
			cssdWashMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		} else {
			// 修改
			CssdWashMaster original = cssdWashMasterDAO.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物品清洗消毒主记录！");
			}
			if ("1".equals(original.getCurrentStatus())) {
				throw new RuntimeException("物品清洗消毒单已审核，不能修改！");
			}
			if(isAutoChk){
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			}else{
				fmaster.setCurrentStatus("0");
			}
			cssdWashMasterDAO.merge(fmaster);
			cssdWashDetailDAO.delByMainAutoId(autoId);
			cssdRetrieveDetailDAO.cleanWashSignByWashMasterAutoId(autoId);
		}
		short i = 0;
		for (CssdWashDetail detail : flstDetail) {
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			cssdWashDetailDAO.save(detail);
//			double sumAmount = detail.getAmount();
//			//查找回收明细
//			List<CssdRetrieveDetail> lstRetrieve = cssdRetrieveDetailDAO.findRetrieveListByMaterialId(fmaster.getBeginRetrieveDate(), fmaster.getEndRetrieveDate(),detail.getMaterialId());
//			for (CssdRetrieveDetail retrieveDetail :lstRetrieve ) {
//				if(retrieveDetail.getAmount() < sumAmount){
//					retrieveDetail.setCurrentStatus("1");
//					sumAmount -= retrieveDetail.getAmount();
//				}else{
//					retrieveDetail.setAmount(sumAmount);
//					break;
//				}
//			}
		}
		//更新清洗系统标识号
		cssdRetrieveDetailDAO.updateWashedAutoId(autoId,unitsCode,fmaster.getBeginRetrieveDate(),fmaster.getEndRetrieveDate());
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(flstDetail);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物品清洗消毒信息");
		CssdWashMaster original = cssdWashMasterDAO.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物品清洗消毒单！");
		}
		if ("1".equals(original.getCurrentStatus())) {
			throw new RuntimeException("物品清洗消毒单已审核，不能审核！");
		}
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRetrieveMaterialStatByCondition(
			ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤回收物品包物资");
		List<Object> data = cssdRetrieveDetailDAO.addUpByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
