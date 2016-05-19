package cn.superion.materialAcct.stat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialRdsAcctMasterDAO;
import cn.superion.util.SessionUtil;
/**
 * 出库汇总服务实现
 * @author 曹国魁
 *
 */
public class DeliverStatImpl implements IDeliverStat {
	private MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO;
	
	public MaterialRdsAcctMasterDAO getMaterialRdsAcctMasterDAO() {
		return materialRdsAcctMasterDAO;
	}

	public void setMaterialRdsAcctMasterDAO(
			MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO) {
		this.materialRdsAcctMasterDAO = materialRdsAcctMasterDAO;
	}

	@Override
	public ReObject findDeliverStatByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，汇总出库物资的批发金额和零售金额");
		fparameter.getConditions().put("rdFlag", RdConstant.D);
		List<Object> datas = new ArrayList<Object>();
		List<Object> data = materialRdsAcctMasterDAO.addUpDeliverByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		List<Object> obj=materialRdsAcctMasterDAO.miMaxBillNo(SessionUtil.getUnitsCode(),fparameter.getConditions());
		datas.add(data);
		datas.add(obj);
		ro.setData(datas);
		return ro;
	}
	
	public ReObject findDeliverStatByAccount(ParameterObject fparameter) {
		ReObject ro = new ReObject("按会计科目汇总出库明细");
		fparameter.getConditions().put("rdFlag", RdConstant.D);
		Map mp = materialRdsAcctMasterDAO.addUpDeptRdsByAccount(SessionUtil.getUnitsCode(),fparameter.getConditions());
		List<Object[]> lstResult = (List<Object[]>) mp.get("result");
		List<Object[]> lstMaterialClass = (List<Object[]>) mp.get("lstMaterialClass");
		List list = new ArrayList();
		list.add(lstResult); //统计结果集
		list.add(lstMaterialClass);//物资分类集合
		ro.setData(list);
		return ro;
	}
}
