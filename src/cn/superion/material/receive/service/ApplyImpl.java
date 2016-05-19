package cn.superion.material.receive.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialApplyDetailDAO;
import cn.superion.material.dao.MaterialApplyMasterDAO;
import cn.superion.material.entity.MaterialApplyDetail;
import cn.superion.material.entity.MaterialApplyMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 特殊入库申请服务实现
 * @author 曹国魁
 *
 */
public class ApplyImpl implements IApply {
	private MaterialApplyMasterDAO materialApplyMasterDAO;
	private MaterialApplyDetailDAO materialApplyDetailDAO;
	private ICommMaterialService commMaterialServiceImpl;
	public MaterialApplyMasterDAO getMaterialApplyMasterDAO() {
		return materialApplyMasterDAO;
	}

	public void setMaterialApplyMasterDAO(
			MaterialApplyMasterDAO materialApplyMasterDAO) {
		this.materialApplyMasterDAO = materialApplyMasterDAO;
	}

	public MaterialApplyDetailDAO getMaterialApplyDetailDAO() {
		return materialApplyDetailDAO;
	}

	public void setMaterialApplyDetailDAO(
			MaterialApplyDetailDAO materialApplyDetailDAO) {
		this.materialApplyDetailDAO = materialApplyDetailDAO;
	}

	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	@Override
	public ReObject deleteApply(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的特殊入库申请");
		MaterialApplyMaster master = materialApplyMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("特殊入库申请不存在！");
		if(!"0".equals(master.getCurrentStatus())){
			throw new RuntimeException("特殊入库申请已审核或入库，不能删除！");
		}
		materialApplyDetailDAO.delByMainAutoId(fstrAutoId);
		materialApplyMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findApplyDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看特殊入库单据详细信息记录");
		MaterialApplyMaster master = materialApplyMasterDAO.findById(fstrAutoId);
		List<MaterialApplyDetail> details = materialApplyDetailDAO.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findApplyListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询特殊入库单据信息");
		List<Object> data = materialApplyMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveApply(MaterialApplyMaster fmaster,
			List<MaterialApplyDetail> fdetails) {
		ReObject ro = new ReObject("保存特殊入库信息");
		if(fmaster == null)
			throw new RuntimeException("特殊入库主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("特殊入库明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		Date curDate = new Date();
		String storageCode = fmaster.getStorageCode();
		if(storageCode == null || "".equals(storageCode)){
			storageCode = "0";
			fmaster.setStorageCode(storageCode);
		}
		String autoId = fmaster.getAutoId(); 
		if(autoId == null || "".equals(autoId)){
			//新增
			String billNo = fmaster.getBillNo();
			if(billNo == null || "".equals(billNo)){
				fmaster.setBillNo(commMaterialServiceImpl.getNextBillNo(RdConstant.R,storageCode));
			}else{
				//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if(!materialApplyMasterDAO.checkBillNoUnique(unitsCode,storageCode,billNo)){
					throw new RuntimeException("手工输入的单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"]下有重复");
				}
			}
			if(fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if(fmaster.getPersonId() == null || "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			fmaster.setCurrentStatus("0");
			materialApplyMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialApplyMaster original = materialApplyMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的特殊入库主记录！");
			}
			if(!"0".equals(original.getCurrentStatus())){
				throw new RuntimeException("特殊入库申请已审核或入库，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			materialApplyMasterDAO.merge(fmaster);
			materialApplyDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for(MaterialApplyDetail detail : fdetails){
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			materialApplyDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject verifyApply(String fstrAutoId) {
		ReObject ro = new ReObject("审核特殊入库");
		MaterialApplyMaster original = materialApplyMasterDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的特殊入库主记录！");
		}
		if(!"0".equals(original.getCurrentStatus())){
			throw new RuntimeException("特殊入库申请已审核或入库，不能审核！");
		}
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		original.setCurrentStatus("1");
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

}
