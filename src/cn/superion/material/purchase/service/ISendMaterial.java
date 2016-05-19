package cn.superion.material.purchase.service;

import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.CdDeptLimit;
import cn.superion.material.entity.MaterialInvoiceDetail;
import cn.superion.material.entity.MaterialInvoiceMaster;
import cn.superion.material.entity.MaterialProvideDetail;
import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.material.entity.MaterialSupplierDetail;
import cn.superion.material.entity.MaterialSupplierSummary;

/**
 * 配送物资服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ISendMaterial {
	/**
	 * 查询 表3.15中的信息
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>deptCode 部门</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>providerId 供货商</li>
	 *            </ul>
	 * 
	 * @return 返回主记录ID列表
	 */
	ReObject findSendMaterialListByCondition(ParameterObject fparameter);
	 ReObject findPrintData(Map<String,Object> map);
	 ReObject findDeptLimitInfo(String year, String season,String deptCode);
	/**
	 * 查询3.16明细列表
	 * 
	 * @param fstrAutoId
	 * @return 返回MaterialInvoiceMaster,{@code List<MaterialInvoiceDetail>}
	 */
	 
	ReObject verifySupplyDetails(String billNo,List<Map<String,Object>> groupItems);
	ReObject findSendDetailByAutoId(String fstrAutoId);
	ReObject saveDeptLimit(CdDeptLimit item);
	ReObject deleteDeptLimit(CdDeptLimit item);
	ReObject findDeptLimitByCon(ParameterObject fparameter);
	ReObject findGroupByProvider(ParameterObject fparameter);
	ReObject updateProviderDetail(List<MaterialProvideDetail> details);
	ReObject updateSendMaterialMaster2(List<MaterialProvideMaster> masters,String status);
	ReObject updateSendMaterialMaster3(List<MaterialProvideMaster> masters,String status);
	//保存供货商汇总数据
	ReObject saveSumary(List<Map<String,Object>> groupItems);
	ReObject delSumary(List<String>mainIds,List<String> detailIds);
	ReObject findSumary(ParameterObject fparameter);
	ReObject findSupplierDetail(String billNo);
	/**
	 * 更新申请明细记录，前台保存时，更新配送数量，审核时，更新状态为2
	 * 
	 * @param details
	 *            明细记录列表
	 * @return
	 */
	ReObject updateSendMaterial(MaterialProvideMaster master,List<MaterialProvideDetail> details);

	/**
	 * 根据供货单位，汇总查询申请单
	 * 
	 * @param autoIds,前台点击勾选框时，获取选中主记录的autoId
	 * @return 返回汇总集合
	 */
	ReObject findSendMaterialTotal(List<String> autoIds,String storageMaterialSign);
	/**
	 * 查询 表3.15中的信息
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>deptCode 部门</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>providerId 供货商</li>
	 *            </ul>
	 * 
	 * @return 返回主记录entity列表
	 */
	ReObject findSendMaterialEntityListByCondition(ParameterObject fparameter);
	

	ReObject findDetailByMainAutoIds(String[] fstrAutoIds,String storageMaterialSign);

	ReObject updateSendMaterialMaster(List<MaterialProvideMaster> masters);

	ReObject verifySendMaterial(List<MaterialProvideMaster> masters);

	ReObject findSendDetailByAutoIdAndOtherCons(String fstrAutoId,String storageMaterialSign);
	ReObject findProvider(ParameterObject fParameters);
}
