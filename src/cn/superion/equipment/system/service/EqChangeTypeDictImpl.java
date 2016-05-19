package cn.superion.equipment.system.service;

import java.util.ArrayList;
import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqChangeTypeDictDAO;
import cn.superion.equipment.entity.EqChangeTypeDict;
/**
 * 变更类型服务实现
 * @author 曹国魁
 *
 */
public class EqChangeTypeDictImpl implements IEqChangeTypeDict {
	private EqChangeTypeDictDAO eqChangeTypeDictDAO;
	
	public EqChangeTypeDictDAO getEqChangeTypeDictDAO() {
		return eqChangeTypeDictDAO;
	}

	public void setEqChangeTypeDictDAO(EqChangeTypeDictDAO eqChangeTypeDictDAO) {
		this.eqChangeTypeDictDAO = eqChangeTypeDictDAO;
	}

	@Override
	public ReObject del(String typeCode) {
		ReObject ro = new ReObject("删除变更类型");
		EqChangeTypeDict dict = eqChangeTypeDictDAO.findById(typeCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备变更类型[编码："+typeCode+"]！");
		}
		eqChangeTypeDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的变更类型");
		List<EqChangeTypeDict> data = eqChangeTypeDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqChangeTypeDict eqChangeTypeDict) {
		ReObject ro = new ReObject("保存变更类型");
		if(isAdd){
			String code = eqChangeTypeDict.getTypeCode();
			if(eqChangeTypeDictDAO.findById(code) == null){
				eqChangeTypeDictDAO.save(eqChangeTypeDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqChangeTypeDictDAO.update(eqChangeTypeDict);
		}
		return ro;
	}

	@Override
	public ReObject findByCode(String typeCode) {
		ReObject ro = new ReObject("根据类型编码查找变更类型");
		EqChangeTypeDict dict = eqChangeTypeDictDAO.findById(typeCode);
		List<Object> data = new ArrayList<Object>();
		data.add(dict);
		ro.setData(data);
		return ro;
	}

}
