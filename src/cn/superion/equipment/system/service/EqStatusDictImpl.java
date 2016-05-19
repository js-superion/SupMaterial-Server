package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqStatusDictDAO;
import cn.superion.equipment.entity.EqStatusDict;
/**
 * 设备状态字典服务实现
 * @author 曹国魁
 *
 */
public class EqStatusDictImpl implements IEqStatusDict {
	private EqStatusDictDAO eqStatusDictDAO;
	public EqStatusDictDAO getEqStatusDictDAO() {
		return eqStatusDictDAO;
	}

	public void setEqStatusDictDAO(EqStatusDictDAO eqStatusDictDAO) {
		this.eqStatusDictDAO = eqStatusDictDAO;
	}

	@Override
	public ReObject del(String statusCode) {
		ReObject ro = new ReObject("删除设备状态");
		EqStatusDict dict = eqStatusDictDAO.findById(statusCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备状态[编码："+statusCode+"]！");
		}
		eqStatusDictDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的设备状态");
		List<EqStatusDict> data = eqStatusDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqStatusDict eqStatusDict) {
		ReObject ro = new ReObject("保存设备状态");
		if(isAdd){
			String code = eqStatusDict.getStatusCode();
			if(eqStatusDictDAO.findById(code) == null){
				eqStatusDictDAO.save(eqStatusDict);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqStatusDictDAO.update(eqStatusDict);
		}
		return ro;
	}

}
