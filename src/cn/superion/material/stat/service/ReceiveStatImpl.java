package cn.superion.material.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.material.stat.entity.StockStatistic;
import cn.superion.util.SessionUtil;
/**
 * 入库汇总服务实现
 * @author 曹国魁
 *
 */
public class ReceiveStatImpl implements IReceiveStat {
	private VMaterialRdsDAO vMaterialRdsDAO;
	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}
	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	}
	@Override
	public ReObject findReceiveStatListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，按供应商，物资类别，物资编码，物资规格等汇总入库明细");
		fparameter.getConditions().put("rdFlag", RdConstant.R);
		List<StockStatistic> data = vMaterialRdsDAO.addUpSalerRdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findReceiveStatListByConditionSum(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，按供应商，物资类别，物资编码，物资规格等汇总入库明细");
		fparameter.getConditions().put("rdFlag", RdConstant.R);
		List<StockStatistic> data = vMaterialRdsDAO.addUpSalerRdsByConditionSum(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
