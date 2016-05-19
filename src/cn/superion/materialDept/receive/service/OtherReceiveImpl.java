package cn.superion.materialDept.receive.service;

import java.util.ArrayList;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.materialDept.dao.MaterialRdsDetailDeptDAO;
import cn.superion.materialDept.dao.MaterialRdsMasterDeptDAO;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 其他入库服务实现
 * @author 曹国魁
 *
 */
public class OtherReceiveImpl implements IOtherReceive {
	private MaterialRdsMasterDeptDAO materialRdsMasterDeptDAO; 
	private MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public MaterialRdsMasterDeptDAO getMaterialRdsMasterDeptDAO() {
		return materialRdsMasterDeptDAO;
	}

	public void setMaterialRdsMasterDeptDAO(
			MaterialRdsMasterDeptDAO materialRdsMasterDeptDAO) {
		this.materialRdsMasterDeptDAO = materialRdsMasterDeptDAO;
	}

	public MaterialRdsDetailDeptDAO getMaterialRdsDetailDeptDAO() {
		return materialRdsDetailDeptDAO;
	}

	public void setMaterialRdsDetailDeptDAO(
			MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO) {
		this.materialRdsDetailDeptDAO = materialRdsDetailDeptDAO;
	}

	public ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	@Override
	public ReObject deleteRds(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的入库单据");
		MaterialRdsMasterDept master = materialRdsMasterDeptDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的入库主记录！");
		}
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已审核，不能删除！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已记帐，不能删除！");
		}
		deptCommMaterialServiceImpl.deleteByAutoId(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject findRdsDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前其他入库单的详细信息记录");
		Object[] objs = deptCommMaterialServiceImpl.findById(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		data.add(objs[1]);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsDetailByMainAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据主记录ID查询收发存明细");
		List<MaterialRdsDetailDept> data = materialRdsDetailDeptDAO
				.findByMainAutoId(fstrAutoId);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findRdsMasterByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询盘盈入库(104)的生单");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		Object[] objs = materialRdsMasterDeptDAO
				.findUnverifiedMasterByCheckReceiveCondition(start, limit,
						user.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findRdsMasterInitiallList(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的期初入库单据列表");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<Object> data = materialRdsMasterDeptDAO
			.findAutoIdsByInitialCondition(user.getUnitsCode(),
				fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的其他入库单据列表");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<Object> data = materialRdsMasterDeptDAO
				.findAutoIdsByOtherReceiveCondition(user.getUnitsCode(),
						fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveInitialRds(MaterialRdsMasterDept fmaster,
			List<MaterialRdsDetailDept> fdetails) {
		ReObject ro = new ReObject("保存当前期初入库信息");
		fmaster.setOperationType(RdConstant.R_INITIAL);
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_INITIAL_CHK, "0"));
		fmaster.setCurrentStatus(isVerified ? "1" : "0");
		fmaster.setRdFlag(RdConstant.R);
		if(fmaster.getRdType() == null || fmaster.getRdType().equals(""))
			fmaster.setRdType("103");
		deptCommMaterialServiceImpl.save(fmaster, fdetails);
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveOtherRds(MaterialRdsMasterDept fmaster,
			List<MaterialRdsDetailDept> fdetails) {
		ReObject ro = new ReObject("保存当前其他入库信息");
		String operationType = fmaster.getOperationType();
		if (!operationType.equals(RdConstant.R_CHECK_PROFIT)
				&& !operationType.equals(RdConstant.R_OTHERS)) {
			throw new RuntimeException("业务类型必须为104或109！");
		}
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_R_OTHERS_CHK, "0"));
		fmaster.setCurrentStatus(isVerified ? "1" : "0");
		fmaster.setRdFlag(RdConstant.R);
		deptCommMaterialServiceImpl.save(fmaster, fdetails);
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verifyRds(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的入库单信息");
		Object[] objs = deptCommMaterialServiceImpl.verify(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		ro.setData(data);
		return ro;
	}

}
