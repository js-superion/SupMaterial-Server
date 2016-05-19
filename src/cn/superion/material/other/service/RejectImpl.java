package cn.superion.material.other.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialRejectDetailDAO;
import cn.superion.material.dao.MaterialRejectMasterDAO;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.material.entity.MaterialRejectDetail;
import cn.superion.material.entity.MaterialRejectMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 物资报损服务实现
 * @author 曹国魁
 *
 */
public class RejectImpl implements IReject {
	private MaterialRejectMasterDAO materialRejectMasterDAO;
	private MaterialRejectDetailDAO materialRejectDetailDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService commMaterialServiceImpl;
	public MaterialRejectMasterDAO getMaterialRejectMasterDAO() {
		return materialRejectMasterDAO;
	}

	public void setMaterialRejectMasterDAO(
			MaterialRejectMasterDAO materialRejectMasterDAO) {
		this.materialRejectMasterDAO = materialRejectMasterDAO;
	}

	public MaterialRejectDetailDAO getMaterialRejectDetailDAO() {
		return materialRejectDetailDAO;
	}

	public void setMaterialRejectDetailDAO(
			MaterialRejectDetailDAO materialRejectDetailDAO) {
		this.materialRejectDetailDAO = materialRejectDetailDAO;
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
	public ReObject deleteReject(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的物资报损单");
		MaterialRejectMaster master = materialRejectMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("物资报损单不存在！");
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("物资报损记录已审核，不能删除！");
		}
		materialRejectDetailDAO.delByMainAutoId(fstrAutoId);
		materialRejectMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findRejectDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前物资报损单的详细信息记录");
		MaterialRejectMaster master = materialRejectMasterDAO.findById(fstrAutoId);
		List<MaterialRejectDetail> details = materialRejectDetailDAO.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRejectMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物资报损单据列表");
		List<Object> data = materialRejectMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveReject(MaterialRejectMaster fmaster,
			List<MaterialRejectDetail> fdetails) {
		ReObject ro = new ReObject("保存当前物资报损单信息");
		if(fmaster == null)
			throw new RuntimeException("物资报损单主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("物资报损单明细记录不能为空！");
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
				fmaster.setBillNo(commMaterialServiceImpl.getNextBillNo(RdConstant.OTHERS,storageCode));
			}else{
				//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if(!materialRejectMasterDAO.checkBillNoUnique(unitsCode,storageCode,billNo)){
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
			materialRejectMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialRejectMaster original = materialRejectMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的物资报损单主记录！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("物资报损单已审核，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			materialRejectMasterDAO.merge(fmaster);
			materialRejectDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for(MaterialRejectDetail detail : fdetails){
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			materialRejectDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verifyReject(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物资报损单");
		MaterialRejectMaster original = materialRejectMasterDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的物资报损单！");
		}
		if("1".equals(original.getCurrentStatus())){
			throw new RuntimeException("物资报损单已审核，不能审核！");
		}
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		String appCode = SessionUtil.getAppCode();
		//写出库单
		//是否要更新库存
//		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(original.getUnitsCode(), appCode, RdConstant.SYS_PARA_CODE_REJECT_LAUNCH,"0"));
		boolean isVerified = true;
		MaterialRdsMaster master = new MaterialRdsMaster();
		master.setUnitsCode(original.getUnitsCode());
		master.setStorageCode(original.getStorageCode());
		//单据类型
		master.setInvoiceType("1");
		//收发标志
		master.setRdFlag(RdConstant.D);
		//收发类别
		master.setRdType(original.getRdType());
		//业务类型
		master.setOperationType(RdConstant.D_REJECT);
		//业务号
		master.setOperationNo(original.getBillNo());
		master.setCurrentStatus(isVerified?"1":"0");
		List<MaterialRejectDetail> rejectDetails =  materialRejectDetailDAO.findByMainAutoId(fstrAutoId);
		List<MaterialRdsDetail> details = new ArrayList<MaterialRdsDetail>();
		for(MaterialRejectDetail rejectDetail : rejectDetails){
			MaterialRdsDetail rdsDetail = rejectDetail.buildMaterialRdsDetail();
			details.add(rdsDetail);
		}
		commMaterialServiceImpl.save(master, details);
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}
	

}
