package cn.superion.material.other.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;

/**
 * 
 * @author ryh 2013.1.17
 *
 */
public interface IRdTogether {


	ReObject findRdsMasterListByCondition(ParameterObject fparameter);
	
	ReObject findRdTogetherByAutoId(String fstrMainAutoId);
	
	/**
	 * 整进整出保存
	 * 
	 * @param receiveMaster 入库主记录
	 * @param deliverMaster 出库主记录
	 * @param details 明细
	 * @return
	 */
	ReObject saveRdTogether(MaterialRdsMaster receiveMaster,MaterialRdsMaster deliverMaster, List<MaterialRdsDetail> fdetails);
	
	/**
	 * 删除当前未审核的入库单据,采购业务时先还原采购订单已入库数和当前状态或特殊入库状态
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
	 * @return
	 */
	ReObject deleteRds(String fstrAutoId);

	/**
	 * 审核当前的入库单信息,改写当前现存量表
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
	 * @return 返回MaterialRdsMaster
	 */
	ReObject verifyRds(String fstrAutoId);
	
	/**
	 * 弃审
	 * @param fstrAutoId 收发存主记录ID
	 * @return
	 */
	ReObject cancelVerifyRds(String fstrAutoId);
	/**
	 * 更新打印状态
	 * @param fstrAutoId
	 * @return
	 */
	ReObject updateRdsPrintSign(String fstrAutoId);
	
}
