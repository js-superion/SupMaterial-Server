package cn.superion.materialAcct.stat.service;

import java.util.ArrayList;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.MaterialRdsAcctMasterDAO;
import cn.superion.util.SessionUtil;
/**
 * 入库汇总服务实现
 * @author 曹国魁
 *
 */
public class ReceiveStatImpl implements IReceiveStat {
	private MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO;

	public MaterialRdsAcctMasterDAO getMaterialRdsAcctMasterDAO() {
		return materialRdsAcctMasterDAO;
	}

	public void setMaterialRdsAcctMasterDAO(
			MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO) {
		this.materialRdsAcctMasterDAO = materialRdsAcctMasterDAO;
	}

	@Override
	public ReObject findReceiveStatListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，汇总入库物资的进价金额，实价金额，批发金额和零售金额");
		List<Object> datas = new ArrayList<Object>();
		List<Object> data = materialRdsAcctMasterDAO.addUpReceiveByCondition(SessionUtil.getUnitsCode(), fparameter.getConditions());
		List<Object> obj=materialRdsAcctMasterDAO.miMaxBillNo(SessionUtil.getUnitsCode(),fparameter.getConditions());
		datas.add(data);
		datas.add(obj);
		ro.setData(datas);
		return ro;
	}

}
