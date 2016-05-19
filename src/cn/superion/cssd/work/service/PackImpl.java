package cn.superion.cssd.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.cssd.dao.CssdPackageDictDAO;
import cn.superion.cssd.dao.CssdPackageMasterDAO;
import cn.superion.cssd.dao.CssdStockDetailDAO;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.cssd.dao.CssdWashDetailDAO;
import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdPackageMaster;
import cn.superion.cssd.entity.CssdStockDetail;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.cssd.entity.CssdWashDetail;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 物品打包登记服务实现
 * 
 * @author 曹国魁
 * 
 */
public class PackImpl implements IPack {
	private static final String PARA_CODE_AUTO_CHK = "0203";
	private CssdPackageMasterDAO cssdPackageMasterDAO;
	private CssdStockMasterDAO cssdStockMasterDAO;
	private CssdStockDetailDAO cssdStockDetailDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	private CssdWashDetailDAO cssdWashDetailDAO;
	private CssdPackageDictDAO cssdPackageDictDAO;

	public CssdPackageDictDAO getCssdPackageDictDAO() {
		return cssdPackageDictDAO;
	}

	public void setCssdPackageDictDAO(CssdPackageDictDAO cssdPackageDictDAO) {
		this.cssdPackageDictDAO = cssdPackageDictDAO;
	}

	public CssdWashDetailDAO getCssdWashDetailDAO() {
		return cssdWashDetailDAO;
	}

	public void setCssdWashDetailDAO(CssdWashDetailDAO cssdWashDetailDAO) {
		this.cssdWashDetailDAO = cssdWashDetailDAO;
	}

	private CdSysParamDAO cdSysParamDAO;

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public CssdPackageMasterDAO getCssdPackageMasterDAO() {
		return cssdPackageMasterDAO;
	}

	public void setCssdPackageMasterDAO(
			CssdPackageMasterDAO cssdPackageMasterDAO) {
		this.cssdPackageMasterDAO = cssdPackageMasterDAO;
	}

	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}

	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
	}

	public CssdStockDetailDAO getCssdStockDetailDAO() {
		return cssdStockDetailDAO;
	}

	public void setCssdStockDetailDAO(CssdStockDetailDAO cssdStockDetailDAO) {
		this.cssdStockDetailDAO = cssdStockDetailDAO;
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
		ReObject ro = new ReObject("删除当前未审核的物品打包单据");
		CssdPackageMaster master = cssdPackageMasterDAO.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("物品打包单不存在！");
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("物品打包记录已审核，不能删除！");
		}
		String unitsCode = master.getUnitsCode();
		List<CssdStockMaster> pgkList = cssdStockMasterDAO.findByPackageAutoId(
				unitsCode, fstrAutoId);
		for (CssdStockMaster pkg : pgkList) {
			String packageNo = pkg.getPackageNo();
			// 检查物品包状态为打包状态
			if ("1".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]已灭菌入库，不能删除！");
			}
			if ("2".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]已发放出库，不能删除！");
			}
			if ("3".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo + "]已回收，不能删除！");
			}
			if (!"0".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("只能删除打包入库状态的物品包记录，物品包[编号："
						+ packageNo + "]不能删除！");
			}
		}
		cssdStockDetailDAO.delByPackageAutoId(unitsCode, fstrAutoId);
		cssdStockMasterDAO.delByPackageAutoId(unitsCode, fstrAutoId);
		cssdPackageMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前物品打包登记的详细信息记录");
		CssdPackageMaster master = cssdPackageMasterDAO.findById(fstrAutoId);
		List<CssdStockMaster> details = null;
		if (master != null) {
			details = cssdStockMasterDAO.findByPackageAutoId(master
					.getUnitsCode(), fstrAutoId);
			for (CssdStockMaster stockMaster : details) {
				stockMaster.setStockDetailList(cssdStockDetailDAO
						.findByPackageNo(stockMaster.getPackageNo()));
			}
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findMasterIdListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物品打包列表");
		List<Object> data = cssdPackageMasterDAO.findAutoIdsByCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(CssdPackageMaster fmaster,
			List<CssdStockMaster> flstStockMaster, List<CssdWashDetail> mAutoid) {
		ReObject ro = new ReObject("保存当前物品打包信息");
		if (fmaster == null)
			throw new RuntimeException("物品打包主记录不能为空！");
		if (flstStockMaster == null || flstStockMaster.isEmpty())
			throw new RuntimeException("物品包记录不能为空！");
		for (CssdStockMaster stockMaster : flstStockMaster) {
			if (stockMaster.getStockDetailList() == null
					|| stockMaster.getStockDetailList().isEmpty()) {
				throw new RuntimeException("物品包明细记录不能为空！");
			}
		}
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
				if (!cssdPackageMasterDAO.checkBillNoUnique(unitsCode, billNo)) {
					throw new RuntimeException("手工输入的单据编号[" + billNo + "]在单位["
							+ unitsCode + "]下有重复");
				}
			}
			if (fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			if (isAutoChk) {
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			} else {
				fmaster.setCurrentStatus("0");
			}
			cssdPackageMasterDAO.save(fmaster);

			for (CssdWashDetail ids : mAutoid) {

				cssdWashDetailDAO.attachDirty(ids);
			}

			autoId = fmaster.getAutoId();
		} else {
			// 修改
			CssdPackageMaster original = cssdPackageMasterDAO.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物品打包主记录！");
			}
			if ("1".equals(original.getCurrentStatus())) {
				throw new RuntimeException("物品打包单已审核，不能修改！");
			}
			List<CssdStockMaster> pgkList = cssdStockMasterDAO
					.findDetachedByPackageAutoId(unitsCode, autoId);
			for (CssdStockMaster pkg : pgkList) {
				String packageNo = pkg.getPackageNo();
				// 检查物品包回收状态为打包状态
				if ("1".equals(pkg.getCurrentStatus())) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已灭菌入库，不能修改！");
				}
				if ("2".equals(pkg.getCurrentStatus())) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已发放出库，不能修改！");
				}
				if ("3".equals(pkg.getCurrentStatus())) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已回收，不能修改！");
				}
				if (!"0".equals(pkg.getCurrentStatus())) {
					throw new RuntimeException("只能修改打包入库状态的物品包记录，物品包[编号："
							+ packageNo + "]不能修改！");
				}
			}
			if (isAutoChk) {
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			} else {
				fmaster.setCurrentStatus("0");
			}
			cssdPackageMasterDAO.merge(fmaster);
			cssdStockDetailDAO.delByPackageAutoId(unitsCode, autoId);
			cssdStockMasterDAO.delByPackageAutoId(unitsCode, autoId);
		}
		short i = 0;
		for (CssdStockMaster stockMaster : flstStockMaster) {
			stockMaster.setUnitsCode(unitsCode);
			stockMaster.setUsedSign("0");
			stockMaster.setCurrentStatus("0");
			stockMaster.setPackageAutoId(autoId);
			stockMaster.setPackageSerialNo(++i);
			stockMaster.setSterilizeStatus("0");
			cssdStockMasterDAO.save(stockMaster);
			String packageNo = stockMaster.getPackageNo();
			short serialNo = 0;
			for (CssdStockDetail stockDetail : stockMaster.getStockDetailList()) {
				stockDetail.setPackageNo(packageNo);
				stockDetail.setSerialNo(++serialNo);
				cssdStockDetailDAO.save(stockDetail);
			}
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(flstStockMaster);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物品打包信息");
		CssdPackageMaster original = cssdPackageMasterDAO.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物品打包单！");
		}
		if ("1".equals(original.getCurrentStatus())) {
			throw new RuntimeException("物品打包单已审核，不能审核！");
		}
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	public ReObject findWashMaterialStatByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤清洗物品包物资");
		// 符合条件的清洗明细
		List<List> listList=new ArrayList<List>();
		//符合条件的清洗明细
		List<CssdWashDetail> cssdWashDetailLists=cssdWashDetailDAO.addUpByCondition2(SessionUtil
				.getUnitsCode(), fparameter.getConditions());
		//符合条件包的数量与包ID
		List<Object> amountList=cssdWashDetailDAO.amountList(SessionUtil
				.getUnitsCode(), fparameter.getConditions());
		listList.add(cssdWashDetailLists);
		listList.add(amountList);
		ro.setData(listList);
		return ro;
	}

	@Override
	public List<CssdPackageDict>  findPackageDictListByClass(String fstrPackageId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物品包列表");
		String unitsCode = SessionUtil.getUnitsCode();

		List<Object> listAy = new ArrayList<Object>();
	
		StringBuilder condition = new StringBuilder();
		condition.append(" where unitsCode = '" + unitsCode + "'");
		condition.append(" and packageId='" + fstrPackageId + "'");
		List<CssdPackageDict> list = cssdPackageDictDAO.findByCondition(
				condition.toString(), 0, 1000);
		listAy.add(list.get(0));
		ro.setData(list);
		return list;
	}

	/**
	 *  根据申请部门查询可用物品包
	 */
	@Override
	public ReObject findSterilizedByProvideAutoId(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据查找条件，根据申请部门查询可用物品包");
		String unitsCode = SessionUtil.getUnitsCode();
//		List<CssdStockMaster> cssdStockMasterList=cssdStockMasterDAO.findSterilizedByProvideAutoId(deptCode, unitsCode);
//		ro.setData(cssdStockMasterList);
		return ro;
	}
	
}
