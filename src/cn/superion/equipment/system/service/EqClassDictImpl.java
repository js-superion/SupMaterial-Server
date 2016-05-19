package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqClassDictDAO;
import cn.superion.equipment.entity.EqClassDict;

/**
 * 设备类别字典服务实现
 * 
 * @author 曹国魁
 * 
 */
public class EqClassDictImpl implements IEqClassDict {
	private EqClassDictDAO eqClassDictDAO;

	public EqClassDictDAO getEqClassDictDAO() {
		return eqClassDictDAO;
	}

	public void setEqClassDictDAO(EqClassDictDAO eqClassDictDAO) {
		this.eqClassDictDAO = eqClassDictDAO;
	}

	@Override
	public ReObject del(String classCode) {
		ReObject ro = new ReObject("删除设备类别");
		EqClassDict dict = eqClassDictDAO.findById(classCode);
		if (dict == null) {
			throw new RuntimeException("不存在此设备类别[编码：" + classCode + "]！");
		}
		List<EqClassDict> subDicts = eqClassDictDAO.findByProperty(
				"parentCode", classCode);
		if (subDicts != null && !subDicts.isEmpty()) {
			throw new RuntimeException("此设备类别存在子设备类别，不能删除！");
		}
		eqClassDictDAO.delete(dict);
		//还原上级类别的末级标志为1
		if (!"1".equals(dict.getCodeLevel())) {
			EqClassDict parentDict = eqClassDictDAO.findById(dict
					.getParentCode());
			if (parentDict != null) {
				parentDict.setEndSign("1");
			}
		}
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的设备类别");
		List<EqClassDict> data = eqClassDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqClassDict eqClassDict) {
		ReObject ro = new ReObject("保存设备类别");
		if (isAdd) {
			eqClassDict.setEndSign("1");
			String code = eqClassDict.getClassCode();
			if (eqClassDictDAO.findById(code) == null) {
				eqClassDictDAO.save(eqClassDict);
				//更新上级类别的末级标志为0
				if (!"1".equals(eqClassDict.getCodeLevel())) {
					EqClassDict parentDict = eqClassDictDAO
							.findById(eqClassDict.getParentCode());
					if (parentDict != null) {
						parentDict.setEndSign("0");
					}
				}
			} else {
				throw new RuntimeException("存在重复的编码" + code + "，违反了编码唯一性");
			}
		} else {
			eqClassDictDAO.update(eqClassDict);
		}
		return ro;
	}

}
