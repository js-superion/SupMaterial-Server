package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqFaultTypeDictDAO;
import cn.superion.equipment.entity.EqFaultTypeDict;
/**
 * 故障类型服务实现
 * @author 曹国魁
 *
 */
public class EqFaultTypeDictImpl implements IEqFaultTypeDict {
	private EqFaultTypeDictDAO eqFaultTypeDictDAO;	


	public EqFaultTypeDictDAO getEqFaultTypeDictDAO() {
		return eqFaultTypeDictDAO;
	}

	public void setEqFaultTypeDictDAO(EqFaultTypeDictDAO eqFaultTypeDictDAO) {
		this.eqFaultTypeDictDAO = eqFaultTypeDictDAO;
	}

	@Override
	public ReObject del(String typeCode) {
		ReObject ro = new ReObject("删除故障类型");
		EqFaultTypeDict dict = eqFaultTypeDictDAO.findById(typeCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备故障类型[编码："+typeCode+"]！");
		}
		eqFaultTypeDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的故障类型");
		List<EqFaultTypeDict> data = eqFaultTypeDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqFaultTypeDict eqFaultTypeDict) {
		ReObject ro = new ReObject("保存故障类型");
		if(isAdd){
			String code = eqFaultTypeDict.getTypeCode();
			if(eqFaultTypeDictDAO.findById(code) == null){
				eqFaultTypeDictDAO.save(eqFaultTypeDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqFaultTypeDictDAO.update(eqFaultTypeDict);
		}
		return ro;
	}

}
