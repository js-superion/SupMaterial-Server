package cn.superion.materialDept.common.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;

/**
 * 科室物资公共接口
 * @author 曹国魁
 *
 */
public interface ICommMaterialService {
	/**
	 * 获取下一个流水号
	 * 
	 * @param rdFlag
	 *            收发标志 0:其他 1：收 2：发
	 * @return String 包含日期的当前号
	 */
	String getNextBillNo(String rdFlag);

	/**
	 * 查找当前现存量的数据列表，以便前台加载使用，用于手工出库，或红字单据入库等，
	 * 隐藏条件storageCode 仓库编码
	 * 
	 * @param fparameter
	 *            包含参数:
	 *            <ul>
	 *            <li>materialId 物资ID 非空</li>
	 *            <li>fatctoryCode 生产厂家</li>
	 *            <li>batch 批号</li>
	 *            </ul>
	 * 
	 * @return {@code List<MaterialCurrentStock>}当前库存汇总记录列表
	 */
	ReObject findCurrentStockByFactoryBatch(ParameterObject fparameter);

	/**
	 * 查询当前现存量，以便前台物资字典表格需加载现存量，全院现存量使用，当前仓库为空或0时，查找的是全院现存量
	 * 
	 * @param fstrMaterialId 物资ID
	 * @return ReObject 包含{@code List<Object>}第一个元素为Map
	 *         <ul>
	 *         <li>Key：currentStockAmount当前仓库现存量</li>
	 *         <li>Key：totalCurrentStockAmount当前全院现存量</li>
	 *         </ul>
	 */
	ReObject findCurrentStockById(String fstrMaterialId);
	
	/**
	 * 
	 * @param fstrMaterialId 物资ID
	 * @param fstrStorageCode 当前仓库，为空，则取当前科室编码
	 * @return
	 */
	ReObject findCurrentStockById(String fstrMaterialId,String fstrStorageCode);
	
	/**
	 * 保存收发存记录，并根据主记录当前状态是否审核更新库存数量；
	 * 新增，修改或审核时调用该方法，调用时必须正确设置fmaster的几个属性值：
	 * <ul>
	 * <li>单据类型 : 新增时非null</li>
	 * <li>收发标志 : 新增时非null</li>
	 * <li>收发类别 : 新增时非null,具体业务中具体指定</li>
	 * <li>业务类型 : 新增时非null,具体业务中具体指定</li>
	 * <li>业务号 : 如果具体业务就是针对出入库表操作，无其他业务表，则业务号可以为null,否则非null</li>
	 * <li>单据日期 : 来自于具体业务的开单日期</li>
	 * <li>仓库编码 : 新增时允许为null,默认为当前用户的deptCode</li>
	 * </ul>
	 * @param fmaster 收发存主记录
	 * @param fdetails 收发存明细记录
	 * @return Object[] 包含：
	 *         <ul>
	 *         <li>index-0为保存后的MaterialRdsMaster</li>
	 *         <li>index-1为保存后的{@code List<MaterialRdsDetail>}</li>
	 *         </ul>
	 */
	Object[] save(MaterialRdsMasterDept fmaster, List<MaterialRdsDetailDept> fdetails);
	
	/**
	 * 根据入出库主记录ID审核，更新当前库存量
	 * @param fstrAutoId
	 * @return 返回收发存主记录
	 */
	Object[] verify(String fstrAutoId);
	
	/**
	 * 芮玉红2012.09.27
	 * 弃审，如果明细记录已记账，则不能弃审 
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
	 * @return
	 */
	void cancelVerify(String fstrAutoId);
	
	/**
	 * 根据收发存主记录ID删除未审核的出入库记录
	 * @param fstrAutoId 收发存主记录ID
	 * @return 返回收发存主记录
	 */
	MaterialRdsMasterDept deleteByAutoId(String fstrAutoId);
	
	/**
	 * 根据收发存主记录ID查询主记录和明细记录
	 * @param fstrAutoId 收发存主记录ID
	 * @return Object[] 包含：
	 *         <ul>
	 *         <li>index-0为MaterialRdsMasterDept</li>
	 *         <li>index-1为{@code List<MaterialRdsDetailDept>}</li>
	 *         </ul>
	 */
	Object[] findById(String fstrAutoId);
	
	/**
	 * 根据业务号查询出入库单据
	 * @param unitsCode 单位编码
	 * @param storageCode 仓库编码
	 * @param operationType 业务类型
	 * @param operationNo 业务号
	 * @return Object[] 包含：
	 *         <ul>
	 *         <li>index-0为MaterialRdsMasterDept</li>
	 *         <li>index-1为{@code List<MaterialRdsDetailDept>}</li>
	 *         </ul> 
	 */
	Object[] findByOperationNo(String unitsCode,String storageCode,String operationType,String operationNo);
	
	/**
	 * 根据项目类别，编码，规格和单位查询HIS项目价格
	 * @param fstrClass HIS诊疗项目类别
	 * @param fstrCode HIS诊疗项目编码
	 * @param fstrSpec HIS诊疗项目规格
	 * @param fstrUnits HIS诊疗项目单位
	 * @return 返回价格
	 */
	ReObject findPrice(String fstrClass,String fstrCode,String fstrSpec,String fstrUnits);

	/**
	 * 写现存量汇总，需判断是否零出库（蓝字出库，或红字入库）
	 * @param isZeroDeliver 科室物资系统是否允许零出库
	 * @param fstock 要累积的现存量记录
	 * @return
	 */
	MaterialCurrentStockDept saveCurrentStock(boolean isZeroDeliver,
			MaterialCurrentStockDept fstock);
}
