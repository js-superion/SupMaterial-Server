package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqJobGroupDictDAO;
import cn.superion.equipment.entity.EqJobGroupDict;
/**
 * 作业小组服务实现
 * @author 曹国魁
 *
 */
public class EqJobGroupDictImpl implements IEqJobGroupDict {
	private EqJobGroupDictDAO eqJobGroupDictDAO;	


	public EqJobGroupDictDAO getEqJobGroupDictDAO() {
		return eqJobGroupDictDAO;
	}

	public void setEqJobGroupDictDAO(EqJobGroupDictDAO eqJobGroupDictDAO) {
		this.eqJobGroupDictDAO = eqJobGroupDictDAO;
	}

	@Override
	public ReObject del(String jobCode) {
		ReObject ro = new ReObject("删除作业小组");
		EqJobGroupDict dict = eqJobGroupDictDAO.findById(jobCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备作业小组[编码："+jobCode+"]！");
		}
		eqJobGroupDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的作业小组");
		List<EqJobGroupDict> data = eqJobGroupDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqJobGroupDict eqJobGroupDict) {
		ReObject ro = new ReObject("保存作业小组");
		if(isAdd){
			String code = eqJobGroupDict.getJobCode();
			if(eqJobGroupDictDAO.findById(code) == null){
				eqJobGroupDictDAO.save(eqJobGroupDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqJobGroupDictDAO.update(eqJobGroupDict);
		}
		return ro;
	}

}
