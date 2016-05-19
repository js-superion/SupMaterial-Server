package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqFaultReasonDictDAO;
import cn.superion.equipment.entity.EqFaultReasonDict;
/**
 * 故障原因服务实现
 * @author 曹国魁
 *
 */
public class EqFaultReasonDictImpl implements IEqFaultReasonDict {
	private EqFaultReasonDictDAO eqFaultReasonDictDAO;	

	public EqFaultReasonDictDAO getEqFaultReasonDictDAO() {
		return eqFaultReasonDictDAO;
	}

	public void setEqFaultReasonDictDAO(EqFaultReasonDictDAO eqFaultReasonDictDAO) {
		this.eqFaultReasonDictDAO = eqFaultReasonDictDAO;
	}

	@Override
	public ReObject del(String reasonCode) {
		ReObject ro = new ReObject("删除故障原因");
		EqFaultReasonDict dict = eqFaultReasonDictDAO.findById(reasonCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备故障原因[编码："+reasonCode+"]！");
		}
		eqFaultReasonDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的故障原因");
		List<EqFaultReasonDict> data = eqFaultReasonDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqFaultReasonDict eqFaultReasonDict) {
		ReObject ro = new ReObject("保存故障原因");
		if(isAdd){
			String code = eqFaultReasonDict.getReasonCode();
			if(eqFaultReasonDictDAO.findById(code) == null){
				eqFaultReasonDictDAO.save(eqFaultReasonDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqFaultReasonDictDAO.update(eqFaultReasonDict);
		}
		return ro;
	}

}
