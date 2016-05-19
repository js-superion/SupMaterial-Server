package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqClassAbcDictDAO;
import cn.superion.equipment.entity.EqClassAbcDict;
/**
 * 设备ABC类别字典服务实现
 * @author 曹国魁
 *
 */
public class EqClassAbcDictImpl implements IEqClassAbcDict {
	private EqClassAbcDictDAO eqClassAbcDictDAO;
	

	public EqClassAbcDictDAO getEqClassAbcDictDAO() {
		return eqClassAbcDictDAO;
	}

	public void setEqClassAbcDictDAO(EqClassAbcDictDAO eqClassAbcDictDAO) {
		this.eqClassAbcDictDAO = eqClassAbcDictDAO;
	}

	@Override
	public ReObject del(String classCode) {
		ReObject ro = new ReObject("删除设备ABC类别");
		EqClassAbcDict dict = eqClassAbcDictDAO.findById(classCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备ABC类别[编码："+classCode+"]！");
		}
		eqClassAbcDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的设备ABC类别");
		List<EqClassAbcDict> data = eqClassAbcDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd,EqClassAbcDict eqClassAbcDict) {
		ReObject ro = new ReObject("保存设备ABC类别");
		if(isAdd){
			String code = eqClassAbcDict.getClassCode();
			if(eqClassAbcDictDAO.findById(code) == null){
				eqClassAbcDictDAO.save(eqClassAbcDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqClassAbcDictDAO.update(eqClassAbcDict);
		}
		return ro;
	}

}
