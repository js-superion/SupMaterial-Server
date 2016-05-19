package cn.superion.materialDept.his.service;

import java.util.List;

import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.PatsBillMaster;

/**
 * HIS病人费用服务接口
 * @author 曹国魁
 *
 */
public interface IHisBill {
	/**
	 * 病人费用记帐,支持一次收费(同样的开单科室和执行科室)下的单据写红字，红字单据需校验原蓝字费用未结算
	 * @param master 病人费用主记录
	 * @param details 病人使用材料明细记录列表，保存完后，明细记录需要记录HIS费用来源单据号或序号
	 */
	void save(PatsBillMaster master,List<MaterialPatsDetail> details);
	
	/**
	 * 写HIS红字费用
	 * @param master 病人费用主记录,只有病人标识号等信息，不含开单科室和执行科室
	 * @param blueDetails 蓝字单据列表
	 * @return 红字单据列表，记录了HIS费用来源单据号或序号
	 */
	List<MaterialPatsDetail> writeRed(PatsBillMaster master,List<MaterialPatsDetail> blueDetails);
	
}
