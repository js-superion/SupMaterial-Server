package cn.superion.cssd.work.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdDeliverMaster;
import cn.superion.cssd.entity.CssdStockMaster;

/**
 * 物品发放处理服务接口
 * @author 曹国魁
 *
 */
public interface IDeliver {
	/**
	 * 根据查找条件，获取当前符合条件的物品发放列表
	 * @param fparameter 包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>deptCode 申请科室</li>
	 *            <li>personId 申请人</li>
	 *            <li>deliverPerson 送货人</li>
	 *            <li>packageId 物品包编码</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findMasterIdListByCondition(ParameterObject fparameter);
	
	/**
	 * 查看当前物品发放处理的详细信息记录
	 * @param fstrAutoId 发放主记录ID
	 * @return CssdDeliverMaster,{@code List<CssdStockMaster>}，{@code List<CssdProvideDetail>}
	 */
	ReObject findDetailById(String fstrAutoId);
	
	/**
	 * 保存当前物品发放信息
	 * @param fmaster 发放主记录
	 * @param flstStockMaster 物品包记录列表
	 * @return CssdDeliverMaster,{@code List<CssdStockMaster>}
	 */
	ReObject save(CssdDeliverMaster fmaster, List<CssdStockMaster> flstStockMaster);
	
	/**
	 * 删除当前未审核的物品发放单据
	 * @param fstrAutoId 发放主记录ID
	 * @return
	 */
	ReObject deleteById(String fstrAutoId);
	
	/**
	 * 审核当前的物品发放信息,并且要扣减物资库存数量
	 * @param fstrAutoId 发放主记录ID
	 * @return CssdDeliverMaster
	 */
	ReObject verify(String fstrAutoId);
	
	/**
	 * 过滤物品发放申请单,分页查询
	 * @param fparameter
	 * @return {@code List<CssdProvideMaster>}
	 */
	ReObject findProvideListByCondition(ParameterObject fparameter);
	
	
	
	ReObject findProvideListByPackageId(String packageId,String deptCode);
	/**
	 * 根据发放主记录ID查询发放申请明细列表
	 * @param fstrAutoId 发放主记录ID
	 * @return {@code List<CssdProvideDetail>}
	 */
	ReObject findProvideDetailById(String fstrAutoId);
	
	/**
	 * 根据物品包编号或拼音码，五笔码查询已灭菌的物品包信息
	 * @param fparameter 包含packageNo,phoInputCode,fiveInputCode
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findStockMasterByPackageNo(ParameterObject fparameter);
	
	/**
	 * 根据物品包ID和发放数量，按有效期止顺序自动发放已灭菌的物品包
	 * @param fstrPackageId 物品包ID
	 * @param famount 请求的发放数量
	 * @return {@code List<CssdStockMaster>}REGISTER
	 */
	ReObject findSterilizedByPackageId(String fstrPackageId,Double famount);
	
	
	ReObject findBatchSterilizedByPackageId(List<CssdStockMaster> cssdStockMasterList);
	/**
	 * 病人用包登记
	 * @param cssdStockMasters
	 * @return
	 */
	ReObject saveRegister(List<CssdStockMaster> cssdStockMasters);
	
	/**
	 * 按条件跟踪包
	 * @param fparameter
	 * @return
	 */
	ReObject findStockMasterStatu(ParameterObject fparameter);
	
	/**
	 * 按照条件查询登记信息
	 */
	ReObject findStockMasterInpNo(ParameterObject fparameter);
	
	/**
	 * 登记领取审核 
	 */
	ReObject verifyInpNo(List<CssdStockMaster> cssdStockMasterList,String stat);
	
	/**
	 * 按照包号过滤已灭菌的包
	 */
	ReObject findStockMasterPackageNo(String PackageNo);
	
}
