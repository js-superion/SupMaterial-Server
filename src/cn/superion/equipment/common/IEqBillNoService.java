package cn.superion.equipment.common;

/**
 * 单据编号（当前流水号）服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqBillNoService {
	
	/**
	 * 获取下一个流水号
	 * 
	 * @param rcptFlag
	 *            业务标志 1：设备变更 2：作业内容 3：作业计划 4：作业单 5：测量点记录 6：故障记录 7：运行记录8:维修申请
	 * @return String 包含日期的当前号
	 */
	String getNextBillNo(String rcptFlag);
}
