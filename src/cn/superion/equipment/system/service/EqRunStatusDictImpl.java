package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqRunStatusDictDAO;
import cn.superion.equipment.entity.EqRunStatusDict;
/**
 * 设备运行状态字典服务实现
 * @author 曹国魁
 *
 */
public class EqRunStatusDictImpl implements IEqRunStatusDict {
	private EqRunStatusDictDAO eqRunStatusDictDAO;


	public EqRunStatusDictDAO getEqRunStatusDictDAO() {
		return eqRunStatusDictDAO;
	}

	public void setEqRunStatusDictDAO(EqRunStatusDictDAO eqRunStatusDictDAO) {
		this.eqRunStatusDictDAO = eqRunStatusDictDAO;
	}

	@Override
	public ReObject del(String statusCode) {
		ReObject ro = new ReObject("删除设备运行状态");
		EqRunStatusDict dict = eqRunStatusDictDAO.findById(statusCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备运行状态[编码："+statusCode+"]！");
		}
		eqRunStatusDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的设备运行状态");
		List<EqRunStatusDict> data = eqRunStatusDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqRunStatusDict eqRunStatusDict) {
		ReObject ro = new ReObject("保存设备运行状态");
		if(isAdd){
			String code = eqRunStatusDict.getStatusCode();
			if(eqRunStatusDictDAO.findById(code) == null){
				eqRunStatusDictDAO.save(eqRunStatusDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqRunStatusDictDAO.update(eqRunStatusDict);
		}
		return ro;
	}

}
