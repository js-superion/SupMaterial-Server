package cn.superion.materialDept.receive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.center.provider.dao.CdProviderMaterialDAO;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.materialDept.dao.MaterialCurrentStockDeptDAO;
import cn.superion.materialDept.dao.MaterialRdsDetailDeptDAO;
import cn.superion.materialDept.dao.MaterialRdsMasterDeptDAO;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 高值耗材入库
 * 
 * @author 芮玉红
 * 
 */
public class ReceiveValueImpl implements IReceiveValue {
	private Log log = LogFactory.getLog(ReceiveValueImpl.class);
	
	private MaterialRdsMasterDeptDAO materialRdsMasterDeptDAO;
	private MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	private CdProviderMaterialDAO cdProviderMaterialDAO;
	private CdMaterialDictDAO cdMaterialDictDAO; 
	private CdSysParamDAO cdSysParamDAO;
	private MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO;
	
	@Override
	public ReObject deleteRds(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的高值耗材入库单据");
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
		ReObject ro = new ReObject("查看当前高值耗材入库单的详细信息记录");
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

	@Override
	public ReObject findRdsMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的高值耗材入库单据列表");
		List<Object> data = materialRdsMasterDeptDAO
				.findAutoIdsBySpecialReceiveCondition(SessionUtil.getUnitsCode(),
						fparameter.getConditions());
		ro.setData(data);
		return ro;
	}



	@Override
	public ReObject saveRds(MaterialRdsMasterDept fmaster,
			List<MaterialRdsDetailDept> fdetails) {
		ReObject ro = new ReObject("保存高值耗材入库信息");

		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		String deptCode =user.getDeptCode();
		
		fmaster.setRdFlag(RdConstant.R);
	
		// 是否校验供应商授权物资
		boolean isNeedAuthorized = "1".equals(cdSysParamDAO.findByParaCode(
				unitsCode, appCode,
				RdConstant.SYS_PARA_CODE_MATERIAL_AUTHORIZATION, "0"));
		String salerCode = fmaster.getSupplyDeptCode();
		for (MaterialRdsDetailDept detail : fdetails) {
			if (isNeedAuthorized && salerCode != null && !"".equals(salerCode)) {
				String materialId = detail.getMaterialId();
				if (!cdProviderMaterialDAO.checkSaleProductPrivilege(
						SessionUtil.getUnitsCode(), salerCode, materialId)) {
					throw new RuntimeException("供应商[" + salerCode + "]没有物资["
							+ materialId + "]的经销授权或授权过期，不能入库！");
				}	
			}
		}
		if(fmaster.getDeptCode()==null || fmaster.getDeptCode().equals(""))
		{
			fmaster.setDeptCode(deptCode);
		}
		List<MaterialRdsDetailDept> detailDepts=new ArrayList<MaterialRdsDetailDept>();
		for(MaterialRdsDetailDept detail:fdetails){
			
			Double detailAmount=detail.getAmount();
			if(fmaster.getInvoiceType().equals("1")){
				detail.setAmount(1d);
				for(int _amount=1;_amount<=detailAmount;_amount++){
					MaterialRdsDetailDept newDetail;
					try {
						newDetail = (MaterialRdsDetailDept) BeanUtils.cloneBean(detail);
						if(newDetail.getTradePrice()!=null){
							newDetail.setTradeMoney(newDetail.getAmount()*newDetail.getTradePrice());
						}
						if(newDetail.getRetailPrice()!=null){
							newDetail.setRetailMoney(newDetail.getAmount()*newDetail.getRetailPrice());
						}
						if(newDetail.getWholeSalePrice()!=null){
							newDetail.setWholeSaleMoney(newDetail.getAmount()*newDetail.getWholeSalePrice());
						}
						if(newDetail.getBarCode()==null || newDetail.getBarCode().trim().length()==0 || newDetail.getBarCode().equals("0") || _amount>1){
							String barCode=materialRdsDetailDeptDAO.nextTimeValueFromDB("SEQ_MATERIAL_RDS_DETAIL_D_BAR", 4);
							newDetail.setBarCode(barCode);
						}
						detailDepts.add(newDetail);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
			else{
				detail.setAmount(-1d);
				for(int _amount=-1;_amount>=detailAmount;_amount--){
					MaterialRdsDetailDept newDetail;
					try {
						newDetail = (MaterialRdsDetailDept) BeanUtils.cloneBean(detail);
						if(newDetail.getTradePrice()!=null){
							newDetail.setTradeMoney(newDetail.getAmount()*newDetail.getTradePrice());
						}
						if(newDetail.getRetailPrice()!=null){
							newDetail.setRetailMoney(newDetail.getAmount()*newDetail.getRetailPrice());
						}
						if(newDetail.getWholeSalePrice()!=null){
							newDetail.setWholeSaleMoney(newDetail.getAmount()*newDetail.getWholeSalePrice());
						}
						if(newDetail.getBarCode()==null || newDetail.getBarCode().trim().length()==0 || newDetail.getBarCode().equals("0") || _amount<-1){
							String barCode=materialRdsDetailDeptDAO.nextTimeValueFromDB("SEQ_MATERIAL_RDS_DETAIL_D_BAR", 4);
							newDetail.setBarCode(barCode);
						}
						detailDepts.add(newDetail);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
			
		}
		//业务类型——受托代销
		fmaster.setOperationType(RdConstant.R_AGENCY);
		Object[] obj=deptCommMaterialServiceImpl.save(fmaster, detailDepts);
		
		List<Object> data = new ArrayList<Object>();
		data.add(obj[0]);
		data.add(obj[1]);
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
	
	@Override
	public ReObject cancelVerifyRds(String fstrAutoId){
		ReObject ro = new ReObject("弃审");
		
		MaterialRdsMasterDept master = materialRdsMasterDeptDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的入库主记录！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已记帐，不能删除！");
		}
		List<MaterialRdsDetailDept> detailDepts=materialRdsDetailDeptDAO.findByMainAutoId(fstrAutoId);
		for(MaterialRdsDetailDept detail:detailDepts){
			String barCode=detail.getBarCode();
			if(barCode!=null && !barCode.equals("")){
				MaterialCurrentStockDept curDept=materialCurrentStockDeptDAO.findByBarCode(barCode);
				if(curDept !=null && curDept.getAmount()<=0){
					throw new RuntimeException("该单据中的物资barCode:["+barCode+"]已被病人使用不能弃审！");
				}
			}
		}
		
		deptCommMaterialServiceImpl.cancelVerify(fstrAutoId);
		return ro;
	}

	
    public	ReObject findValueMaterialDict(ParameterObject fparam){
    	ReObject ro = new ReObject("查找耗材类物资字典");
		
    	Map<String, Object> map=fparam.getConditions();
    	String phoInputCode=(String) map.get("phoInputCode");
    	String fiveInputCode=(String) map.get("fiveInputCode");
    	String highValueSign=(String) map.get("highValueSign");
    	String agentSign = (String) map.get("agentSign");
    	String storageCode = (String) map.get("storageCode");
    	
    	// 单位编码
		String unitsCode = (String) map.get("comboValue");
		if(unitsCode==null || unitsCode.equals("")){
			unitsCode=SessionUtil.getUnitsCode();
		}
    	
    	StringBuilder condition=new StringBuilder(" where unitsCode = '"+unitsCode+"'");
    	int start=fparam.getStart();
    	int limits=fparam.getItemsPerPage();
    	
    	if(phoInputCode!=null && !phoInputCode.equals("")){
    		phoInputCode.toLowerCase();
    		condition.append(" and (phoInputCode like '%"+phoInputCode+"%' or generalPhoInputCode like '%"+phoInputCode+"%')");
    	}
    	else if(fiveInputCode!=null && !fiveInputCode.equals("")){
    		fiveInputCode.toLowerCase();
    		condition.append(" and (fiveInputCode like '%"+fiveInputCode+"%' or generalFiveInputCode like '%"+fiveInputCode+"%')");
    	}
    	if(highValueSign!=null && !highValueSign.equals("")){
    		condition.append(" and highValueSign = '"+highValueSign+"'");
    	}
    	if(agentSign!=null && !agentSign.equals("")){
    		condition.append(" and agentSign = '"+agentSign+"'");
    	}
    	if(storageCode!=null && !storageCode.equals("")){
    		condition.append(" and storageDefault = '"+storageCode+"'");
    	}
    	
    	List<CdMaterialDict> materialDictList=cdMaterialDictDAO.findByCondition(condition.toString(), start, limits);
	    int count=cdMaterialDictDAO.countByCondition(condition.toString());
    	ro.setData(materialDictList);
    	ro.setCount(count,limits);
		return ro;
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

	public MaterialRdsMasterDeptDAO getMaterialRdsMasterDeptDAO() {
		return materialRdsMasterDeptDAO;
	}

	public void setMaterialRdsMasterDeptDAO(
			MaterialRdsMasterDeptDAO materialRdsMasterDeptDAO) {
		this.materialRdsMasterDeptDAO = materialRdsMasterDeptDAO;
	}

	public CdProviderMaterialDAO getCdProviderMaterialDAO() {
		return cdProviderMaterialDAO;
	}

	public void setCdProviderMaterialDAO(CdProviderMaterialDAO cdProviderMaterialDAO) {
		this.cdProviderMaterialDAO = cdProviderMaterialDAO;
	}

	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}


	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}


	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}


	public MaterialCurrentStockDeptDAO getMaterialCurrentStockDeptDAO() {
		return materialCurrentStockDeptDAO;
	}


	public void setMaterialCurrentStockDeptDAO(
			MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO) {
		this.materialCurrentStockDeptDAO = materialCurrentStockDeptDAO;
	}

}
