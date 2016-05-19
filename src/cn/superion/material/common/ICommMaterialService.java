package cn.superion.material.common;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.material.entity.MaterialRdsDetail;

/**
 * 物资公共服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICommMaterialService {
	/**
	 * 获取下一个流水号
	 * 
	 * @param rdFlag
	 *            收发标志 0:其他 1：收 2：发
	 * @param storageCode
	 *            仓库编码
	 * @return String 包含日期的当前号
	 */
	String getNextBillNo(String rdFlag,String rdType, String storageCode);
	String getNextBillNo(String rdFlag,String storageCode);
	String getNextBillMonthNo(String rdFlag,String rdType, String storageCode);

	/**
	 * 查找当前现存量的数据列表，以便前台加载使用，用于手工出库，或红字单据入库等
	 * 
	 * @param fparameter
	 *            包含参数:
	 *            <ul>
	 *            <li>storageCode 仓库编码 非空</li>
	 *            <li>materialId 物资ID</li>
	 *            <li>fatctoryCode 生产厂家</li>
	 *            <li>batch 批号</li>
	 *            <li>barCode 条码</li>
	 *            </ul>
	 * 
	 * @return {@code List<MaterialCurrentStock>}当前库存汇总记录列表
	 */
	ReObject findCurrentStockByFactoryBatch(ParameterObject fparameter);

	/**
	 * 查询当前现存量，以便前台物资字典表格需加载现存量，全院现存量使用，当前仓库为空或0时，查找的是全院现存量，
	 * 等同于findCurrentStockById方法
	 * 
	 * @param fstrMaterialId
	 * @param fstrStorageCode
	 * @return ReObject 包含{@code List<Object>}第一个元素为Map
	 *         <ul>
	 *         <li>Key：currentStockAmount当前仓库现存量</li>
	 *         <li>Key：totalCurrentStockAmount当前全院现存量</li>
	 *         </ul>
	 */
	@Deprecated
	ReObject findCurrentStockByIdStorage(String fstrMaterialId,
			String fstrStorageCode);

	/**
	 * 查询当前现存量，以便前台物资字典表格需加载现存量，全院现存量使用，当前仓库为空或0时，查找的是全院现存量
	 * 
	 * @param fstrMaterialId
	 *            物资ID
	 * @return ReObject 包含{@code List<Object>}第一个元素为Map
	 *         <ul>
	 *         <li>Key：currentStockAmount当前仓库现存量</li>
	 *         <li>Key：totalCurrentStockAmount当前全院现存量</li>
	 *         </ul>
	 */
	ReObject findCurrentStockById(String fstrMaterialId, String fstrStorageCode);

	/**
	 * 保存收发存记录，并根据主记录当前状态是否审核更新库存数量； 新增，修改或审核时调用该方法，调用时必须正确设置fmaster的几个属性值：
	 * <ul>
	 * <li>仓库编码 : 新增时非null</li>
	 * <li>单据类型 : 新增时非null</li>
	 * <li>收发标志 : 新增时非null</li>
	 * <li>收发类别 : 新增时非null,具体业务中具体指定</li>
	 * <li>业务类型 : 新增时非null,具体业务中具体指定</li>
	 * <li>业务号 : 如果具体业务就是针对出入库表操作，无其他业务表，则业务号可以为null,否则非null</li>
	 * <li>单据日期 : 来自于具体业务的开单日期</li>
	 * </ul>
	 * 
	 * @param fmaster
	 *            收发存主记录
	 * @param fdetails
	 *            收发存明细记录
	 * @return Object[] 包含：
	 *         <ul>
	 *         <li>index-0为保存后的MaterialRdsMaster</li>
	 *         <li>index-1为保存后的{@code List<MaterialRdsDetail>}</li>
	 *         </ul>
	 */
	Object[] save(MaterialRdsMaster fmaster, List<MaterialRdsDetail> fdetails);

	/**
	 * 根据入出库主记录ID审核，更新当前库存量
	 * 
	 * @param fstrAutoId
	 * @return 返回收发存主记录
	 */
	Object[] verify(String fstrAutoId);

	/**
	 * 弃审，如果明细记录已记账，则不能弃审
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
	 * @return
	 */
	void cancelVerify(String fstrAutoId);

	/**
	 * 根据收发存主记录ID删除未审核的出入库记录
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
	 * @return 返回收发存主记录
	 */
	MaterialRdsMaster deleteByAutoId(String fstrAutoId);
	/**
	 * 更新打印状态
	 * @param fstrAutoId
	 */
	void updatePrintSign(String fstrAutoId);

	/**
	 * 根据收发存主记录ID查询主记录和明细记录
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
	 * @return Object[] 包含：
	 *         <ul>
	 *         <li>index-0为MaterialRdsMaster</li>
	 *         <li>index-1为{@code List<MaterialRdsDetail>}</li>
	 *         </ul>
	 */
	Object[] findById(String fstrAutoId);

	ReObject uploadFileToTmp(String fstrFileName, byte[] faryFileData);

	/**
	 * 查询物资收发类别字典
	 * 
	 * @param fParameters
	 *            包含：
	 *            <ul>
	 *            <li>rdFlag 收发标志</li>
	 *            <li>phoInputCode 拼音码</li>
	 *            <li>fiveInputCode 五笔码</li>
	 *            </ul>
	 * @return {@code List<CdStorageDict>}
	 */
	ReObject findOperationTypeByCondition(ParameterObject fParameters);
}
