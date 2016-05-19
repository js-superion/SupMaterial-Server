package cn.superion.cssd.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.VCssdRetrieveDAO;
import cn.superion.util.SessionUtil;
/**
 * 物品报损统计服务实现
 * @author 曹国魁
 *
 */
public class RetrievedMaterialRejectionImpl implements
		IRetrievedMaterialRejection {
	private VCssdRetrieveDAO vCssdRetrieveDAO;
	public VCssdRetrieveDAO getvCssdRetrieveDAO() {
		return vCssdRetrieveDAO;
	}
	public void setvCssdRetrieveDAO(VCssdRetrieveDAO vCssdRetrieveDAO) {
		this.vCssdRetrieveDAO = vCssdRetrieveDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，获取当前符合条件的物品包回收时的物品报损统计列表");
		List data = vCssdRetrieveDAO.addUpByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
