package cn.superion.material.receive.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.VMaterialPats;

public interface ISpecialValue {

	
	/**
	 * 根据条件查询病人使用高值耗材明细
	 * @param fparam
	 * @return
	 */
	ReObject findMaterialValueDetailByCondition(ParameterObject fparam);
	

	
	/**
	 * 保存高值耗材入库
	 * @param fmaster
	 * @param details
	 * @return
	 */
	ReObject saveMaterialRds(MaterialRdsMaster fmaster,String[] fstrAutoIds,List<VMaterialPats> fPatsDetails);
	
	
}
