package cn.superion.equipment.ledger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqEquipmentTypeDAO;
import cn.superion.equipment.entity.EqEquipmentType;
import cn.superion.util.SessionUtil;

/**
 * 设备类型台账服务实现
 * @author 曹国魁
 *
 */
public class EquipmentTypeImpl implements IEquipmentType {
	private EqEquipmentTypeDAO eqEquipmentTypeDAO;
	private IEqBillNoService eqBillNoService;
	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	public EqEquipmentTypeDAO getEqEquipmentTypeDAO() {
		return eqEquipmentTypeDAO;
	}

	public void setEqEquipmentTypeDAO(EqEquipmentTypeDAO eqEquipmentTypeDAO) {
		this.eqEquipmentTypeDAO = eqEquipmentTypeDAO;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的设备类型台账");
		EqEquipmentType et = eqEquipmentTypeDAO.findById(fstrAutoId);
		if(et == null){
			throw new RuntimeException("不存在此设备类型台账[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(et.getCurrentStatus())){
			throw new RuntimeException("设备类型台账已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqEquipmentTypeDAO.delete(et);
		return ro;
	}

	@Override
	public ReObject findByClassCode(String classCode) {
		ReObject ro = new ReObject("查询设备类别下的设备类型台账");
		List<EqEquipmentType> data = eqEquipmentTypeDAO.findByClassCode(classCode);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(EqEquipmentType eqType) {
		ReObject ro = new ReObject("保存设备类型台账");
		String typeCode = eqType.getEquipmentTypeCode();
		/*if(typeCode == null || "".equals(typeCode.trim())){
			throw new RuntimeException("设备类型编码不能为空！");
		}*/
		String autoId = eqType.getAutoId();
		if(autoId == null || "".equals(autoId)){
			//新增
			//校验类型编码唯一
			if(typeCode == null || "".equals(typeCode)){
				typeCode = eqBillNoService.getNextBillNo("8");
				eqType.setEquipmentTypeCode(typeCode);
			}else if(!eqEquipmentTypeDAO.checkTypeCodeUnique(typeCode)){
				throw new RuntimeException("设备类型编码["+typeCode+"]已存在,不能保存！");
			}
			
			eqType.setCurrentStatus("0");
			eqType.setMaker(SessionUtil.getPersonId());
			eqType.setMakeDate(new Date());
			eqEquipmentTypeDAO.save(eqType);
		}else{
			//修改
			//校验审核状态
			EqEquipmentType original = eqEquipmentTypeDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此设备类型台账[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("设备类型台账已审核[系统标识号："+autoId+"]，不能修改！");
			}
			//校验类型编码唯一
			if(!eqEquipmentTypeDAO.checkTypeCodeUnique(typeCode,autoId)){
				throw new RuntimeException("设备类型编码["+typeCode+"]已存在,不能保存！");
			}
			eqEquipmentTypeDAO.merge(eqType);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(eqType);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的设备类型台账");
		EqEquipmentType et = eqEquipmentTypeDAO.findById(fstrAutoId);
		if(et == null){
			throw new RuntimeException("不存在此设备类型台账[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(et.getCurrentStatus())){
			throw new RuntimeException("设备类型台账已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		et.setCurrentStatus("1");
		et.setVerifier(SessionUtil.getPersonId());
		et.setVerifyDate(new Date());
		List<Object> data = new ArrayList<Object>();
		data.add(et);
		ro.setData(data);
		return ro;
	}

}
