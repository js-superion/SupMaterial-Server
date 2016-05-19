package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqJobTypeDictDAO;
import cn.superion.equipment.entity.EqJobTypeDict;
/**
 * 作业类型服务实现
 * @author 曹国魁
 *
 */
public class EqJobTypeDictImpl implements IEqJobTypeDict {
	private EqJobTypeDictDAO eqJobTypeDictDAO;	
	public EqJobTypeDictDAO getEqJobTypeDictDAO() {
		return eqJobTypeDictDAO;
	}

	public void setEqJobTypeDictDAO(EqJobTypeDictDAO eqJobTypeDictDAO) {
		this.eqJobTypeDictDAO = eqJobTypeDictDAO;
	}

	@Override
	public ReObject del(String typeCode) {
		ReObject ro = new ReObject("删除作业类型");
		EqJobTypeDict dict = eqJobTypeDictDAO.findById(typeCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备作业类型[编码："+typeCode+"]！");
		}
		eqJobTypeDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的作业类型");
		List<EqJobTypeDict> data = eqJobTypeDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqJobTypeDict eqJobTypeDict) {
		ReObject ro = new ReObject("保存作业类型");
		if(isAdd){
			String code = eqJobTypeDict.getTypeCode();
			if(eqJobTypeDictDAO.findById(code) == null){
				eqJobTypeDictDAO.save(eqJobTypeDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqJobTypeDictDAO.update(eqJobTypeDict);
		}
		return ro;
	}

}
