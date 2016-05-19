package cn.superion.equipment.spare.service;
import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqUnitDictDAO;
import cn.superion.equipment.entity.EqUnitDict;
public class EqUnitDictImpl implements IEqUnitDict {
	private EqUnitDictDAO eqUnitDictDAO;
	
	public EqUnitDictDAO getEqUnitDictDAO() {
		return eqUnitDictDAO;
	}

	public void setEqUnitDictDAO(EqUnitDictDAO eqUnitDictDAO) {
		this.eqUnitDictDAO = eqUnitDictDAO;
	}

	@Override
	public ReObject findAll() {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("");
		List<EqUnitDict> data = eqUnitDictDAO.findAll();
		ro.setData(data);
		StringBuilder s = new StringBuilder();
		return ro;
	}

	@Override
	public ReObject del(EqUnitDict obj) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("");
		eqUnitDictDAO.delete(obj);
		return ro;
	}

	@Override
	public ReObject save(EqUnitDict obj) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("保存");
		EqUnitDict item = eqUnitDictDAO.findById(obj.getUnitcode());
		if(item!=null){
			//更新
			eqUnitDictDAO.merge(obj);
		}else{
			
			eqUnitDictDAO.save(obj);
		}
		return ro;
	}

}
