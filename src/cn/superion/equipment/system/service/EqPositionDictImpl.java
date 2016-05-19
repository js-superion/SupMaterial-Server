package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqPositionDictDAO;
import cn.superion.equipment.entity.EqPositionDict;
/**
 * 设备位置字典服务实现
 * @author 曹国魁
 *
 */
public class EqPositionDictImpl implements IEqPositionDict {
	private EqPositionDictDAO eqPositionDictDAO;

	public EqPositionDictDAO getEqPositionDictDAO() {
		return eqPositionDictDAO;
	}

	public void setEqPositionDictDAO(EqPositionDictDAO eqPositionDictDAO) {
		this.eqPositionDictDAO = eqPositionDictDAO;
	}

	@Override
	public ReObject del(String positionCode) {
		ReObject ro = new ReObject("删除设备位置");
		EqPositionDict dict = eqPositionDictDAO.findById(positionCode);
		if(dict == null){
			throw new RuntimeException("不存在此设备位置[编码："+positionCode+"]！");
		}
		List<EqPositionDict> subDicts = eqPositionDictDAO.findByProperty("parentCode", positionCode);
		if(subDicts != null && !subDicts.isEmpty()){
			throw new RuntimeException("此设备位置存在子设备位置，不能删除！");
		}
		eqPositionDictDAO.delete(dict);
		//还原上级类别的末级标志为1
		if (!"1".equals(dict.getCodeLevel())) {
			EqPositionDict parentDict = eqPositionDictDAO.findById(dict
					.getParentCode());
			if (parentDict != null) {
				parentDict.setEndSign("1");
			}
		}
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的设备位置");
		List<EqPositionDict> data = eqPositionDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd,EqPositionDict eqPositionDict) {
		ReObject ro = new ReObject("保存设备位置");
		if(isAdd){
			eqPositionDict.setEndSign("1");
			String code = eqPositionDict.getPositionCode();
			if(eqPositionDictDAO.findById(code) == null){
				eqPositionDictDAO.save(eqPositionDict);
				//更新上级类别的末级标志为0
				if (!"1".equals(eqPositionDict.getCodeLevel())) {
					EqPositionDict parentDict = eqPositionDictDAO
							.findById(eqPositionDict.getParentCode());
					if (parentDict != null) {
						parentDict.setEndSign("0");
					}
				}
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqPositionDictDAO.update(eqPositionDict);
		}
		return ro;
	}

}
