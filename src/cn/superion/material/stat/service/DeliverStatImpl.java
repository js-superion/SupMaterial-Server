package cn.superion.material.stat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.material.stat.entity.StockStatistic;
import cn.superion.util.SessionUtil;

/**
 * 出库汇总服务实现
 * @author 曹国魁
 *
 */
public class DeliverStatImpl implements IDeliverStat {
	private VMaterialRdsDAO vMaterialRdsDAO;
	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}
	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	}
	@Override
	public ReObject findDeliverStatListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，按领用部门，物资类别，物资编码，物资规格等汇总出库明细");
		fparameter.getConditions().put("rdFlag", RdConstant.D);
		List<StockStatistic> data = vMaterialRdsDAO.addUpDeptRdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findDeliverStatListByDept(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据科室，物资分类查询费用");
		List<String> data1 = vMaterialRdsDAO.findClassName(fparameter);
		List<String> data2 = vMaterialRdsDAO.findDept(fparameter);
		List<Map<String,Object>> listMap = vMaterialRdsDAO.findFee(fparameter);
		List data = new ArrayList();
		data.add(data1);
		data.add(data2);
		data.add(listMap);
		ro.setData(data);
		return ro;
	}
	//查询计算机分类费用
	@Override
	public ReObject findDeliverStatListByDeptComputerFee(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据科室，物资分类查询费用");
		List<Map<String,Object>> listMap = vMaterialRdsDAO.findComputerFee(fparameter);
		List data = new ArrayList();
		data.add(listMap);
		ro.setData(data);
		return ro;
	}
	
	//查询医疗物资分类费用
	@Override
	public ReObject findDeliverStatListByDeptMedicalFee(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据科室，物资分类查询费用");
		List<Map<String,Object>> listMap = vMaterialRdsDAO.findMedicalFee(fparameter);
		List data = new ArrayList();
		data.add(listMap);
		ro.setData(data);
		return ro;
	}
	//查询计价类别分类费用
	@Override
	public ReObject findDeliverCountClassListByDeptMedicalFee(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据科室，物资分类查询费用");
		List<Map<String,Object>> listMap = vMaterialRdsDAO.findMedicalFeeByCountClass(fparameter);
		List data = new ArrayList();
		data.add(listMap);
		ro.setData(data);
		return ro;
	}
	
	//查询计价类别入库分类费用
	@Override
	public ReObject findDeliverCountClassListByDeptMedicalReceiveFee(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据科室，物资分类查询费用");
		List<Map<String,Object>> listMap = vMaterialRdsDAO.findMedicalReceiveFeeByCountClass(fparameter);
		List data = new ArrayList();
		data.add(listMap);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findDeliverStatListByDeptNew(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据科室，物资分类查询费用");
		List<String> data1 = vMaterialRdsDAO.findClassName(fparameter);
		List data = new ArrayList();
		data.add(data1);
		ro.setData(data);
		return ro;
	}
	//查询计算机物资类别，只要是计算机仓库出库的
	@Override
	public ReObject findDeliverStatListByDeptComputer(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询计算机物资类别");
		List<Map<String,Object>> data1 = vMaterialRdsDAO.findComputerClassName(fparameter);
		List data = new ArrayList();
		data.add(data1);
		ro.setData(data);
		return ro;
	}
	//查询医疗物资类别，从物资类别表中进行查询
	@Override
	public ReObject findDeliverStatListByDeptMedical(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询医疗物资类别");
		List<Map<String,Object>> data1 = vMaterialRdsDAO.findMedicalClassName(fparameter);
		List data = new ArrayList();
		data.add(data1);
		ro.setData(data);
		return ro;
	}
}
