package cn.superion.material.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 药库收发常量
 * @author 曹国魁
 *
 */
public class RdConstant {
	//application code
	/** 仓库物资系统应用程序编码 */
	public static final String APP_CODE_STORAGE_MATERIAL = "888";
	/** 科室物资系统应用程序编码 */
	public static final String APP_CODE_DEPT_MATERIAL = "889";
	//系统参数code
	/** 单据编号生成规则 */
	public static final String SYS_PARA_CODE_BILL_NO = "0101";
	/** 自动出库方式 */
	public static final String SYS_PARA_CODE_DELIVE_TYPE = "0201";
	/** 是否出入库跟踪 */
	public static final String SYS_PARA_CODE_TRACE = "0202";
	/** 采购入库是否校验供应商物资授权 */
	public static final String SYS_PARA_CODE_MATERIAL_AUTHORIZATION = "0203";
	/** 代销物资是否托管 */
	public static final String SYS_PARA_CODE_AGENT_TRUSTEESHIP = "0204";
	/** 是否使用领用卡 */
	public static final String SYS_PARA_CODE_USE_MATERIAL_CARD = "0205"; 
	/** 是否允许零出库 */
	public static final String SYS_PARA_CODE_ZERO_INVENTORY = "0601";
	
	//采购业务
	/** 采购计划保存自动审核 */
	public static final String SYS_PARA_CODE_PURCHASE_PLAN_CHK = "0301";
	/** 采购申请保存自动审核 */
	public static final String SYS_PARA_CODE_PURCHASE_APPLY_CHK = "0302";
	/** 采购订单保存自动审核 */
	public static final String SYS_PARA_CODE_PURCHASE_ORDER_CHK = "0303";
	/** 采购到货保存自动审核 */
	public static final String SYS_PARA_CODE_PURCHASE_ARRIVAL_CHK = "0304";
	/** 发票登记保存自动审核 */
	public static final String SYS_PARA_CODE_PURCHASE_INVOICE_CHK = "0305";
	
	//入库业务
	/** 采购入库保存时是否自动审核 */
	public static final String SYS_PARA_CODE_R_PURCHASE_CHK = "0401";
	/** 其它入库保存时是否自动审核 */
	public static final String SYS_PARA_CODE_R_OTHERS_CHK = "0402";
	/** 期初入库时是否自动审核 */
	public static final String SYS_PARA_CODE_INITIAL_CHK = "0405";
	
	
	//出库业务
	/** 物资领用保存时是否自动审核 */
	public static final String SYS_PARA_CODE_D_DELIVER_CHK = "0403";
	/** 其他出库保存时是否自动审核 */
	public static final String SYS_PARA_CODE_D_OTHERS_CHK = "0404";
	
	//其他业务
	/** 报损处理审核时出库单据是否生效--更新库存数量 */
	public static final String SYS_PARA_CODE_REJECT_LAUNCH = "0501";
	/** 盘点处理审核时出库单据和入库单据是否生效--更新库存数量 */
	public static final String SYS_PARA_CODE_CHECK_LAUNCH = "0503";
	
	/** 代销物资领用生产的入库类别编码 */
	public static final String SYS_PARA_CODE_AGENT_R_TYPE = "0701";
	/** 代销物资领用生产的出库类别编码 */
	public static final String SYS_PARA_CODE_AGENT_D_TYPE = "0702";
	
	//业务类型
	/** 普通采购 */
	public static final String R_PURCHASE = "101";
	/** 受托代销 */
	public static final String R_AGENCY = "102";
	/** 直运 */
	public static final String R_DIRECT = "103";
	/** 盘盈入库 */
	public static final String R_CHECK_PROFIT = "104";
	/** 期初入库 */
	public static final String R_INITIAL = "105";
	/** 特殊入库 */
	public static final String R_SPECIAL = "106";
	/** 领用入库，科室物资系统使用 */
	public static final String R_APPLY = "107";
	/** 一般高值入库 */
	public static final String R_VALUE = "108";
	/** 其他入库 */
	public static final String R_OTHERS = "109";
	/**整进整出 */
	public static final String R_TOGETHER = "110";
	
	/** 领用出库 */
	public static final String D_DELIVER = "201";
	/** 销售出库 */
	public static final String D_SALE = "202";
	/** 调拨出库 */
	public static final String D_CANN = "203";
	/** 盘亏出库 */
	public static final String D_CHECK_LOSS = "204";
	/** 报损出库 */
	public static final String D_REJECT = "205";
	/** 其他出库 */
	public static final String D_OTHERS = "209";
	/**整进整出 */
	public static final String D_TOGETHER = "210";
	
	//收发标志
	/** 收 */
	public static final String R = "1";
	/** 发 */
	public static final String D = "2";
	/** 其他 */
	public static final String OTHERS = "9";
	public static  HashMap<String,Object> mp1 = new HashMap<String,Object>();
	static {
		mp1.put("1", "1");
		mp1.put("2", "1");
		mp1.put("3", "1");
		
		mp1.put("4", "2");
		mp1.put("5", "2");
		mp1.put("6", "2");
		
		mp1.put("7", "3");
		mp1.put("8", "3");
		mp1.put("9", "3");
		
		mp1.put("10", "4");
		mp1.put("11", "4");
		mp1.put("12", "4");
//		season.add
	}
}
