package cn.superion.equipment.system.service;

import java.util.List;

import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqNationClassDictDAO;
import cn.superion.equipment.entity.EqNationClassDict;
public class EqNationClassDictImpl implements IEqNationClassDict {
	private EqNationClassDictDAO eqNationClassDictDAO;

	public EqNationClassDictDAO getEqNationClassDictDAO() {
		return eqNationClassDictDAO;
	}

	public void setEqNationClassDictDAO(EqNationClassDictDAO eqNationClassDictDAO) {
		this.eqNationClassDictDAO = eqNationClassDictDAO;
	}

	@Override
	public ReObject del(String classCode) {
		ReObject ro = new ReObject("删除设备国家类别");
		EqNationClassDict dict = eqNationClassDictDAO.findById(classCode);
		if (dict == null) {
			throw new RuntimeException("不存在此设备国家类别[编码：" + classCode + "]！");
		}
		List<EqNationClassDict> subDicts = eqNationClassDictDAO.findByProperty(
				"parentCode", classCode);
		if (subDicts != null && !subDicts.isEmpty()) {
			throw new RuntimeException("此设备类别存在子设备类别，不能删除！");
		}
		eqNationClassDictDAO.delete(dict);
		//还原上级类别的末级标志为1
		if (!"1".equals(dict.getCodeLevel())) {
			EqNationClassDict parentDict = eqNationClassDictDAO.findById(dict
					.getParentCode());
			if (parentDict != null) {
				parentDict.setEndSign("1");
			}
		}
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的设备国家类别");
		List<EqNationClassDict> data = eqNationClassDictDAO.findAll();
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqNationClassDict eqNationClassDict) {
		ReObject ro = new ReObject("保存设备国家类别");
		if (isAdd) {
			eqNationClassDict.setEndSign("1");
			String code = eqNationClassDict.getClassCode();
			if (eqNationClassDictDAO.findById(code) == null) {
				eqNationClassDictDAO.save(eqNationClassDict);
				//更新上级类别的末级标志为0
				if (!"1".equals(eqNationClassDict.getCodeLevel())) {
					EqNationClassDict parentDict = eqNationClassDictDAO
							.findById(eqNationClassDict.getParentCode());
					if (parentDict != null) {
						parentDict.setEndSign("0");
					}
				}
			} else {
				throw new RuntimeException("存在重复的编码" + code + "，违反了编码唯一性");
			}
		} else {
			eqNationClassDictDAO.update(eqNationClassDict);
		}
		return ro;
	}

}
