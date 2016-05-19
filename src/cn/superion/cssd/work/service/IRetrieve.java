package cn.superion.cssd.work.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdRetrieveDetail;
import cn.superion.cssd.entity.CssdRetrieveMaster;


/**
 * 物品回收处理服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IRetrieve {
	/**
	 * 根据查找条件，获取当前符合条件的物品回收列表
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>deptCode 回收科室</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findMasterIdListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前物品回收处理的详细信息记录
	 * 
	 * @param fstrAutoId
	 * @return CssdRetrieveMaster,{@code List<CssdRetrieveDetail>}
	 */
	ReObject findDetailById(String fstrAutoId);
	
	/**
	 * 保存当前物品回收信息，修改时,如果有关联的物资报损单，需写报损红单；
	 * 对于通过拼音码或五笔码查询的物品包物资汇总的业务过程，需要按有效期至顺序来分配回收科室之前已审核发放的的物品包物资，作为实际回收的物资
	 * 
	 * @param fmaster 回收主记录
	 * @param flstDetail 回收明细记录，对于通过拼音码或五笔码查询的物品包物资汇总的业务过程,实为汇总的物品包物资记录，需包含属性：物品包ID packageId
	 * @return CssdRetrieveMaster,{@code List<CssdRetrieveDetail>}
	 */
	ReObject save(CssdRetrieveMaster fmaster, List<CssdRetrieveDetail> flstDetail);
	
	/**
	 * 删除当前未审核的物品回收单据,如果有关联的物资报损单，需写报损红单
	 * @param fstrAutoId
	 * @return
	 */
	ReObject deleteById(String fstrAutoId);
	
	/**
	 * 审核当前的物品回收信息，并且要增加物资库存数量
	 * @param fstrAutoId
	 * @return CssdRetrieveMaster
	 */
	ReObject verify(String fstrAutoId);
	
	/**
	 * 过滤已审的物品发放单，分页查询
	 * @param fparameter 包含
	 *            <ul>
	 *            <li>deptCode 回收科室</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            <li>usedSign 当前状态（使用标志）</li>
	 *            </ul>
	 * @return CssdRetrieveMaster
	 */
	ReObject findDeliverListByCondition(ParameterObject fparameter);
	
	/**
	 * 根据物品发放单主记录ID查询物品包视图列表
	 * @param fstrAutoId
	 * @return  {@code List<VCssdStock>}
	 */
	ReObject findDeliverDetail(String fstrAutoId);
	
	/**
	 * 根据条码或拼音码，五笔码查询可回收物品包视图
	 * @param fparameter 包含packageNo,phoInputCode,fiveInputCode
	 * @return {@code List<VCssdStock>}
	 */
	ReObject findRetrieveVStockByPackageNo(ParameterObject fparameter);
	
	/**
	 * 根据物品包ID查询回收科室领用的物品包明细汇总，包含应回数量（领用出库数量）
	 * @param fstrDeptCode 回收科室
	 * @param fstrPackageId 物品包编码
	 * @return {@code List<DeptPackageMaterial>}
	 */
	ReObject findPackageMaterial(String fstrDeptCode,String fstrPackageId);

}
