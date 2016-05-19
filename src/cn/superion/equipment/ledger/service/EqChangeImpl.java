package cn.superion.equipment.ledger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqChangeDetailDAO;
import cn.superion.equipment.dao.EqChangeMasterDAO;
import cn.superion.equipment.dao.EqEquipmentDAO;
import cn.superion.equipment.entity.EqChangeDetail;
import cn.superion.equipment.entity.EqChangeMaster;
import cn.superion.equipment.entity.EqEquipment;
import cn.superion.system.entity.SysUser;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;

/**
 * 设备变更服务实现
 * @author 曹国魁
 *
 */
public class EqChangeImpl implements IEqChange {
	private EqChangeMasterDAO eqChangeMasterDAO;
	private EqChangeDetailDAO eqChangeDetailDAO;
	private EqEquipmentDAO eqEquipmentDAO;
	private IEqBillNoService eqBillNoService;
	
	public EqEquipmentDAO getEqEquipmentDAO() {
		return eqEquipmentDAO;
	}

	public void setEqEquipmentDAO(EqEquipmentDAO eqEquipmentDAO) {
		this.eqEquipmentDAO = eqEquipmentDAO;
	}

	public EqChangeMasterDAO getEqChangeMasterDAO() {
		return eqChangeMasterDAO;
	}

	public void setEqChangeMasterDAO(EqChangeMasterDAO eqChangeMasterDAO) {
		this.eqChangeMasterDAO = eqChangeMasterDAO;
	}

	public EqChangeDetailDAO getEqChangeDetailDAO() {
		return eqChangeDetailDAO;
	}

	public void setEqChangeDetailDAO(EqChangeDetailDAO eqChangeDetailDAO) {
		this.eqChangeDetailDAO = eqChangeDetailDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的变更记录");
		EqChangeMaster master = eqChangeMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此变更记录[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("变更记录已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqChangeDetailDAO.delByAutoId(fstrAutoId);
		eqChangeMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询变更主记录ID列表");
		List<Object> data = eqChangeMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据变更主记录ID查询变更记录");
		EqChangeMaster master = eqChangeMasterDAO.findById(fstrAutoId);
		List<EqChangeDetail> details = eqChangeDetailDAO.findByAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(EqChangeMaster master, List<EqChangeDetail> details) {
		ReObject ro = new ReObject("保存变更记录");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String autoId = master.getAutoId();
		if(autoId == null || "".equals(autoId)){
			//新增
			String billNo = master.getBillNo();
			if(billNo == null || "".equals(billNo)){
				master.setBillNo(eqBillNoService.getNextBillNo("6"));
			}else{
				//校验故障单号唯一
				if(!eqChangeMasterDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的变更记录单号[" + billNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			master.setUnitsCode(unitsCode);
			master.setCurrentStatus("0");
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Date());
			eqChangeMasterDAO.save(master);
			autoId = master.getAutoId();
		}else{
			//修改
			EqChangeMaster original = eqChangeMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此变更记录[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("变更记录已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqChangeMasterDAO.merge(master);
			//先删除明细记录
			eqChangeDetailDAO.delByAutoId(autoId);
		}
		short i = 0;
		for(EqChangeDetail detail : details){
			detail.setAutoId(autoId);
			detail.setSerialNo(++i);
			eqChangeDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的变更记录");
		EqChangeMaster master = eqChangeMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此变更记录[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("变更记录已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		master.setCurrentStatus("1");
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Date());
		String unitsCode = master.getUnitsCode();
		//更新设备台账的内容
		List<EqChangeDetail> details = eqChangeDetailDAO.findByAutoId(fstrAutoId);
		for(EqChangeDetail detail : details){
			String changeContent = detail.getChangeContent();
			String afterContent = detail.getAfterContent();
			EqEquipment eq = eqEquipmentDAO.findByCode(unitsCode,detail.getEquipmentCode());
			if(eq != null){
				if("1".equals(changeContent)){
					//作业部门
					eq.setJobDept(afterContent);
				}else if("2".equals(changeContent)){
					//使用部门
					eq.setUsedDept(afterContent);
				}else if("3".equals(changeContent)){
					//制度时间
					eq.setSystemTime(Double.valueOf(afterContent));
				}else if("4".equals(changeContent)){
					//保修截止时间（yyyy-MM-dd）
					eq.setGuaranteeCutOffDate(DateUtil.getDateComeString(afterContent));
				}else if("5".equals(changeContent)){
					//设备原值
					eq.setOriginalValue(Double.valueOf(afterContent));
				}else if("6".equals(changeContent)){
					//位置
					eq.setPositionCode(afterContent);
				}else if("7".equals(changeContent)){
					//设备状态
					eq.setEquipmentStatus(afterContent);
				}
			}
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

}
