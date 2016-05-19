package cn.superion.cssd.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdProvideDetailDAO;
import cn.superion.cssd.dao.CssdProvideMasterDAO;
import cn.superion.cssd.entity.CssdProvideDetail;
import cn.superion.cssd.entity.CssdProvideMaster;
import cn.superion.material.common.RdConstant;
import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 物资领用服务实现
 * 
 * @author 曹国魁
 * 
 */
public class CssdApplyImpl implements ICssdApply {
	private CssdProvideMasterDAO cssdProvideMasterDAO;
	private CssdProvideDetailDAO cssdProvideDetailDAO;
	private cn.superion.material.common.ICommMaterialService commMaterialServiceImpl;
	private cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl;

	public CssdProvideMasterDAO getCssdProvideMasterDAO() {
		return cssdProvideMasterDAO;
	}

	public void setCssdProvideMasterDAO(CssdProvideMasterDAO cssdProvideMasterDAO) {
		this.cssdProvideMasterDAO = cssdProvideMasterDAO;
	}

	public CssdProvideDetailDAO getCssdProvideDetailDAO() {
		return cssdProvideDetailDAO;
	}

	public void setCssdProvideDetailDAO(CssdProvideDetailDAO cssdProvideDetailDAO) {
		this.cssdProvideDetailDAO = cssdProvideDetailDAO;
	}

	public cn.superion.material.common.ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			cn.superion.material.common.ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	public cn.superion.materialDept.common.service.ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	@Override
	public ReObject deleteApply(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的物资领用申请");
		CssdProvideMaster master = cssdProvideMasterDAO
				.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("物资领用申请不存在！");
		if (!"0".equals(master.getCurrentStatus())) {
			throw new RuntimeException("物资领用申请已审核或领用出库，不能删除！");
		}
		cssdProvideDetailDAO.deleteByMainAutoId(fstrAutoId);
		cssdProvideMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findApplyDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看物资领用申请单据详细信息记录");
		CssdProvideMaster master = cssdProvideMasterDAO
				.findById(fstrAutoId);
		List<CssdProvideDetail> details = cssdProvideDetailDAO
				.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findApplyMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询物资领用申请单据信息");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("deptCode", user.getDeptCode());
		List<Object> data = cssdProvideMasterDAO.findAutoIdsByCondition(
				user.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}


	@Override
	public ReObject findApplyListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤已审的申请单");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("deptCode", user.getDeptCode());
		fparameter.getConditions().put("currentStatus", "1");
		List<Object> data = cssdProvideMasterDAO.findAutoIdsByCondition(
				user.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveApply(CssdProvideMaster fmaster,
			List<CssdProvideDetail> fdetails) {
		ReObject ro = new ReObject("保存物资领用申请信息");
		if (fmaster == null)
			throw new RuntimeException("物资领用申请主记录不能为空！");
		if (fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("物资领用申请明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String deptCode = user.getDeptCode();
		Date curDate = new Date();
		
		String autoId = fmaster.getAutoId();
		if (autoId == null || "".equals(autoId)) {
			// 新增
			String billNo = fmaster.getBillNo();
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(deptCommMaterialServiceImpl.getNextBillNo(
						RdConstant.R));
			}
			if (fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if (fmaster.getPersonId() == null
					|| "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setDeptCode(deptCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			fmaster.setCurrentStatus("0");
			cssdProvideMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		} else {
			// 修改
			CssdProvideMaster original = cssdProvideMasterDAO
					.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物资领用申请主记录！");
			}
			if (!"0".equals(original.getCurrentStatus())) {
				throw new RuntimeException("物资领用申请已审核或领用出库，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			cssdProvideMasterDAO.merge(fmaster);
			cssdProvideDetailDAO.deleteByMainAutoId(autoId);
		}
		short i = 0;
		for (CssdProvideDetail detail : fdetails) {
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			cssdProvideDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verifyApply(String fstrAutoId) {
		ReObject ro = new ReObject("审核物资领用申请");
		CssdProvideMaster original = cssdProvideMasterDAO
				.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物资领用申请主记录！");
		}
		if (!"0".equals(original.getCurrentStatus())) {
			throw new RuntimeException("物资领用申请已审核或领用出库，不能审核！");
		}
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		original.setCurrentStatus("1");
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}
	
	public ReObject cancelVerifyApply(String fstrAutoId) {
		ReObject ro = new ReObject("弃审物品包申请单");
		CssdProvideMaster original = cssdProvideMasterDAO
				.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物资领用申请主记录！");
		}
		if ("3".equals(original.getCurrentStatus())) {
			throw new RuntimeException("该单据已发放，不能弃审！");
		}
		original.setVerifier(null);
		original.setVerifyDate(null);
		original.setRemark("弃审");
		original.setCurrentStatus("0");
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}
//	public ReObject verifyDeptReceive(String fstrAutoId) {
//		ReObject ro = new ReObject("根据领用出库单主记录ID确认当前的物资领用入科信息");
//		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
//		CssdDeliverMaster master = (CssdDeliverMaster) objs[0];
//		List<MaterialRdsDetail> details = (List<MaterialRdsDetail>) objs[1];
//		if (master == null) {
//			throw new RuntimeException("领用出库单[系统标识号：" + fstrAutoId + "]不存在！");
//		}
//		if ("0".equals(master.getCurrentStatus())) {
//			throw new RuntimeException("领用出库单[系统标识号：" + fstrAutoId
//					+ "]仓库还未审核，不能审核！");
//		}
//		String unitsCode = master.getUnitsCode();
//		String cardCode = master.getCardCode();
//		MaterialRdsMasterDept mrmd = null;
//		List<MaterialRdsDetailDept> mrdds = null;
//		boolean isBlue = "1".equals(master.getInvoiceType());
//		if (isBlue) {
//			//蓝字单据时，要生成科室领用物资入库单
//			String billNo = master.getOperationNo();
//			CssdProvideMaster pmaster = CssdProvideDetailDAO
//					.findByBillNo(master.getUnitsCode(), master
//							.getStorageCode(), billNo);
//			if (pmaster == null) {
//				throw new RuntimeException("科室领用申请单[单据号：" + billNo
//						+ "]不存在,科室不能入库确认!");
//			}
//			pmaster.setCurrentStatus("3");
//			cardCode = pmaster.getCardCode();
//			MaterialRdsMasterDept dmaster = buildMaterialRdsMasterDept(master,
//					pmaster);
//			List<MaterialRdsDetailDept> ddetails = new ArrayList<MaterialRdsDetailDept>();
//			for (MaterialRdsDetail detail : details) {
//				ddetails.add(buildMaterialRdsDetailDept(detail));
//			}
//			Object[] dobjs = deptCommMaterialServiceImpl
//					.save(dmaster, ddetails);
//			mrmd = (MaterialRdsMasterDept) dobjs[0];
//			mrdds = (List<MaterialRdsDetailDept>) dobjs[1];
//		}
//		Double payout = 0d;
//		for (MaterialRdsDetail detail : details) {
//			Double retailMoney = 0d;
//			if(isBlue){
//				CdMaterialDict madt = cdMaterialDictDAO.findById(unitsCode, detail.getMaterialId());
//				retailMoney = madt.getRetailPrice()*detail.getAmount();
//			}else{
//				retailMoney = detail.getRetailMoney();
//			}
//			if(retailMoney == null){
//				log.warn(detail.getMaterialName()+"["+detail.getMaterialId()+"]售价为空，不能有效合计科室领用物资总费用！");
//			}else{
//				payout += retailMoney;
//			}
//		}
//		//更新物资领用卡帐户余额
//		Double accountRemain = 0d;
//		String appCode = SessionUtil.getAppCode();
//		boolean isUseCard = "1".equals(cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0"));
//		if(isUseCard){
//			if(cardCode != null && !cardCode.equals("")){
//				CdMaterialCard cmc = cdMaterialCardDAO.updateCredit(unitsCode, cardCode,payout);
//				if(cmc == null){
//					log.warn("物资领用卡[卡号:"+cardCode+"]不存在或已停用，不能更新帐户余额(支出:"+payout+")！");
//				}else{
//					accountRemain = cmc.getAccountRemain();
//				}
//			}else{
//				log.warn("物资领用卡号为空，不能更新帐户余额！");
//			}
//		}
//		List data = new ArrayList();
//		data.add(accountRemain);
//		data.add(mrmd);
//		data.add(mrdds);
//		ro.setData(data);
//		return ro;
//	}

}
