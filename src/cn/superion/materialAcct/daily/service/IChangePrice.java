package cn.superion.materialAcct.daily.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialChangePriceDetail;
import cn.superion.material.entity.MaterialChangePriceMaster;

/**
 * 调价记录单接口
 * 
 * @author 李攀
 * 
 */
public interface IChangePrice {

	/**
	 *根据条件查询调价报告单记录
	 * 
	 * @param fparam参数说明
	 *            调价主记录条件
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>maker 制单人</li>
	 *            调价明细记录条件
	 *            <li>storageCode 仓库编码</li>
     *            <li>beginMaterialClass 起始物资分类</li>
	 *            <li>endMaterialClass 结束物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            </ul>
	 *@return {@code List<MaterialChangePriceMaster>}
	 * 
	 * 
	 */
	ReObject findByConditon(ParameterObject fparameter);

	/**
	 * 根据ID查询当前调价单据信息
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>String fstrAutoId</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"调价记录单"</li>
	 *         <li>data[0]为ChangePriceMaster</li>
	 *         <li>data[1]为{@code List<ChangePriceDetail>}</li>
	 *         </ul>
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 *保存调价记录
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>MaterialChangePriceMaster master</li>
	 *            <li>List<MaterialChangePriceDetail> detail</li>
	 *            </ul>
	 *@return 返回值ReObject包含属性
	 *         <ul>
	 *         <li>action 值为"保存调价信息"</li>
	 *         <li>data 值为{master}</li>
	 *         </ul>
	 */
	ReObject save(MaterialChangePriceMaster master,
			List<MaterialChangePriceDetail> detail);

	/**
	 * 删除未审核的调价记录
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>String fstrAutoId</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"删除调价信息"</li>
	 *         <li>data 值为{null}</li>
	 *         </ul>
	 * 
	 */

	ReObject del(String fstrAutoId);

	/**
	 * 审核未审核的调价记录
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>String fstrAutoId</li>
	 *            </ul>
	 *@return <ul>
	 *         <li>action 值为"审核调价信息"</li>
	 *         <li>data 值为{ChangePriceMaster}</li>
	 *         </ul>
	 * 
	 */

	ReObject verify(String fstrAutoId);
}
