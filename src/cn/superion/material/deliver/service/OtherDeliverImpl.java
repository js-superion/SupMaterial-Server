package cn.superion.material.deliver.service;

import java.util.ArrayList;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 其他出库服务实现
 * @author 曹国魁
 *
 */
public class OtherDeliverImpl implements IOtherDeliver {
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService commMaterialServiceImpl;
	public MaterialRdsMasterDAO getMaterialRdsMasterDAO() {
		return materialRdsMasterDAO;
	}

	public void setMaterialRdsMasterDAO(MaterialRdsMasterDAO materialRdsMasterDAO) {
		this.materialRdsMasterDAO = materialRdsMasterDAO;
	}

	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	@Override
	public ReObject deleteRdsOther(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的其他出库单据");
		commMaterialServiceImpl.deleteByAutoId(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject findOtherDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前其他出库单的详细信息记录");
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		data.add(objs[1]);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findOtherMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的其他出库单据列表");
		List<Object> data = materialRdsMasterDAO
		.findAutoIdsByOtherDeliverCondition(SessionUtil.getUnitsCode(),
				fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsDetailByMainAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前其他出库单的详细信息记录");
		List<MaterialRdsDetail> data = materialRdsDetailDAO
		.findByMainAutoId(fstrAutoId);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findRdsMasterOtherByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤其他未审核的出库单据");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = materialRdsMasterDAO
				.findMasterByOtherDeliverCondition(start, limit,
						SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject saveRdsOther(MaterialRdsMaster fmaster,
			List<MaterialRdsDetail> fdetails) {
		ReObject ro = new ReObject("保存当前其他出库单信息");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,appCode,RdConstant.SYS_PARA_CODE_D_OTHERS_CHK,"0"));
		fmaster.setCurrentStatus(isVerified?"1":"0");
		fmaster.setRdFlag(RdConstant.D);
		commMaterialServiceImpl.save(fmaster ,fdetails);
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verifyRdsOther(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的其他出库单信息");
		Object[] objs = commMaterialServiceImpl.verify(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject cancelVerifyRds(String fstrAutoId){
		ReObject ro = new ReObject("弃审");
		commMaterialServiceImpl.cancelVerify(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject updateRdsPrintSign(String fstrAutoId) {
		ReObject ro = new ReObject("更新打印状态！");
		commMaterialServiceImpl.updatePrintSign(fstrAutoId);
		return ro;
	}
}
