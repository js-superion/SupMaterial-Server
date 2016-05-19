package cn.superion.material.other.service;

import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialMonth;

/**
 * 月末结账服务接口
 * @author 曹国魁
 *
 */
public interface IMonth {
	/**
	 * 生成收发存汇总记录表，并写月末结账记录
	 * @param materialMonth 月末结账记录
	 * @return
	 */
	ReObject saveMonth(MaterialMonth  materialMonth);
	
	/**
	 * 生成结账汇总记录表，并写月末结账记录
	 * @param materialMonth 月末结账记录
	 * @return
	 */
	ReObject saveMonthFee(MaterialMonth  materialMonth);
	
	/**
	 * 取消结账，删除当前月份的收发存汇总记录表
	 * @param strStorageCode 仓库编码
	 * @param strYearMonth 月份,yyyy-MM格式
	 * @return
	 */
	ReObject cancelMonth (String strStorageCode,String strYearMonth);
	
	/**
	 * 查找当前年度的月末结账记录列表
	 * @param strStorageCode 仓库编码
	 * @param strYear 年份
	 * @return 返回 {@code List<MaterialMonth>}
	 */
	ReObject findMonth (String strStorageCode,String strYear);
}
