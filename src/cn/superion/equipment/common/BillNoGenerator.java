package cn.superion.equipment.common;

import cn.superion.util.DateUtil;
import cn.superion.util.StringUtil;

public class BillNoGenerator {
	/**
	 * 根据流水号类型和流水号当前值生成流水号的算法，流水号类型有以下值：
	 * <ul>
	 * <li>1-YYYYMMDD+4位数字</li>
	 * <li>2-YYYYMMDD+3位数字</li>
	 * <li>3-YYYYMMDD+2位数字</li>
	 * <li>4-YYYYMM+4位数字</li>
	 * <li>5-YYYYMM+3位数字</li>
	 * <li>6-YYYYMM+2位数字</li>
	 * </ul>
	 * 
	 * @param fstrRcptType
	 *            流水号类型
	 * @param fstrCurTypeDate
	 * 	流水号的当前日期           
	 * @param fstrCurNo
	 *            流水号当前值,若为null,则为新的流水号的第一个号
	 * @param fstrNowTypeDate
	 * 流水号现在日期           
	 * @return String 不含日期字符串的下一流水号
	 * @throws Exception 
	 */
	public static String getNextNo(String fstrRcptType, String fstrCurTypeDate,String fstrCurNo,String fstrNowTypeDate) throws Exception {
		// YYYYMMDD+4位数字
		if ("1".equals(fstrRcptType)) {
			return parseNo(fstrCurTypeDate,fstrCurNo, fstrNowTypeDate, 4);
		}
		// YYYYMMDD+3位数字
		if ("2".equals(fstrRcptType)) {
			return parseNo(fstrCurTypeDate,fstrCurNo, fstrNowTypeDate, 3);
		}
		// YYYYMMDD+2位数字
		if ("3".equals(fstrRcptType)) {
			return parseNo(fstrCurTypeDate,fstrCurNo, fstrNowTypeDate, 2);
		}
		// YYYYMM+4位数字
		if ("4".equals(fstrRcptType)) {
			return parseNo(fstrCurTypeDate,fstrCurNo, fstrNowTypeDate, 4);
		}
		// YYYYMM+3位数字
		if ("5".equals(fstrRcptType)) {
			return parseNo(fstrCurTypeDate,fstrCurNo, fstrNowTypeDate, 3);
		}
		//YYYYMM+2位数字
		if ("6".equals(fstrRcptType)) {
			return parseNo(fstrCurTypeDate,fstrCurNo, fstrNowTypeDate, 2);
		}
		//默认编码规则：YYYYMMDD+4位数字
		return parseNo(fstrCurTypeDate,fstrCurNo, fstrNowTypeDate, 4);
		//throw new RuntimeException("流水号类型值["+fstrRcptType+"]不正确,应在[1,2,3,4,5,6]集合中取值");
	}
	
	public static String getNowTypeDate(String fstrRcptType){
		if("1".equals(fstrRcptType) || "2".equals(fstrRcptType) || "3".equals(fstrRcptType)){
			return DateUtil.getCurrentDateVersion();
		}else if("4".equals(fstrRcptType) || "5".equals(fstrRcptType) || "6".equals(fstrRcptType)){
			return DateUtil.getCurYYMM();
		}
		throw new RuntimeException("流水号类型值["+fstrRcptType+"]不正确,应在[1,2,3,4,5,6]集合中取值");
	}

	private static String parseNo(String fstrCurTypeDate,String fstrCurNo,String fstrNowTypeDate,int digital){
		if(fstrCurNo==null || "".equals(fstrCurNo)){
			return StringUtil.lpad("1",digital);
		}
		if(fstrNowTypeDate!=null){
			String number = null;
			if(fstrNowTypeDate.equals(fstrCurTypeDate)){
				number = String.valueOf(Integer.parseInt(fstrCurNo) + 1);
			}else{
				number = "1";
			}
			return StringUtil.lpad(number,digital);
		}
		return null;
	}
}
