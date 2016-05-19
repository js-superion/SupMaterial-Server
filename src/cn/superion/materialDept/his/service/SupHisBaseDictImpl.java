package cn.superion.materialDept.his.service;

import cn.superion.center.clinic.dao.CdClinicDictDAO;

/**
 * 正融HIS基础字典服务实现
 * 
 * @author 曹国魁
 * 
 */
public class SupHisBaseDictImpl implements IHisBaseDict {
	private CdClinicDictDAO cdClinicDictDAO;

	public CdClinicDictDAO getCdClinicDictDAO() {
		return cdClinicDictDAO;
	}

	public void setCdClinicDictDAO(CdClinicDictDAO cdClinicDictDAO) {
		this.cdClinicDictDAO = cdClinicDictDAO;
	}

	@Override
	public Double findPrice(String fstrClass, String fstrCode, String fstrSpec,
			String fstrUnits) {
//		String unitsCode = SessionUtil.getUnitsCode();
		// itemClass非空
		if (fstrClass == null || "".equals(fstrClass))
			throw new RuntimeException("查询HIS项目价格时，项目类别非空！");
		// itemCode非空
		if (fstrCode == null || "".equals(fstrCode))
			throw new RuntimeException("查询HIS项目价格时，项目编码非空！");
		return 0d;
		// "";cdClinicItemDictDAO.findRetailPrice(unitsCode,fstrClass,fstrCode,fstrSpec,fstrUnits);
	}
}
